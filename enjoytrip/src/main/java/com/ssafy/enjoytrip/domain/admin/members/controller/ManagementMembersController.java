package com.ssafy.enjoytrip.domain.admin.members.controller;

import com.ssafy.enjoytrip.domain.admin.members.dto.response.MemberAdminResponseDto;
import com.ssafy.enjoytrip.domain.admin.members.service.ManagementMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/members")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ManagementMembersController {

    private final ManagementMemberService managementMemberService;

    @GetMapping
    public ResponseEntity<Page<MemberAdminResponseDto>> getMembers(Pageable pageable) {
        return ResponseEntity.ok(managementMemberService.getMembers(pageable));
    }
}
