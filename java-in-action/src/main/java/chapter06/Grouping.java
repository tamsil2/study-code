package chapter06;

import java.util.*;

import static java.util.stream.Collectors.*;

public class Grouping {
    public enum CaloricLevel { DIET, NORMAL, FAT }

    public static void main(String[] args) {
        System.out.println("Dish group by type : " + dishedByType());
        System.out.println("Dish group by caloric level : " + groupDishesByCaloricLevel());
        System.out.println("Dish group by caloric dishes : " + groupCaloricDishesByType1());
        System.out.println("Dish group by caloric dished2 : " + groupCaloricDishesByType2());
        System.out.println("Dish group by type name " + groupDishNamesByType());
        System.out.println("Dish group by names by type " + dishNamesByType2());
        System.out.println("Dish group by dished by type caloriclevel : " + dishesByTypeCaloricLevel());
    }

    private static Map<Dish.Type, List<Dish>> dishedByType() {
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream().collect(groupingBy(Dish::getType));
        return dishesByType;
    }

    private static Map<Dish.Type, Set<String>> dishNamesByType2() {
        Map<Dish.Type, Set<String>> dishNamesByType2 =
                Dish.menu.stream()
                .collect(groupingBy(Dish::getType, flatMapping(dish -> Dish.dishTags.get(dish.getName()).stream(), toSet())));
        return dishNamesByType2;
    }

    private static Map<Dish.Type, List<String>> groupDishNamesByType() {
        Map<Dish.Type, List<String>> dishNamesByType =
                Dish.menu.stream()
                .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
        return dishNamesByType;
    }

    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Dish.menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));
        return dishesByCaloricLevel;
    }

    public static Map<Dish.Type, List<Dish>> groupCaloricDishesByType1() {
        Map<Dish.Type, List<Dish>> caloricDishedByType =
                Dish.menu.stream().filter(dish -> dish.getCalories() > 500)
                .collect(groupingBy(Dish::getType));
        return caloricDishedByType;
    }

    public static Map<Dish.Type, List<Dish>> groupCaloricDishesByType2() {
        Map<Dish.Type, List<Dish>> caloricDishesByType =
                Dish.menu.stream()
                .collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
        return caloricDishesByType;
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                Dish.menu.stream().collect(
                        groupingBy(Dish::getType,
                                groupingBy(dish -> {
                                    if(dish.getCalories() <= 400)
                                        return CaloricLevel.DIET;
                                    else if(dish.getCalories() <= 700)
                                        return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                }))
                );
        return dishesByTypeCaloricLevel;
    }

    private static Map<Dish.Type, Long> typesCount() {
        Map<Dish.Type, Long> typesCount = Dish.menu.stream().collect(
                groupingBy(Dish::getType, counting())
        );
        return typesCount;
    }

    private static Map<Dish.Type, Optional<Dish>> mostCaloricByType() {
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                Dish.menu.stream()
                .collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
        return mostCaloricByType;
    }

    private static Map<Dish.Type, Dish> mostCaloricByType2() {
        Map<Dish.Type, Dish> mostCaloricByType =
                Dish.menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                        )));
        return mostCaloricByType;
    }

    private static Map<Dish.Type, Integer> totalCaloriesByType() {
        Map<Dish.Type, Integer> totalCaloriesByType =
                Dish.menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        return totalCaloriesByType;
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelByType =
                Dish.menu.stream().collect(
                        groupingBy(Dish::getType, mapping(dish -> {
                            if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        }, toSet()))
                );
        return caloricLevelByType;
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType2() {
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = Dish.menu.stream().collect(
                groupingBy(Dish::getType, mapping(dish -> {
                    if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }, toCollection(HashSet::new)))
        );
        return caloricLevelsByType;
    }
}
