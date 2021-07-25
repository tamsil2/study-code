package me.kobeshow.servlet.web.frontcontroller.v2.controller;

import me.kobeshow.servlet.domain.member.Member;
import me.kobeshow.servlet.domain.member.MemberRepository;
import me.kobeshow.servlet.web.frontcontroller.MyView;
import me.kobeshow.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) {
        List<Member> members = memberRepository.findAll();
        request.setAttribute("members", members);
        return new MyView("/WEB-INF/views/members.jsp");
    }
}
