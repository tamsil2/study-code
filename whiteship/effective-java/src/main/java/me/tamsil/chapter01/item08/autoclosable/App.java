package me.tamsil.chapter01.item08.autoclosable;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try (AutoClosableIsGood good = new AutoClosableIsGood("")) {
            // TODO 자원 반납 처리가 됨.
        }
    }
}
