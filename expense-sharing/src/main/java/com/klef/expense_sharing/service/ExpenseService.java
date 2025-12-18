package com.klef.expense_sharing.service;


import com.klef.expense_sharing.dto.AddExpenseRequest;
import com.klef.expense_sharing.entity.*;
import com.klef.expense_sharing.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseSplitRepository expenseSplitRepository;
    private final BalanceRepository balanceRepository;

    public void addExpense(AddExpenseRequest request) {

        User paidBy = userRepository.findById(request.getPaidByUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Expense expense = new Expense();
        expense.setAmount(request.getAmount());
        expense.setSplitType(request.getSplitType());
        expense.setPaidBy(paidBy);
        expense.setGroup(group);

        expenseRepository.save(expense);

        for (Map.Entry<Long, Double> entry : request.getSplits().entrySet()) {

            User user = userRepository.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            double owedAmount;

            if (request.getSplitType() == SplitType.EQUAL) {
                owedAmount = request.getAmount() / request.getSplits().size();
            } else if (request.getSplitType() == SplitType.EXACT) {
                owedAmount = entry.getValue();
            } else {
                owedAmount = request.getAmount() * entry.getValue() / 100;
            }

            ExpenseSplit split = new ExpenseSplit();
            split.setExpense(expense);
            split.setUser(user);
            split.setAmountOwed(owedAmount);

            expenseSplitRepository.save(split);

            if (!user.getId().equals(paidBy.getId())) {
                updateBalance(user, paidBy, owedAmount);
            }
        }
    }

    private void updateBalance(User from, User to, double amount) {

        Balance balance = balanceRepository.findByFromUserAndToUser(from, to);

        if (balance == null) {
            balance = new Balance();
            balance.setFromUser(from);
            balance.setToUser(to);
            balance.setAmount(amount);
        } else {
            balance.setAmount(balance.getAmount() + amount);
        }

        balanceRepository.save(balance);
    }
}
