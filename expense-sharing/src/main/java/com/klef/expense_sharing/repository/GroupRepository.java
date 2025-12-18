package com.klef.expense_sharing.repository;

import com.klef.expense_sharing.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
