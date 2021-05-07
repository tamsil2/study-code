package me.kobeshow;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.TYPE, ElementType.FIELD})
//@Inherited
public @interface MyAnnotation {

    String name() default "tamsil";

    int number() default 100;
}
