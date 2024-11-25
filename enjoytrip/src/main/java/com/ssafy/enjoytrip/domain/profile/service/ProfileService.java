package com.ssafy.enjoytrip.domain.profile.service;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.member.entity.repository.MemberRepository;
import com.ssafy.enjoytrip.domain.plan.entity.repository.PlanRepository;
import com.ssafy.enjoytrip.domain.profile.dto.response.ProfileResponseDto;
import com.ssafy.enjoytrip.domain.profile.dto.response.ProfileReviewCommentDto;
import com.ssafy.enjoytrip.domain.profile.dto.response.StatsDto;
import com.ssafy.enjoytrip.domain.profile.dto.response.UserInfoDto;
import com.ssafy.enjoytrip.domain.review.entity.ReviewComment;
import com.ssafy.enjoytrip.domain.review.entity.repository.ReviewCommentRepository;
import com.ssafy.enjoytrip.domain.review.entity.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final PlanRepository planRepository;
    private final ReviewCommentRepository commentRepository;

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


}
