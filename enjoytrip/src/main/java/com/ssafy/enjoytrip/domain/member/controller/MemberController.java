package com.ssafy.enjoytrip.domain.member.controller;

import com.ssafy.enjoytrip.domain.member.dto.request.MemberEmailValidationRequestDto;
import com.ssafy.enjoytrip.domain.member.dto.request.PasswordResetRequestDto;
import com.ssafy.enjoytrip.domain.member.service.MemberService;
import com.ssafy.enjoytrip.domain.member.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;
    private final PasswordResetService passwordResetService;

    // 회원가입
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Map<String, String> user) {
        try {
            Long memberId = memberService.createMember(user);
            return ResponseEntity.ok(memberId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();  // 에러 스택 트레이스 출력
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> user) {
        try {
            String token = memberService.login(user);
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 회원가입 시 이메일 인증
    @GetMapping("/verify/{memberEmail}")
    public ResponseEntity<String> verifyEmail(@PathVariable String memberEmail) {
        boolean verified = memberService.verifyEmail(memberEmail);

        if (verified) {
            return ResponseEntity.ok("이메일이 성공적으로 인증되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("이메일 인증에 실패했습니다.");
        }
    }

    @PostMapping("/find-password")
    public ResponseEntity<?> requestPasswordReset(@RequestBody MemberEmailValidationRequestDto validationRequestDto) {
        try {
            passwordResetService.sendPasswordResetEmail(validationRequestDto.getMemberEmail());
            return ResponseEntity.ok().body("비밀번호 재설정 이메일이 전송되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequestDto requestDto) {
        try {
            passwordResetService.resetPassword(
                    requestDto.getEmail(),
                    requestDto.getToken(),
                    requestDto.getNewPassword()
            );
            return ResponseEntity.ok().body("비밀번호가 성공적으로 재설정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
