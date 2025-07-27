package com.example.test;

public class User {
    private  String email ;
    private  String password ;
    private   String address ;
    private   String phone ;
    private   String name ;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email,String name ,String password, String address, String phone ) {
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
