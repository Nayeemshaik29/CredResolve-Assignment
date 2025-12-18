package com.klef.expense_sharing.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ExpenseSplit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Expense expense;

    @ManyToOne
    private User user;

    private Double amountOwed;
}
