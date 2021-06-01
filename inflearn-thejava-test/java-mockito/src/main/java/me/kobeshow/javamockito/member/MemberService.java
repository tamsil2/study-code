package me.kobeshow.javamockito.member;

import me.kobeshow.javamockito.domain.Member;
import me.kobeshow.javamockito.domain.Study;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long memberId) ;

    void validate(Long memberId);

    void notify(Study newStudy);

    void notify(Member member);
}
