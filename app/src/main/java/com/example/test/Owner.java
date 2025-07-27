package com.example.test;

public class Owner extends User {

    public  String idR ;

    public Owner(String email, String name, String password, String address, String phone, String idR) {
        super(email, name, password, address, phone);
        this.idR = idR;
    }

    public Owner(String email, String name, String password, String address, String phone) {
        super(email, name, password, address, phone);
    }
}
