package me.kobeshow.springbootjpashop.repository;

import lombok.Data;
import me.kobeshow.springbootjpashop.domain.Address;
import me.kobeshow.springbootjpashop.domain.OrderStatus;

import java.time.LocalDateTime;

@Data
public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name; // LAZY 초기화
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address; // LAZY 초기화
    }
}
