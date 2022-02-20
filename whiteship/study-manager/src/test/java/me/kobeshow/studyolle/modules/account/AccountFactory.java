package me.kobeshow.studyolle.modules.account;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountFactory {

    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(String nickName) {
        Account tamsil = new Account();
        tamsil.setNickname(nickName);
        tamsil.setEmail(nickName + "@email.com");
        accountRepository.save(tamsil);
        return tamsil;
    }
}
