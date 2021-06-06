package me.kobeshow.ch1reactive;

public class DishMetaphor {

    static class PoliteRestaurant {
        public static void main(String[] args) {
            PoliteServer server = new PoliteServer(new KitchenService());

            server.doingMyJob().subscribe(
                    dish -> System.out.println("Consuming " + dish),
                    throwable -> System.err.println(throwable)
            );
        }
    }
}
