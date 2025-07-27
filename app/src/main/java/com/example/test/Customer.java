package com.example.test;

public class Customer extends User {


    private   String id ;
    public Customer(String email, String name, String password, String address, String phone) {
        super(email, name, password, address, phone);
    }

    public Customer(String email, String name, String password, String address, String phone, String id) {
        super(email, name, password, address, phone);
        this.id = id;
    }



}
