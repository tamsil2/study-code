package me.kobeshow.java8annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@Repeatable(value = ChickenContainer.class)
public @interface Chicken {
    String value();
}
