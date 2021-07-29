package me.kobeshow.studyolle.modules.study.event;

import lombok.Getter;
import me.kobeshow.studyolle.modules.study.Study;
import org.springframework.context.ApplicationEvent;

@Getter
public class StudyCreatedEvent {

    private Study study;

    public StudyCreatedEvent(Study study) {
        this.study = study;
    }
}
