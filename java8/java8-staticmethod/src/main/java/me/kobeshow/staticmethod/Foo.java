package me.kobeshow.staticmethod;

import java.util.Locale;

public interface Foo {

    void printName();

    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase(Locale.ROOT));
    }

    static void printAnything() {
        System.out.println("FOO");
    }

    String getName();
}
