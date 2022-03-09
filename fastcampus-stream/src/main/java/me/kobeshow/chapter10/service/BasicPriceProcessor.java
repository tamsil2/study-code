package me.kobeshow.chapter10.service;

import me.kobeshow.chapter10.model.Price;

public class BasicPriceProcessor implements PriceProcessor {

    @Override
    public Price process(Price price) {
        return price;
    }
}
