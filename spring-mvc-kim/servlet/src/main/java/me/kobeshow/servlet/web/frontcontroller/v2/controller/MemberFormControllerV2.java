package me.kobeshow.servlet.web.frontcontroller.v2.controller;

import me.kobeshow.servlet.web.frontcontroller.MyView;
import me.kobeshow.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) {
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
