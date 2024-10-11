package com.gamerproject.gamerproject.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @PostMapping("/join/member")
    public String joinMember(String userid,
                             String password,
                             String displayName,
                             String email,
                             String interest) {

        Member member = new Member();
        member.setEmail(email);
        member.setDisplayName(displayName);
        member.setPassword(password);
        member.setUserId(userid);
        member.setInterest(interest);

        memberRepository.save(member);
        return "redirect:/";
    }

    @PostMapping("/login/login")
    public String login(String userid, String password) {
        if(memberService.login(userid, password)) {
            System.out.println("성공");
            return "redirect:/";
        } else {
            System.out.println("실패");
            return "redirect:/error";
        }
    }
}
