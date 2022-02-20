package me.kobeshow.staticmethod;

import java.util.Locale;

public class DefaultFoo implements Foo {

    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public void printNameUpperCase() {
        System.out.println(this.name.toUpperCase(Locale.ROOT));
    }

    @Override
    public String getName() {
        return this.name;
    }
}
