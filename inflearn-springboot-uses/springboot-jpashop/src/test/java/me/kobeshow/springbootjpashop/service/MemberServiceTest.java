package me.kobeshow.springbootjpashop.service;

import me.kobeshow.springbootjpashop.domain.Member;
import me.kobeshow.springbootjpashop.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("회원가입")
    @Test
    public void test01() throws Exception {
        // given
        Member member = new Member();
        member.setName("hong");

        // when
        Long savedId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findById(savedId).get());
    }

    @DisplayName("중복 회원 예외")
    @Test
    public void test02() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("hong");

        Member member2 = new Member();
        member2.setName("hong");

        // when
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member1);
            memberService.join(member2);
        });
    }
}
