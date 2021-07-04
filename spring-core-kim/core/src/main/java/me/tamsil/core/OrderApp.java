package me.tamsil.core;

import me.tamsil.core.member.Grade;
import me.tamsil.core.member.Member;
import me.tamsil.core.member.MemberService;
import me.tamsil.core.order.Order;
import me.tamsil.core.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order);
    }
}
