package com.ssafy.enjoytrip.domain.admin.members.service;

import com.ssafy.enjoytrip.domain.admin.members.dto.response.MemberAdminResponseDto;
import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.member.entity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    private MemberAdminResponseDto convertToAdminDto(Member member) {
        return MemberAdminResponseDto.builder()
                .memberEmail(member.getMemberEmail())
                .memberName(member.getMemberName())
                .roleType(member.getRoleType())
                .isLocked(member.getIsLocked())
                .build();
    }
}
