package com.klef.expense_sharing.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private SplitType splitType;

    @ManyToOne
    private User paidBy;

    @ManyToOne
    private Group group;
}
