package me.kobeshow.springbootproject.pointcut;

public interface TargetInterface {
    void hello();
    void hello(String a);
    int minus(int a, int b) throws RuntimeException;
    int plus(int a, int b);
}
