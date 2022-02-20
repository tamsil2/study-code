package me.tamsil.designpatterns._01_creational_patterns._01_singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionApp {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Settings4 settings4 = Settings4.getInstance();

        Constructor<Settings4> constructor = Settings4.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Settings4 settings41 = constructor.newInstance();

        System.out.println(settings4 == settings41);
    }
}
