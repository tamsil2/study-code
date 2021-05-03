package chapter08.item52;

import java.util.List;

// 재정의된 메서드 호출 매커니즘
public class Overriding {
    public static void main(String[] args) {
        List<Wine> wineList = List.of(
                new Wine(), new SparklingWine(), new Champagne());

        for(Wine wine : wineList) {
            System.out.println(wine.name());
        }
    }
}
