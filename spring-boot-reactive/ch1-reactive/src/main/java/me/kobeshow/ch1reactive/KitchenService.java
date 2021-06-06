package me.kobeshow.ch1reactive;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class KitchenService {

    Flux<Dish> getDishes() {
        return Flux.just(
                new Dish("Sesame chicken"),
                new Dish("Lo mein noodles, plain"),
                new Dish("Sweet & sour beef")
        );
    }
}
