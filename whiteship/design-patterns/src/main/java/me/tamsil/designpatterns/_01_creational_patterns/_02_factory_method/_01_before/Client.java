package me.tamsil.designpatterns._01_creational_patterns._02_factory_method._01_before;

public class Client {
    public static void main(String[] args) {
        Ship whiteShip = ShipFactory.orderShip("Whiteship", "tamsil2@email.com");
        System.out.println(whiteShip);

        Ship blackShip = ShipFactory.orderShip("BlackShip", "tamsil2@email.com");
        System.out.println(blackShip);
    }
}
