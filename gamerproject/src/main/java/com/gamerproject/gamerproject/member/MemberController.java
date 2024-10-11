package com.gamerproject.gamerproject.member;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "join.html";
    }

    @PostMapping("/join/member")
    public String joinMember(@Valid MemberDto memberDto,
                             BindingResult bindingResult,
                             String interest) {
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "/join";
        }

        memberService.memberJoin(memberDto,interest);
        return "redirect:/";
    }

    @PostMapping("/login/login")
    public String login(@Valid MemberLoginDto memberLoginDto,
                        BindingResult bindingResult,
                        Model model,
                        HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("memberDto", new MemberDto());
            System.out.println("유효성 에러"+bindingResult.getAllErrors());
            return "redirect:/"; // 로그인 페이지로 포워드
        }else if(memberService.login(memberLoginDto)){
            Member member = memberRepository.findByUserId(memberLoginDto.getUserid()); // DB에서 유저 정보 조회
            session.setAttribute("member", member); // 유저 정보를 세션에 저장
            return "redirect:/";
        }
        System.out.println("로그인 실패한공간*유효성말고");
        model.addAttribute("errorMessage", "아이디 또는 비밀번호가 틀렸습니다.");
        return "index.html";
    }

}
