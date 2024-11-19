package com.ssafy.enjoytrip.global.security;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.member.entity.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberEamil) throws UsernameNotFoundException {
        Optional<Member> optional = memberRepository.findByMemberEmail(memberEamil);
        if(!optional.isPresent()) {
            throw new UsernameNotFoundException(memberEamil + " 사용자 없음");
        } else {
            Member member = optional.get();
            return new SecurityUser(member);
        }
    }
}
