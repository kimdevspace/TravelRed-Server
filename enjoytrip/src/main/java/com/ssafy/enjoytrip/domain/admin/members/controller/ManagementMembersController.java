package com.ssafy.enjoytrip.domain.admin.members.controller;

import com.ssafy.enjoytrip.domain.admin.members.dto.response.MemberAdminResponseDto;
import com.ssafy.enjoytrip.domain.admin.members.service.ManagementMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 회원 활성화/비활성화
     * @PathVariable memberEmail
     */
    @PatchMapping("/{memberEmail}/toggle-lock")
    public ResponseEntity<MemberAdminResponseDto> toggleMemberLock(@PathVariable String memberEmail) {
        return ResponseEntity.ok(managementMemberService.toggleMemberLock(memberEmail));
    }
}
