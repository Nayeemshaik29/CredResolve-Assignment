package com.klef.expense_sharing.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddGroupMemberRequest {

    @NotNull
    private Long userId;
}
