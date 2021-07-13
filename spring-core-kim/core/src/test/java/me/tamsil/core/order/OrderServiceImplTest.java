package me.tamsil.core.order;

import me.tamsil.core.discount.FixDiscountPolicy;
import me.tamsil.core.member.Grade;
import me.tamsil.core.member.Member;
import me.tamsil.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "iteamA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
