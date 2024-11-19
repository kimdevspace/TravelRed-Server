package com.ssafy.enjoytrip.domain.member.entity;

import java.time.LocalDateTime;

import com.ssafy.enjoytrip.domain.city.entity.City;
import com.ssafy.enjoytrip.domain.city.entity.Town;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "members")
public class Member {

    @Id @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String memberName;

    @Column(nullable = false, unique = true)
    private String memberEmail;

    @NotNull
    private String memberPwd;

    @NotNull
    private String nickname;

    private String profileImage;

    @ManyToOne
    @JoinColumn(name = "city_code", referencedColumnName = "city_code", nullable = true)
    private City city;

    @ManyToOne
    @JoinColumn(name = "town_code", referencedColumnName = "town_code", nullable = true)
    private Town town;    // 다른 테이블에서 가져오는 것

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @NotNull
    private Boolean isEmailVerified = false;

    @NotNull
    private Boolean isLocked = false;

    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;

    // 생성자 정의
    @Builder
    public Member(
            String memberName,
            String memberEmail,
            String memberPwd,
            String nickname,
            String profileImage,
            City city,
            Town town,
            ProviderType providerType,
            RoleType roleType,
            Boolean isEmailVerified,
            Boolean isLocked
    ) {
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPwd = memberPwd;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.city = city;
        this.town = town;
        this.providerType = providerType != null ? providerType : ProviderType.LOCAL; // 기본값 설정
        this.roleType = roleType != null ? roleType : RoleType.USER; // 기본값 설정
        this.isEmailVerified = isEmailVerified != null ? isEmailVerified : false;
        this.isLocked = isLocked != null ? isLocked : false;
        this.createdAt = LocalDateTime.now(); // createdAt 초기화
        this.updatedAt = this.createdAt;     // updatedAt 초기화
    }


}
