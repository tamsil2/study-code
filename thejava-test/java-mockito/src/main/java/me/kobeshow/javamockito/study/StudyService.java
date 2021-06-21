package me.kobeshow.javamockito.study;

import me.kobeshow.javamockito.domain.Member;
import me.kobeshow.javamockito.domain.Study;
import me.kobeshow.javamockito.member.MemberService;

import java.util.Optional;

public class StudyService {

    private final MemberService memberService;

    private final StudyRepository repository;

    public StudyService(MemberService memberService, StudyRepository repository) {
        assert memberService != null;
        assert repository != null;
        this.memberService = memberService;
        this.repository = repository;
    }

    public Study createNewStudy(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id: '" + member.get().getId())));
        Study newStudy = repository.save(study);
        memberService.notify(newStudy);
        memberService.notify(member.get());
        return newStudy;
    }
}
