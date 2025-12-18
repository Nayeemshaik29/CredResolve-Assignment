package com.klef.expense_sharing.controller;


import com.klef.expense_sharing.entity.Balance;
import com.klef.expense_sharing.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balances")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping("/user/{userId}")
    public List<Balance> getUserBalances(@PathVariable Long userId) {
        return balanceService.getBalancesForUser(userId);
    }
}
