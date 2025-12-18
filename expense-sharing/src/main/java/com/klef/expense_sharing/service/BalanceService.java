package com.klef.expense_sharing.service;


import com.klef.expense_sharing.entity.Balance;
import com.klef.expense_sharing.entity.User;
import com.klef.expense_sharing.repository.BalanceRepository;
import com.klef.expense_sharing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final UserRepository userRepository;

    public List<Balance> getBalancesForUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return balanceRepository.findByFromUser(user);
    }

    public void simplifyBalance(User from, User to, double amount) {

        Balance reverse = balanceRepository.findByFromUserAndToUser(to, from);

        if (reverse != null) {
            if (reverse.getAmount() > amount) {
                reverse.setAmount(reverse.getAmount() - amount);
                balanceRepository.save(reverse);
                return;
            } else if (reverse.getAmount() < amount) {
                amount = amount - reverse.getAmount();
                balanceRepository.delete(reverse);
            } else {
                balanceRepository.delete(reverse);
                return;
            }
        }

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
