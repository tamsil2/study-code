package chapter06;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Partitioning {

    public static void main(String[] args) {
        System.out.println("partitioned menu is " + partitionedMenu());
        System.out.println("vegetariandishes : " + vegetariadishes());
        System.out.println("vegetarian dishes by type : " + vegetarianDishesByType());
        System.out.println("most caloric partitioned by vegetarian : " + mostCaloricPartitionedByVegetarian());
    }

    private static Map<Boolean, List<Dish>> partitionedMenu() {
        Map<Boolean, List<Dish>> partitionedMenu = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
        return partitionedMenu;
    }

    private static List<Dish> vegetariadishes() {
        List<Dish> vegetarianDishes = Dish.menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        return vegetarianDishes;
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = Dish.menu.stream()
                .collect(
                        partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType))
                );
        return vegetarianDishesByType;
    }

    private static Map<Boolean, Dish> mostCaloricPartitionedByVegetarian() {
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                Dish.menu.stream().collect(
                        partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get))
                );
        return mostCaloricPartitionedByVegetarian;
    }
}
