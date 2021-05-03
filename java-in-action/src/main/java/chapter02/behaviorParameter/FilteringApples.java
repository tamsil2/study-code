package chapter02.behaviorParameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED));

        List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
        List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
        System.out.println(greenApples);

        List<Apple> greenApples2 = filterApples(inventory, Color.GREEN, 0, true);
        List<Apple> heavyApples = filterApples(inventory, null, 150, false);

        List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedHeavyPredicate());
    }

    public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if((flag && apple.getColor().equals(color)) ||
                    (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(Color.GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static class Apple {
        private int weight = 0;
        private Color color;

        public Apple(){};

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "weight=" + weight +
                    ", color=" + color +
                    '}';
        }
    }

    enum Color {
        RED,
        GREEN
    }

    public interface ApplePredicate {
        boolean test (Apple apple);
    }

    public static class AppleRedHeavyPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return Color.RED.equals(apple.getColor())
                    && apple.getWeight() > 150;
        }
    }
}
