package me.kobeshow.java8optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        boolean present = optional.isPresent();
        System.out.println(present);

        OnlineClass onlineClass = optional.get();
        System.out.println(onlineClass.getTitle());

        optional.ifPresent(oc -> System.out.println(oc.getTitle()));

        OnlineClass onlineClass1 = optional.orElse(createNewClasses());
        System.out.println(onlineClass1.getTitle());

        OnlineClass onlineClass2 = optional.orElseGet(App::createNewClasses);
        System.out.println(onlineClass2.getTitle());

        OnlineClass onlineClass3 = optional.orElseThrow(() -> {
            return new IllegalStateException();
        });

        Optional<OnlineClass> onlineClass4 = optional.filter(oc -> oc.isClosed());
        System.out.println(onlineClass4);

        Optional<Integer> integer = optional.map(oc -> oc.getId());
        System.out.println(integer.isPresent());

        Optional<Progress> progress = optional.flatMap(OnlineClass::getProgress);

    }

    private static OnlineClass createNewClasses() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "New Class", false);
    }
}
