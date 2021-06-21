package me.kobeshow.springbootjpashop.repository;

import me.kobeshow.springbootjpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);
}
