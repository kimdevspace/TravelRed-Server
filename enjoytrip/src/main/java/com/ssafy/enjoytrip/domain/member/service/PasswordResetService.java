package com.ssafy.enjoytrip.domain.member.service;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.member.entity.repository.MemberRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class PasswordResetService {
    private final JavaMailSender javaMailSender;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 토큰 저장소: <이메일, 토큰> 형태로 저장
    private static final Map<String, String> resetTokens = new ConcurrentHashMap<>();

    @Value("${app.email.from}")
    private String emailFrom;

    // 비밀번호 재설정 이메일 발송
    public void sendPasswordResetEmail(String email) throws Exception {
        Member member = memberRepository.findByMemberEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 사용자가 없습니다."));

        String token = UUID.randomUUID().toString();

        // 토큰 저장
        resetTokens.put(email, token);

        // 1시간 후 토큰 제거를 위한 스케줄링
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                resetTokens.remove(email);
            }
        }, TimeUnit.HOURS.toMillis(1));  // 1시간 후 실행

        // 이메일 내용 생성
        String emailContent = String.format("""
                <div style='margin:100px;'>
                    <h1>비밀번호 재설정</h1>
                    <p>아래 인증 코드를 입력하여 비밀번호를 재설정하세요:</p>
                    <p>인증 코드: <strong>%s</strong></p>
                    <p>이 코드는 1시간 동안만 유효합니다.</p>
                </div>
                """, token);

        // 이메일 전송
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(emailFrom);
        helper.setTo(email);
        helper.setSubject("[EnjoyTrip] 비밀번호 재설정");
        helper.setText(emailContent, true);

        javaMailSender.send(message);
    }

    // 비밀번호 재설정
    @Transactional
    public void resetPassword(String email, String token, String newPassword) {
        // 토큰 검증
        String storedToken = resetTokens.get(email);
        if (storedToken == null || !storedToken.equals(token)) {
            throw new IllegalArgumentException("유효하지 않은 인증 코드입니다.");
        }

        Member member = memberRepository.findByMemberEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 사용자가 없습니다."));

        // 로그 추가
        System.out.println("이전 비밀번호: " + member.getMemberPwd());

        // 비밀번호 암호화 및 업데이트
        String encodedPassword = passwordEncoder.encode(newPassword);
        System.out.println("새로운 암호화된 비밀번호: " + encodedPassword);

        member.updatePassword(encodedPassword);
        Member savedMember = memberRepository.save(member);

        // 저장 후 확인
        System.out.println("저장된 비밀번호: " + savedMember.getMemberPwd());

        resetTokens.remove(email);
    }
}