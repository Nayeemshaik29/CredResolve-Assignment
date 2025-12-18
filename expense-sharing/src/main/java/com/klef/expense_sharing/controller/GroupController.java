package com.klef.expense_sharing.controller;


import com.klef.expense_sharing.dto.AddGroupMemberRequest;
import com.klef.expense_sharing.dto.CreateGroupRequest;
import com.klef.expense_sharing.entity.Group;
import com.klef.expense_sharing.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Group createGroup(@Valid @RequestBody CreateGroupRequest request) {
        return groupService.createGroup(request);
    }

    @PostMapping("/{groupId}/members")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMember(@PathVariable Long groupId,
                          @Valid @RequestBody AddGroupMemberRequest request) {
        groupService.addMember(groupId, request);
    }
}
