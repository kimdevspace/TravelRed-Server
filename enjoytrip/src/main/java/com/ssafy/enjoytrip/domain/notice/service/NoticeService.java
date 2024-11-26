package com.ssafy.enjoytrip.domain.notice.service;

import com.ssafy.enjoytrip.domain.notice.entity.Notice;
import com.ssafy.enjoytrip.domain.notice.entity.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<Notice> getNoticesForHome() {
        return noticeRepository.findTop10ByOrderByCreatedAtDesc();
    }

    public List<Notice> getAll() {
        return noticeRepository.findAll();
    }
}
