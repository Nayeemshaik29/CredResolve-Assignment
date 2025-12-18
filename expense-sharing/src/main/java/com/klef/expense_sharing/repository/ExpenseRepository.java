package com.klef.expense_sharing.repository;

import com.klef.expense_sharing.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
