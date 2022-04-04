package me.tamsil.designpatterns._02_structural_patterns._11_flyweight._02_after;

public class Charater {
    private char value;
    private String color;
    private Font font;

    public Charater(char value, String color, Font font) {
        this.value = value;
        this.color = color;
        this.font = font;
    }
}
