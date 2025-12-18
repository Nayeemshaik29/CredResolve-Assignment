package com.klef.expense_sharing.dto;


import com.klef.expense_sharing.entity.SplitType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;
import java.util.List;

@Data
public class AddExpenseRequest {

    @NotNull
    private Long groupId;

    @NotNull
    private Long paidByUserId;

    @NotNull
    private Double amount;

    @NotNull
    private SplitType splitType;

    // userId -> amount or percentage
    private Map<Long, Double> splits;
}
