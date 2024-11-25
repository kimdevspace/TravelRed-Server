package com.ssafy.enjoytrip.domain.profile.controller;

import com.ssafy.enjoytrip.domain.profile.dto.response.ProfileResponseDto;
import com.ssafy.enjoytrip.domain.profile.service.ProfileService;
import com.ssafy.enjoytrip.global.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<ProfileResponseDto> getProfile(@AuthenticationPrincipal SecurityUser securityUser) {
        return ResponseEntity.ok(profileService.getProfile(securityUser.getMember().getMemberEmail()));
    }

}
