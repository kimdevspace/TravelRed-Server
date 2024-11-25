package com.ssafy.enjoytrip.domain.admin.notices.controller;

import com.ssafy.enjoytrip.domain.admin.notices.dto.request.NoticeCreateRequestDto;
import com.ssafy.enjoytrip.domain.admin.notices.dto.request.NoticeUpdateRequestDto;
import com.ssafy.enjoytrip.domain.admin.notices.dto.response.NoticeAdminResponseDto;
import com.ssafy.enjoytrip.domain.admin.notices.dto.response.NoticeDetailResponseDto;
import com.ssafy.enjoytrip.domain.admin.notices.service.NoticeAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/notices")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class NoticeAdminController {
    private final NoticeAdminService noticeAdminService;

    //모든 공지 조회
    @GetMapping
    public ResponseEntity<Page<NoticeAdminResponseDto>> getAllNotices(Pageable pageable) {
        return ResponseEntity.ok(noticeAdminService.getAllNotices(pageable));
    }

    //공지 상세조회
    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeDetailResponseDto> getNotice(@PathVariable Long noticeId) {
        return ResponseEntity.ok(noticeAdminService.getNotice(noticeId));
    }

    //공지 작성
    @PostMapping
    public ResponseEntity<Void> createNotice(@RequestBody NoticeCreateRequestDto noticeCreateRequestDto) {
        noticeAdminService.creteNotice(noticeCreateRequestDto);
        return ResponseEntity.ok().build();
    }

    //공지 수정
    @PatchMapping("/{noticeId}")
    public ResponseEntity<Void> updateNotice(@PathVariable Long noticeId, @RequestBody NoticeUpdateRequestDto noticeUpdateRequestDto) {
        noticeAdminService.updateNotice(noticeId, noticeUpdateRequestDto);
        return ResponseEntity.ok().build();
    }

    //공지 삭제
    @DeleteMapping("/{noticeId}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long noticeId) {
        noticeAdminService.deleteNotice(noticeId);
        return ResponseEntity.ok().build();
    }
}
