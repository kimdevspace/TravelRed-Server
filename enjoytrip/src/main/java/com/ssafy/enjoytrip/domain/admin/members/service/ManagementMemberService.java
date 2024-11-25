package com.ssafy.enjoytrip.domain.admin.members.service;

import com.ssafy.enjoytrip.domain.admin.members.dto.response.MemberAdminResponseDto;
import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.member.entity.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagementMemberService {
    private final MemberRepository memberRepository;

    //회원목록 조회
    public Page<MemberAdminResponseDto> getMembers(Pageable pageable) {
        return memberRepository.findAll(pageable)
                .map(this::convertToAdminDto);
    }

    @Transactional
    public MemberAdminResponseDto toggleMemberLock(String memberEmail) {
        Member member = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new EntityNotFoundException("멤버를 찾지 못했습니다."));

        member.setIsLocked(!member.getIsLocked());
        member.setUpdatedAt(LocalDateTime.now());

        return convertToAdminDto(member);
    }


    private MemberAdminResponseDto convertToAdminDto(Member member) {
        return MemberAdminResponseDto.builder()
                .memberEmail(member.getMemberEmail())
                .memberName(member.getMemberName())
                .roleType(member.getRoleType())
                .isLocked(member.getIsLocked())
                .build();
    }
}
