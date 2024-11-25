package com.ssafy.enjoytrip.domain.admin.notices.service;

import com.ssafy.enjoytrip.domain.admin.notices.dto.request.NoticeCreateRequestDto;
import com.ssafy.enjoytrip.domain.admin.notices.dto.request.NoticeUpdateRequestDto;
import com.ssafy.enjoytrip.domain.admin.notices.dto.response.NoticeAdminResponseDto;
import com.ssafy.enjoytrip.domain.admin.notices.dto.response.NoticeDetailResponseDto;
import com.ssafy.enjoytrip.domain.notice.entity.Notice;
import com.ssafy.enjoytrip.domain.notice.entity.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeAdminService {

    private final NoticeRepository noticeRepository;
    // 공지사항 목록 조회
    public Page<NoticeAdminResponseDto> getAllNotices(Pageable pageable) {
        return noticeRepository.findAll(pageable)
                .map(this::toDto);
    }

    // 공지사항 상세조회
    public NoticeDetailResponseDto getNotice(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 존재하지 않습니다."));
        return toDetailDto(notice);
    }

    // 공지사항 생성
    @Transactional
    public void creteNotice(NoticeCreateRequestDto noticeCreateRequestDto) {
        Notice notice = Notice.builder()
                .title(noticeCreateRequestDto.getTitle())
                .content(noticeCreateRequestDto.getContent())
                .build();
        noticeRepository.save(notice);
    }

    // 공지사항 수정
    @Transactional
    public void updateNotice(Long noticeId, NoticeUpdateRequestDto requestDto) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 존재하지 않습니다."));

        notice.update(requestDto.getTitle(), requestDto.getContent());
    }

    //공지사항 삭제
    @Transactional
    public void deleteNotice(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }

    // Entity -> DTO 변환
    private NoticeAdminResponseDto toDto(Notice notice) {
        return NoticeAdminResponseDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .createdAt(notice.getCreatedAt())
                .build();
    }

    // Entity -> DetailDto 변환
    private NoticeDetailResponseDto toDetailDto(Notice notice) {
        return NoticeDetailResponseDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .memberName("관리자")
                .createdAt(notice.getCreatedAt())
                .content(notice.getContent())
                .build();
    }
}
