package chapter04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class StreamBasic {
    public static void main(String[] args) {
        // 자바 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        // 자바 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

        // 자바 8 Parallel
        getLowCaloricDishesNamesInJava8Parallel(Dish.menu).forEach(System.out::println);

        getTop3HighCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish dish : dishes) {
            if(dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        List<String> lowCaloricDishesName = new ArrayList<>();
        for(Dish dish : lowCaloricDishes) {
            lowCaloricDishesName.add(dish.getName());
        }
        return lowCaloricDishesName;
    }

    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        List<String> lowCaloricDishesName =
                dishes.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());

        return lowCaloricDishesName;
    }

    public static List<String> getLowCaloricDishesNamesInJava8Parallel(List<Dish> dishes) {
        List<String> lowCaloricDishesName =
                dishes.parallelStream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());

        return lowCaloricDishesName;
    }

    public static List<String> getTop3HighCaloricDishesNamesInJava8(List<Dish> dishes) {
        List<String> highCaloricDishesName =
                dishes.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        return highCaloricDishesName;
    }
}
