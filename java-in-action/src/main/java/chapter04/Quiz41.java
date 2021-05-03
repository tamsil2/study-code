package chapter04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Quiz41 {
    public static void main(String[] args) throws IOException {
        // As-IS
        List<String> highCaloricDishes = new ArrayList<>();
        Iterator<Dish> iterator = Dish.menu.iterator();
        while(iterator.hasNext()) {
            Dish dish = iterator.next();
            if(dish.getCalories() > 300) {
                highCaloricDishes.add(dish.getName());
            }
        }

        // Refactoring
        List<String> highCaloricDish =
                Dish.menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(dish -> dish.getName())
                .collect(toList());
    }
}
