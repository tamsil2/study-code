package me.tamsil.designpatterns._02_structural_patterns._11_flyweight._02_after;

public class Client {
    public static void main(String[] args) {
        FontFactory fontFactory = new FontFactory();
        Charater cl = new Charater('h', "white", fontFactory.getFont("nanum:12"));
        Charater c2 = new Charater('e', "white", fontFactory.getFont("nanum:12"));
        Charater c3 = new Charater('l', "white", fontFactory.getFont("nanum:12"));
    }
}
