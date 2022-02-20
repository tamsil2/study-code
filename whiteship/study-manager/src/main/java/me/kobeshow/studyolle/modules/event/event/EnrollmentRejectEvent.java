package me.kobeshow.studyolle.modules.event.event;

import me.kobeshow.studyolle.modules.event.Enrollment;

public class EnrollmentRejectEvent extends EnrollmentEvent{

    public EnrollmentRejectEvent(Enrollment enrollment) {
        super(enrollment, "모임 참가 신청을 거절했습니다.");
    }
}
