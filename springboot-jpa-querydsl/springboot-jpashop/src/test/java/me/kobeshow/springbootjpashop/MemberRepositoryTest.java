package me.kobeshow.springbootjpashop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

//    @Autowired
//    MemberRepositoryOld memberRepository;
//
//    @Test
//    @Transactional
//    public void testMember() throws Exception {
//        // given
//        Member member = new Member();
//        member.setUsername("memberA");
//
//        // when
//        Long savedId = memberRepository.save(member);
//        Member findMember = memberRepository.find(savedId);
//
//        // then
//        assertEquals(findMember.getId(), member.getId());
//        assertEquals(findMember.getUsername(), member.getUsername());
//        assertEquals(findMember, member);
//    }

}
