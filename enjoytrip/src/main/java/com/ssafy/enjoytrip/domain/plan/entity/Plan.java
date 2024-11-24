package com.ssafy.enjoytrip.domain.plan.entity;

import com.ssafy.enjoytrip.domain.city.entity.City;
import com.ssafy.enjoytrip.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity //이 클래스가 JPA 엔터티임을 나타낸다. DB 테이블과 매핑
@Table(name = "plan") // 매핑할 테이블의 이름 지정
@Getter // 롬복, 모든 필드의 게터 메서드 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 롬복, 파라미터가 업슨ㄴ 기본생성자 생성 (액세스 레벨을 설정하여 외부에서 무분별한 객체생성 막기)
public class Plan {

    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 사용함을 의미
    @Column(name = "plan_id")
    private Long id;

    @Column(name = "plan_title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "thumbnail_image")
    private String thumbnailImage;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_code")
    private City city;

    @Column(name = "day", nullable = false)
    private Integer day;

    @Builder
    private Plan(String title, Member member, String thumbnailImage,
                 LocalDate startDate, LocalDate endDate, City city, Integer day) {
        // 필수값 검증
        if (title == null || member == null || city == null || day == null) {
            throw new IllegalArgumentException("필수 값이 누락되었습니다.");
        }

        this.title = title;
        this.member = member;
        this.thumbnailImage = thumbnailImage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.city = city;
        this.day = day;
    }
}