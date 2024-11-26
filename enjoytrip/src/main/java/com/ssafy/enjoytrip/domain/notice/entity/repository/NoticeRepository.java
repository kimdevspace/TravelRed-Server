package com.ssafy.enjoytrip.domain.notice.entity.repository;

import com.ssafy.enjoytrip.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findTop10ByOrderByCreatedAtDesc();
    List<Notice> findAll();

    @Query("SELECT n FROM Notice n " +
            "WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(n.content) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "ORDER BY n.createdAt DESC")
    List<Notice> searchNoticesWithKeyWord(@Param("keyword") String keyword);
}
