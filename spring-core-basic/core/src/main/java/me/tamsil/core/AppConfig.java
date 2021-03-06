package me.tamsil.core;

import me.tamsil.core.discount.DiscountPolicy;
import me.tamsil.core.discount.RateDiscountPolicy;
import me.tamsil.core.member.MemberService;
import me.tamsil.core.member.MemberServiceImpl;
import me.tamsil.core.member.MemoryMemberRepository;
import me.tamsil.core.order.OrderService;
import me.tamsil.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
