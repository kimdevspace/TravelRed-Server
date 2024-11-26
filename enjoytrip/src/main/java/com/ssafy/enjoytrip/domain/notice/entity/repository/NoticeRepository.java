package com.ssafy.enjoytrip.domain.notice.entity.repository;

import com.ssafy.enjoytrip.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findTop10ByOrderByCreatedAtDesc();
    List<Notice> findAll();
}
