package me.kobeshow.studyolle.modules.study;

import lombok.RequiredArgsConstructor;
import me.kobeshow.studyolle.modules.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudyFactory {

    @Autowired
    StudyService studyService;

    @Autowired
    StudyRepository studyRepository;

    public Study createdStudy(String path, Account manager) {
        Study study = new Study();
        study.setPath(path);
        studyService.createNewStudy(study, manager);
        return study;
    }
}
