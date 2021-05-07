package me.kobeshow.javaproxy;

public class BookServiceClass {

    BookRepository bookRepository;

    public void rent(Book book) {
        System.out.println("rent : " + book.getTitle());
    }

    public void returnBook(Book book) {
        System.out.println("returnBook : " + book.getTitle());
    }
}
