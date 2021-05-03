package chapter06;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

public class Reducing {
    public static void main(String[] args) {

    }

    private static int calculateReducing() {
        int totalCalories = Dish.menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        return totalCalories;
    }

    private static int calculateByIntStream() {
        int totalCalories = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
        return totalCalories;
    }

    public static void groupDishesByCaloricLevel() {
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Dish.menu.stream().collect(
                groupingBy(dish -> {
                    if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));
    }

    public enum CaloricLevel {
        DIET, NORMAL, FAT
    }
}
