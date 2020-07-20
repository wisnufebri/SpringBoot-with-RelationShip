package com.wisnufebriramadhan.tugas4.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {


    @OneToOne()
    @MapsId
    @JsonIgnore
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String address;
    private String country;
    private String province;

    @Override
    public String toString() {
        return "Address{" +
                "user=" + user +
                ", id=" + id +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                '}';
    }

    public Address(int id, String address, String country, String province) {
        this.id = id;
        this.address = address;
        this.country = country;
        this.province = province;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Address() {
    }
}
