package com.klef.expense_sharing.service;


import com.klef.expense_sharing.dto.AddGroupMemberRequest;
import com.klef.expense_sharing.dto.CreateGroupRequest;
import com.klef.expense_sharing.entity.Group;
import com.klef.expense_sharing.entity.GroupMember;
import com.klef.expense_sharing.entity.User;
import com.klef.expense_sharing.repository.GroupMemberRepository;
import com.klef.expense_sharing.repository.GroupRepository;
import com.klef.expense_sharing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;

    public Group createGroup(CreateGroupRequest request) {

        User creator = userRepository.findById(request.getCreatedByUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Group group = Group.builder()
                .name(request.getName())
                .createdBy(creator)
                .build();

        Group savedGroup = groupRepository.save(group);

        // Add creator as group member
        GroupMember member = GroupMember.builder()
                .group(savedGroup)
                .user(creator)
                .build();

        groupMemberRepository.save(member);

        return savedGroup;
    }

    public void addMember(Long groupId, AddGroupMemberRequest request) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        GroupMember member = GroupMember.builder()
                .group(group)
                .user(user)
                .build();

        groupMemberRepository.save(member);
    }
}
