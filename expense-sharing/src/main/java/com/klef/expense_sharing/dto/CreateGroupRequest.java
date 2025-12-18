package com.klef.expense_sharing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateGroupRequest {

    @NotBlank
    private String name;

    @NotNull
    private Long createdByUserId;
}
