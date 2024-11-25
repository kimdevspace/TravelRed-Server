package com.ssafy.enjoytrip.domain.admin.members.dto.response;

import com.ssafy.enjoytrip.domain.member.entity.RoleType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberAdminResponseDto {
    private String memberEmail;
    private String memberName;
    private RoleType roleType;
    private Boolean isLocked;
}
