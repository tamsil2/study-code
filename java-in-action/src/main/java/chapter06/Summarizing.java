package chapter06;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


public class Summarizing {
    public static void main(String[] args) {
//        System.out.println(calculateTotalCalories());
        calculateSummarizing();
    }

    private static long howManyDishes() {
        long howManyDishes = Dish.menu.stream().collect(Collectors.counting());
        return howManyDishes;
    }

    private static void findMostCaloricDishUsingComparator() {
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(maxBy(dishCaloriesComparator));
    }

    private static int calculateTotalCalories() {
        int totalCalories = Dish.menu.stream().collect(summingInt(Dish::getCalories));
        return totalCalories;
    }

    private static double calculateAvgcalories() {
        double avgCalories = Dish.menu.stream().collect(averagingInt(Dish::getCalories));
        return avgCalories;
    }

    private static void calculateSummarizing() {
        IntSummaryStatistics menuStatistics = Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);
    }

    private static String shortMenu() {
        String shortMenu = Dish.menu.stream().map(Dish::getName).collect(joining(", "));
        return shortMenu;
    }
}
