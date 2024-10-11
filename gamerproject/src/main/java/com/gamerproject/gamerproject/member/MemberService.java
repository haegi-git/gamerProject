package com.gamerproject.gamerproject.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean login(String userid, String password) {

        Member member = memberRepository.findByUserId(userid);
        if (member != null && member.getPassword().equals(password)) {
            return true;
        }else{
            return false;
        }
    }

}
