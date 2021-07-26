package me.kobeshow.studyolle.modules.event;

import lombok.RequiredArgsConstructor;
import me.kobeshow.studyolle.modules.account.WithAccount;
import me.kobeshow.studyolle.modules.account.AccountRepository;
import me.kobeshow.studyolle.modules.study.StudyRepository;
import me.kobeshow.studyolle.modules.study.StudyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StudyService studyService;
    @Autowired
    private StudyRepository studyRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Test
    @DisplayName("선착순 모임에게 참가 신청 - 자동 수락")
    @WithAccount("tamsil")
    void newEnrollment_to_FEFS_event_accepted() throws Exception{

    }
}
