package me.kobeshow.studyolle.settings.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.kobeshow.studyolle.domain.Account;

@Data
public class Notifications {

    private boolean studyCreatedByEmail;

    private boolean studyCreatedByWeb;

    private boolean studyEnrollmentResultByEmail;

    private boolean studyEnrollmentResultByWeb;

    private boolean studyUpdatedByEmail;

    private boolean studyUpdatedByWeb;

}
