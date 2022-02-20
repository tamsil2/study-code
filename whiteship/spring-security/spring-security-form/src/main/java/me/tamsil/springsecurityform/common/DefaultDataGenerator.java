package me.tamsil.springsecurityform.common;

import me.tamsil.springsecurityform.account.Account;
import me.tamsil.springsecurityform.account.AccountService;
import me.tamsil.springsecurityform.book.Book;
import me.tamsil.springsecurityform.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class DefaultDataGenerator implements ApplicationRunner {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account tamsil = createUser("tamsil");
        Account hong = createUser("hong");
        createBook("spring", tamsil);
        createBook("hibernate", hong);
    }

    private void createBook(String title, Account account) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(account);
        bookRepository.save(book);
    }

    private Account createUser(String username) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword("123");
        account.setRole("USER");
        return accountService.createNew(account);
    }
}
