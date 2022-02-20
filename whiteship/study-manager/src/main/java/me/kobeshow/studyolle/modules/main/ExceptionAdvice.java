package me.kobeshow.studyolle.modules.main;

import lombok.extern.slf4j.Slf4j;
import me.kobeshow.studyolle.modules.account.Account;
import me.kobeshow.studyolle.modules.account.CurrentAccount;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public String handleRuntimeException(@CurrentAccount Account account, HttpServletRequest req, RuntimeException e) {
        if (account != null) {
            log.info("'{}' requested '{}'", account.getNickname(), req.getRequestURI());
        } else {
            log.info("requested '{}'", req.getRequestURI());
        }
        log.error("bad request", e);
        return "error";
    }
}
