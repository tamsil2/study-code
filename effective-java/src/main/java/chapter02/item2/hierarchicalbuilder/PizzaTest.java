package chapter02.item2.hierarchicalbuilder;


import static chapter02.item2.hierarchicalbuilder.NyPizza.Size.*;
import static chapter02.item2.hierarchicalbuilder.Pizza.Topping.*;

public class PizzaTest {
    public static void main(String[] args) {
        NyPizza pizza = (NyPizza) new NyPizza.Builder(SMALL).addTopping(SAUSAGE).addTopping(ONION).build();
        Calzone calzone = (Calzone) new Calzone.Builder().addTopping(HAM).sauceInside().build();

        System.out.println(pizza);
        System.out.println(calzone);
    }
}
