package com.ssafy.enjoytrip.domain.profile.controller;

import com.ssafy.enjoytrip.domain.profile.dto.request.PasswordCheckRequestDto;
import com.ssafy.enjoytrip.domain.profile.dto.request.ProfileUpdateRequestDto;
import com.ssafy.enjoytrip.domain.profile.dto.response.PlanProfileDto;
import com.ssafy.enjoytrip.domain.profile.dto.response.ProfileResponseDto;
import com.ssafy.enjoytrip.domain.profile.dto.response.ProfileReviewCommentDto;
import com.ssafy.enjoytrip.domain.profile.dto.response.ReviewProfileDto;
import com.ssafy.enjoytrip.domain.profile.service.ProfileService;
import com.ssafy.enjoytrip.global.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<ProfileResponseDto> getProfile(@AuthenticationPrincipal SecurityUser securityUser) {
        return ResponseEntity.ok(profileService.getProfile(securityUser.getMember().getMemberEmail()));
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewProfileDto>> getMyReviews(@AuthenticationPrincipal SecurityUser securityUser) {
        return ResponseEntity.ok(profileService.getMyReviews(securityUser.getMember().getMemberEmail()));
    }

    @GetMapping("/reviews/liked")
    public ResponseEntity<List<ReviewProfileDto>> getLikedReviews(@AuthenticationPrincipal SecurityUser securityUser) {
        return ResponseEntity.ok(profileService.getLikedReviews(securityUser.getMember().getMemberEmail()));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<ProfileReviewCommentDto>> getMyComments(@AuthenticationPrincipal SecurityUser securityUser) {
        return ResponseEntity.ok(profileService.getMyComments(securityUser.getMember().getMemberEmail()));
    }

    @GetMapping("/plans")
    public ResponseEntity<List<PlanProfileDto>> getMyPlans(@AuthenticationPrincipal SecurityUser securityUser) {
        return ResponseEntity.ok(profileService.getMyPlans(securityUser.getMember().getMemberEmail()));
    }

    @PostMapping("/password-check")
    public ResponseEntity<Void> checkPassword(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody PasswordCheckRequestDto requestDto
    ) {
        boolean isValid = profileService.checkPassword(
                securityUser.getMember().getMemberEmail(),
                requestDto.getPassword()
        );

        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().build();
    }

    // 프로필 수정
    @PatchMapping
    public ResponseEntity<Void> updateProfile(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody ProfileUpdateRequestDto requestDto
    ) {
        profileService.updateProfile(securityUser.getMember().getMemberEmail(), requestDto);
        return ResponseEntity.ok().build();
    }

}
