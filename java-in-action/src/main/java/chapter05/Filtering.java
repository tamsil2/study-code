package chapter05;

import chapter04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Filtering {
    public static void main(String[] args) {
        List<Dish> specialMenu = Arrays.asList(
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));

        // As-IS
        List<Dish> filteredMenu = specialMenu.stream()
                .filter(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());

        // takeWhile
        List<Dish> slicedMenu1 = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());

        // dropWhile
        List<Dish> slicedMenu2 = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());

        // limit
        List<Dish> dishes = specialMenu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());

        // skip
        List<Dish> dishes2 = specialMenu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
    }
}
