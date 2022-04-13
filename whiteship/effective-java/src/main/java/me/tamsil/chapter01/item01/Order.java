package me.tamsil.chapter01.item01;

import java.util.Arrays;

public class Order {
    private boolean price;
    private boolean urgent;

    private Product product;

//    public Order(Product product, boolean price) {
//        this.product = product;
//        this.price = price;
//    }
//
//    public Order(boolean urgent, Product product) {
//        this.product = product;
//        this.urgent = urgent;
//    }

    private OrderStatus orderStatus;

    public static Order primeOrder(Product product) {
        Order order = new Order();
        order.price = true;
        order.product = product;
        return order;
    }

    public static Order urgentOrder(Product product) {
        Order order = new Order();
        order.urgent = true;
        order.product = product;
        return order;
    }

    public static void main(String[] args) {
        Order order = new Order();
        if (order.orderStatus == OrderStatus.DELIVERED) {
            System.out.println("delivered");
        }

        Arrays.stream(OrderStatus.values()).forEach(System.out::println);
    }
}
