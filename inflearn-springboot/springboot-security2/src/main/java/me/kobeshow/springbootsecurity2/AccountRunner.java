package me.kobeshow.springbootsecurity2;

import me.kobeshow.springbootsecurity2.account.Account;
import me.kobeshow.springbootsecurity2.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account tamsil = accountService.createAccount("tamsil", "1234");
        System.out.println(tamsil.getUsername() + " password: " + tamsil.getPassword());
    }
}
