package chapter05;

import chapter04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static chapter04.Dish.menu;

public class Finding {
    public static void main(String[] args) {
        if(menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");

            // allMatch
            boolean isHealthy = menu.stream().allMatch(dish -> dish.getCalories() < 1000);

            // NoneMatch
            boolean isHealthy2 = menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);

            // findAny
            Optional<Dish> dish = menu.stream()
                    .filter(Dish::isVegetarian)
                    .findAny();

            // findFirst
            List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
            Optional<Integer> firstSquareDivisibleByThree =
                    someNumbers.stream()
                    .map(n -> n * n)
                    .filter(n -> n % 3 == 0)
                    .findFirst();
        }
    }
}
