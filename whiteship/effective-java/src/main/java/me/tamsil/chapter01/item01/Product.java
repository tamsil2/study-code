package me.tamsil.chapter01.item01;

public class Product {
    public static void main(String[] args) {
        Settings settings1 = Settings.getinstance();
        Settings settings2 = Settings.getinstance();

        System.out.println(settings1);
        System.out.println(settings2);

        Boolean.valueOf(false);
    }
}
