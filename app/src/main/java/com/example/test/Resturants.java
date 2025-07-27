package com.example.test;

public class Resturants {
    String id;
    String name;
    String address;
    String descreption;
    String opening_hours;
    String number;
    private byte[] mealimage;

    public Resturants(String id, String name, String address, String descreption, String opening_hours, String number) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.descreption = descreption;
        this.opening_hours = opening_hours;
        this.number = number;
    }
    public Resturants(byte[] mealimage,String id, String name, String address, String descreption, String opening_hours, String number) {
        this.mealimage=mealimage;
        this.id = id;
        this.name = name;
        this.address = address;
        this.descreption = descreption;
        this.opening_hours = opening_hours;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public byte[] getMealimage() {
        return mealimage;
    }

    public String getName() { return name; }

    public String getAddress() {
        return address;
    }

    public String getDescreption() {
        return descreption;
    }

    public String getOpening_hours() {
        return opening_hours;
    }

    public String getNumber() {
        return number;
    }

}

