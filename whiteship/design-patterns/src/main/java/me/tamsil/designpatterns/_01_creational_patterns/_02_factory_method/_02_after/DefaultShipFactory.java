package me.tamsil.designpatterns._01_creational_patterns._02_factory_method._02_after;

public abstract class DefaultShipFactory implements ShipFactory{

    public void sendEmailTo(String email, Ship ship) {
        System.out.println(ship.getName() + " 다 만들었습니다.");
    }
}
