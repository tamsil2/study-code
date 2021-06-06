package me.kobeshow.ch1reactive;

import reactor.core.publisher.Flux;

public class SImpleServer {
    private final KitchenService kitchen;

    SImpleServer(KitchenService kitchen) {
        this.kitchen = kitchen;
    }

    Flux<Dish> doingMyJob() {
        return this.kitchen.getDishes()
                .map(dish -> Dish.deliver(dish));
    }
}
