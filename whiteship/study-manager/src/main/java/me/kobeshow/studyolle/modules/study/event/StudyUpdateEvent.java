package me.kobeshow.studyolle.modules.study.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.kobeshow.studyolle.modules.study.Study;
import org.springframework.context.ApplicationEvent;

@Getter
@RequiredArgsConstructor
public class StudyUpdateEvent {

    private final Study study;

    private final String message;
}
