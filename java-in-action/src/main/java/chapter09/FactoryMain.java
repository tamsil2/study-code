package chapter09;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FactoryMain {
    public static void main(String[] args) {
        Product p1 = ProductFactory.createProduct("loan");
        System.out.printf("p1: %s%n", p1.getClass().getSimpleName());

        Supplier<Product> loanSupplier = Loan::new;
        Loan loan = (Loan) loanSupplier.get();
    }

    private static class ProductFactory {
        public static Product createProduct(String name) {
            switch (name) {
                case "loan":
                    return new Loan();
                case "stock":
                    return new Stock();
                case "bond":
                    return new Bond();
                default:
                    throw new RuntimeException("No such product " + name);
            }
        }

        public static Product createProductLambda(String name) {
            Supplier<Product> p = map.get(name);
            if(p != null) return p.get();
            throw new IllegalArgumentException("No such product " + name);
        }
    }

    private static interface Product {}
    private static class Loan implements Product {}
    private static class Stock implements Product {}
    private static class Bond implements Product {}

    final static Map<String, Supplier<Product>> map = new HashMap<>();
    static  {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }
}
