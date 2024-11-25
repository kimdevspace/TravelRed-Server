package com.ssafy.enjoytrip.domain.member.entity;

import java.time.LocalDateTime;

import com.ssafy.enjoytrip.domain.city.entity.City;
import com.ssafy.enjoytrip.domain.city.entity.Town;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "members")
public class Member {

    @Id
    @Column(name = "member_id")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_code", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL) // 삭제되면 null로 변경
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "town_code", referencedColumnName = "town_code"),
            @JoinColumn(name = "city_code", referencedColumnName = "city_code")
    })
    //    @JoinColumn(name = "town_code", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Town town;    // 다른 테이블에서 가져오는 것

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @NotNull
    private Boolean isEmailVerified;

    @NotNull
    private Boolean isLocked;

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
        this.providerType = (providerType != null ? providerType : ProviderType.LOCAL); // 기본값 설정
        this.roleType = (roleType != null ? roleType : RoleType.USER); // 기본값 설정
        this.isEmailVerified = (isEmailVerified != null ? isEmailVerified : false);
        this.isLocked = (isLocked != null ? isLocked : false);
        this.createdAt = LocalDateTime.now(); // createdAt 초기화
        this.updatedAt = this.createdAt;     // updatedAt 초기화
    }


}
