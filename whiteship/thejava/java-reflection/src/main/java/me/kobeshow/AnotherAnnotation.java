package me.kobeshow;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
//@Inherited
public @interface AnotherAnnotation {

    String name() default "tamsil";

    int number() default 100;
}
