package com.ssafy.enjoytrip.global.security;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {
    private Member member;

    public SecurityUser(Member member) {
        super(member.getId().toString(), member.getMemberPwd(),
                AuthorityUtils.createAuthorityList("ROLE_" + member.getRoleType().toString()));
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}
