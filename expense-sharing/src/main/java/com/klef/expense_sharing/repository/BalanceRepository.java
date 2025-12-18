package com.klef.expense_sharing.repository;

import com.klef.expense_sharing.entity.Balance;
import com.klef.expense_sharing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Balance findByFromUserAndToUser(User from, User to);
    List<Balance> findByFromUser(User fromUser);

    List<Balance> findByToUser(User toUser);
}
