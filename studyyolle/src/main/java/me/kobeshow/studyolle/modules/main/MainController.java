package me.kobeshow.studyolle.modules.main;

import lombok.RequiredArgsConstructor;
import me.kobeshow.studyolle.modules.account.CurrentAccount;
import me.kobeshow.studyolle.modules.account.Account;
import me.kobeshow.studyolle.modules.notification.NotificationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(@CurrentAccount Account account, Model model) {
        if (account != null) {
            model.addAttribute(account);
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
