package me.tamsil.designpatterns._01_creational_patterns._02_factory_method._02_after;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        client.print(new WhiteshipFactory(), "whiteship", "tamsil@mail.com");
        client.print(new BlackShipFactory(), "blackship", "tamsil@mail.com");

//        Ship whiteShip = new WhiteshipFactory().orderShip("Whitewhip", "tamsil2@email.com");
//        System.out.println(whiteShip);
//
//        Ship blackShip = new BlackShipFactory().orderShip("BlackShip", "tamsil2@email.com");
//        System.out.println(blackShip);
    }

    private void print(ShipFactory shipFactory, String name, String email) {
        System.out.println(shipFactory.orderShip(name, email));
    }
}
