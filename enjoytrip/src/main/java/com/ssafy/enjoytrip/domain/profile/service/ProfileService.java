package com.ssafy.enjoytrip.domain.profile.service;

import com.ssafy.enjoytrip.domain.city.entity.Town;
import com.ssafy.enjoytrip.domain.city.entity.TownId;
import com.ssafy.enjoytrip.domain.city.entity.repository.CityRepository;
import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.member.entity.repository.MemberRepository;
import com.ssafy.enjoytrip.domain.plan.entity.Plan;
import com.ssafy.enjoytrip.domain.plan.entity.repository.PlanRepository;
import com.ssafy.enjoytrip.domain.profile.dto.request.ProfileUpdateRequestDto;
import com.ssafy.enjoytrip.domain.profile.dto.response.*;
import com.ssafy.enjoytrip.domain.review.entity.Review;
import com.ssafy.enjoytrip.domain.review.entity.ReviewComment;
import com.ssafy.enjoytrip.domain.review.entity.repository.ReviewCommentRepository;
import com.ssafy.enjoytrip.domain.review.entity.repository.ReviewRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final PlanRepository planRepository;
    private final ReviewCommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;

    //기본 프로필 정보 조회
    public ProfileResponseDto getProfile(String email) {
        Member member = memberRepository.findByMemberEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        UserInfoDto userInfo = getUserInfo(member);
        StatsDto stats = getStats(member);

        return ProfileResponseDto.builder()
                .userInfo(userInfo)
                .stats(stats)
                .build();
    }

    //내가 작성한 리뷰조회
    public List<ReviewProfileDto> getMyReviews(String email) {
        Member member = getMember(email);
        return reviewRepository.findByMemberId(member.getId()).stream()
                .map(this::toReviewProfileDto)
                .collect(Collectors.toList());
    }

    //내가 좋아요 한 리뷰조회
    public List<ReviewProfileDto> getLikedReviews(String email) {
        Member member = getMember(email);
        return reviewRepository.findLikedReviewsByMemberId(member.getId()).stream()
                .map(this::toReviewProfileDto)
                .collect(Collectors.toList());
    }

    //내가 댓글 단 리뷰 조회
    public List<ProfileReviewCommentDto> getMyComments(String email) {
        Member member = getMember(email);
        return commentRepository.findByMemberId(member.getId()).stream()
                .map(this::toProfileCommentDto)
                .collect(Collectors.toList());
    }

    //나의 여행계획
    public List<PlanProfileDto> getMyPlans(String email) {
        Member member = getMember(email);
        return planRepository.findByMemberId(member.getId()).stream()
                .map(this::toPlanProfileDto)
                .collect(Collectors.toList());
    }

    //비밀번호 확인
    public boolean checkPassword(String email, String password) {
        Member member = getMember(email);
        return passwordEncoder.matches(password, member.getMemberPwd());
    }

    //회원정보수정
    @Transactional
    public void updateProfile(String email, ProfileUpdateRequestDto requestDto) {
        Member member = getMember(email);

        Town town = null;
        if (requestDto.getTownCode() != null && requestDto.getCityCode() != null) {
            try {
                town = entityManager.createQuery(
                                "SELECT t FROM Town t " +
                                        "WHERE t.id = :townCode " +        // town_code
                                        "AND t.city.id = :cityCode", Town.class)  // city의 city_code
                        .setParameter("townCode", requestDto.getTownCode().longValue())  // Integer를 Long으로 변환
                        .setParameter("cityCode", requestDto.getCityCode().longValue())
                        .getSingleResult();
            } catch (NoResultException e) {
                throw new IllegalArgumentException("존재하지 않는 지역입니다.");
            }
        }

        member.updateProfile(
                requestDto.getNickname(),
                requestDto.getProfileImage(),
                town
        );
    }

    private Member getMember(String email) {
        return memberRepository.findByMemberEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    private UserInfoDto getUserInfo(Member member) {
        return UserInfoDto.builder()
                .memberName(member.getMemberName())
                .email(member.getMemberEmail())
                .nickname(member.getNickname())
                .profileImage(member.getProfileImage())
                .cityName(member.getCity() != null ? member.getCity().getCityName() : null)
                .townName(member.getTown() != null ? member.getTown().getTownName() : null)
                .build();
    }

    private StatsDto getStats(Member member) {
        return StatsDto.builder()
                .reviewCount(reviewRepository.countByMemberId(member.getId()))
                .planCount(planRepository.countByMemberId(member.getId()))
                .build();
    }

    private ReviewProfileDto toReviewProfileDto(Review review) {
        return ReviewProfileDto.builder()
                .id(review.getReviewId())
                .title(review.getReviewTitle())
                .content(review.getReviewContent())
                .reviewImage(review.getReviewImage())
                .likeCount(review.getLikeCount())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    private ProfileReviewCommentDto toProfileCommentDto(ReviewComment comment) {
        return ProfileReviewCommentDto.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .review(ProfileReviewCommentDto.ReviewInfo.builder()
                        .reviewId(comment.getReview().getReviewId())
                        .title(comment.getReview().getReviewTitle())
                        .build())
                .build();
    }

    private PlanProfileDto toPlanProfileDto(Plan plan) {
        return PlanProfileDto.builder()
                .id(plan.getId())
                .title(plan.getTitle())
                .thumbnailImage(plan.getThumbnailImage())
                .startDate(plan.getStartDate())
                .endDate(plan.getEndDate())
                .day(plan.getDay())
                .cityName(plan.getCity().getCityName())
                .build();
    }


}
