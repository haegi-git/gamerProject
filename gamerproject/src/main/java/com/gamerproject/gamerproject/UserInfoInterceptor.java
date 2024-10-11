package com.gamerproject.gamerproject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gamerproject.gamerproject.member.Member; // Member 클래스 임포트

@Component
public class UserInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member"); // 세션에서 Member 객체 가져오기
        request.setAttribute("member", member); // 사용자 정보를 request에 설정
        return true; // 요청을 계속 진행하도록 허용
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            modelAndView.addObject("member", request.getAttribute("member")); // 사용자 정보를 모델에 추가
        }
    }
}
