package me.kobeshow.studyolle.event;

import lombok.RequiredArgsConstructor;
import me.kobeshow.studyolle.account.CurrentAccount;
import me.kobeshow.studyolle.domain.Account;
import me.kobeshow.studyolle.domain.Study;
import me.kobeshow.studyolle.event.form.EventForm;
import me.kobeshow.studyolle.study.StudyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/study/{path}")
@RequiredArgsConstructor
public class EventController {

    private final StudyService studyService;

    @GetMapping("/new-event")
    public String newEventForm(@CurrentAccount Account account, @PathVariable String path, Model model) {
        Study study = studyService.getStudyToUpdateStatus(account, path);
        model.addAttribute(study);
        model.addAttribute(account);
        model.addAttribute(new EventForm());
        return "event/form";
    }
}
