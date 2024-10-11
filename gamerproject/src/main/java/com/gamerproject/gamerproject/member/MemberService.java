package com.gamerproject.gamerproject.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean login(MemberLoginDto memberLoginDto) {

        Member member = memberRepository.findByUserId(memberLoginDto.getUserid());


        if (member != null && passwordEncoder.matches(memberLoginDto.getPassword(), member.getPassword())) {
            return true;
        }else{
            return false;
        }
    }

    public void memberJoin(MemberDto memberDto,String interest) {
        Member member = new Member();
        member.setUserId(memberDto.getUserid());
        var passwordEncoding = passwordEncoder.encode(memberDto.getPassword());
        member.setPassword(passwordEncoding);
        member.setDisplayName(memberDto.getDisplayName());
        member.setEmail(memberDto.getEmail());
        member.setInterest(interest);

        memberRepository.save(member);
    }
}
