package me.tamsil.designpatterns._01_creational_patterns._03_abstract_factory._02_after;

import me.tamsil.designpatterns._01_creational_patterns._02_factory_method._02_after.DefaultShipFactory;
import me.tamsil.designpatterns._01_creational_patterns._02_factory_method._02_after.Ship;
import me.tamsil.designpatterns._01_creational_patterns._02_factory_method._02_after.WhiteShip;
import me.tamsil.designpatterns._01_creational_patterns._03_abstract_factory._01_before.WhiteAnchor;
import me.tamsil.designpatterns._01_creational_patterns._03_abstract_factory._01_before.WhiteWheel;

public class WhiteshipFactory extends DefaultShipFactory {

    private ShipPartsFactory shipPartsFactory;

    public WhiteshipFactory(ShipPartsFactory shipPartsFactory) {
        this.shipPartsFactory = shipPartsFactory;
    }

    @Override
    public Ship createShip() {
        Ship ship = new WhiteShip();
        ship.setAnchor(shipPartsFactory.createAnchor());
        ship.setWheel(shipPartsFactory.createWheel());
        return ship;
    }
}
