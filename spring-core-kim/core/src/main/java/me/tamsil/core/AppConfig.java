package me.tamsil.core;

import me.tamsil.core.discount.DiscountPolicy;
import me.tamsil.core.discount.FixDiscountPolicy;
import me.tamsil.core.discount.RateDiscountPolicy;
import me.tamsil.core.member.MemberService;
import me.tamsil.core.member.MemberServiceImpl;
import me.tamsil.core.member.MemoryMemberRepository;
import me.tamsil.core.order.OrderService;
import me.tamsil.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
