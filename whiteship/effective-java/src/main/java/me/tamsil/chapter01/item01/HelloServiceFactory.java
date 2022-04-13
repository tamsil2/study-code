package me.tamsil.chapter01.item01;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.ServiceLoader;

public class HelloServiceFactory {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        ServiceLoader<HelloService> loader = ServiceLoader.load(HelloService.class);
        Optional<HelloService> helloServiceOptional = loader.findFirst();
        helloServiceOptional.ifPresent(h -> {
            System.out.println(h.hello());
        });

        Class<?> aClass = Class.forName("me.tamsil.chapter01.item01.HelloService");
        Method[] declaredMethods = aClass.getDeclaredMethods();
        Annotation[] declaredAnnotations = aClass.getDeclaredAnnotations();

        Constructor<?> constructor = aClass.getConstructor();
        HelloService helloService = (HelloService) constructor.newInstance();
    }
}
