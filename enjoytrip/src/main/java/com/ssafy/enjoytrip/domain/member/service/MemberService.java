package com.ssafy.enjoytrip.domain.member.service;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.member.entity.ProviderType;
import com.ssafy.enjoytrip.domain.member.entity.RoleType;
import com.ssafy.enjoytrip.domain.member.entity.repository.MemberRepository;
import com.ssafy.enjoytrip.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${app.server.host}")
    private String serverHost;

    @Value("${app.email.from}")
    private String emailFrom;
    public Long createMember(Map<String, String> user) {
        try {
            // 입력값 검증
            if (user.get("memberEmail") == null || user.get("memberPwd") == null ||
                    user.get("memberName") == null || user.get("nickname") == null) {
                throw new IllegalArgumentException("필수 입력값이 누락되었습니다.");
            }

            // 공백 제거 및 유효성 검사
            String memberEmail = user.get("memberEmail").trim();
            String memberPwd = user.get("memberPwd").trim();
            String memberName = user.get("memberName").trim();
            String nickname = user.get("nickname").trim();

            System.out.println("Email: " + memberEmail);  // 로깅 추가
            System.out.println("Name: " + memberName);
            System.out.println("Nickname: " + nickname);

            // 이메일 중복 검사
            if (memberRepository.findByMemberEmail(memberEmail).isPresent()) {
                throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
            }

            Member newMember = Member.builder()
                    .memberEmail(memberEmail)
                    .memberPwd(passwordEncoder.encode(memberPwd))
                    .memberName(memberName)
                    .nickname(nickname)
                    .roleType(RoleType.USER)
                    .providerType(ProviderType.LOCAL)
                    .isEmailVerified(false)
                    .isLocked(false)
                    .build();

            Member savedMember = memberRepository.save(newMember);

            try {
                sendVerificationEmail(savedMember);
            } catch (Exception e) {
                System.out.println("이메일 전송 중 에러: " + e.getMessage());
                e.printStackTrace();
            }

            return savedMember.getId();
        } catch (Exception e) {
            System.out.println("회원 생성 중 에러: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public String login(Map<String, String> user) {
        Member member = memberRepository.findByMemberEmail(user.get("memberEmail"))
                .orElseThrow(() -> new IllegalArgumentException("가입 되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(user.get("memberPwd"), member.getMemberPwd())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 맞지 않습니다.");
        }

        if (!member.getIsEmailVerified()) {
            throw new IllegalArgumentException("이메일 인증을 완료해주세요.");
        }

        return jwtTokenProvider.createToken(member.getMemberEmail(), member.getRoleType(), member.getId());
    }

    // 인증 이메일 보내기
    private void sendVerificationEmail(Member member) {
        String verifyUrl = serverHost + "/api/v1/member/verify/" + member.getMemberEmail();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailFrom);
        mailMessage.setTo(member.getMemberEmail());
        mailMessage.setSubject("EnjoyTrip 이메일 인증");
        mailMessage.setText("다음 링크를 클릭하여 이메일을 인증해주세요: " + verifyUrl);

        mailSender.send(mailMessage);
    }

    // 이메일 인증 확인
    public boolean verifyEmail(String email) {
        Optional<Member> userOpt = memberRepository.findByMemberEmail(email);

        if (userOpt.isPresent()) {
            Member member = userOpt.get();
            member.setIsEmailVerified(true);
            memberRepository.save(member);
            return true;
        }
        return false;
    }

}
