package com.klef.expense_sharing.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User fromUser;   // who owes

    @ManyToOne
    private User toUser;     // who gets money

    private Double amount;
}
