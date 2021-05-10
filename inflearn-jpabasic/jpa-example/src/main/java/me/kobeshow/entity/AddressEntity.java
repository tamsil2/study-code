package me.kobeshow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AddressEntity {

    public AddressEntity() {
    }

    public AddressEntity(Long id, Address address) {
        this.id = id;
        this.address = address;
    }

    @Id @GeneratedValue
    private Long id;

    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
