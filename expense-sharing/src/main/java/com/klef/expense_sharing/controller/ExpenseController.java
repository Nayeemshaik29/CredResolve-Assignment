package com.klef.expense_sharing.controller;

import com.klef.expense_sharing.dto.AddExpenseRequest;
import com.klef.expense_sharing.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addExpense(@Valid @RequestBody AddExpenseRequest request) {
        expenseService.addExpense(request);
    }
}
