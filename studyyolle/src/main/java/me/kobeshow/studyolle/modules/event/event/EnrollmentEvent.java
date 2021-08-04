package me.kobeshow.studyolle.modules.event.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.kobeshow.studyolle.modules.event.Enrollment;

@Getter
@RequiredArgsConstructor
public class EnrollmentEvent {

    protected final Enrollment enrollment;

    protected final String message;
}
