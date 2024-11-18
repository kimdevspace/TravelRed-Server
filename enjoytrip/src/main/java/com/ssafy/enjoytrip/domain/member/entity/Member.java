package com.ssafy.enjoytrip.domain.member.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @NotNull
    private String memberName;

    @Column(nullable = false, unique = true)
    private String memberEmail;

    @NotNull
    private String memberPwd;

    @NotNull
    private String nickname;

    private String profileImage;

    private Integer cityCode;   // 다른 테이블에서 가져오는 것

    private String townCode;    // 다른 테이블에서 가져오는 것

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProviderType providerType = ProviderType.LOCAL;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.USER;

    @NotNull
    private Boolean isEmailVerified = false;

    @NotNull
    private Boolean isLocked = false;

    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;

    // @PrePersist & @PreUpdate를 사용하여 createdAt, updatedAt 자동 설정
    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        updatedAt = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }



}
