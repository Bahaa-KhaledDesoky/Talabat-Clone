package com.example.test;


public class Meals {
    private byte[] mealimage;
    private String mealname;
    private String mealprice;
    private String mealdescription;
    private String resturantid;
    private String idMeal;

    public Meals(byte[] mealimage, String mealname, String mealprice, String mealdescription, String resturantid, String idMeal) {
        this.mealimage = mealimage;
        this.mealname = mealname;
        this.mealprice = mealprice;
        this.mealdescription = mealdescription;
        this.resturantid = resturantid;
        this.idMeal = idMeal;

    }

    public Meals(String mealname, String mealprice, String mealdescription, String resturantid, String idMeal) {
        this.mealname = mealname;
        this.mealprice = mealprice;
        this.mealdescription = mealdescription;
        this.resturantid = resturantid;
        this.idMeal = idMeal;
    }

    public byte[] getImageMeal() {
        return mealimage;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public String getMealname() {
        return mealname;
    }

    public String getMealprice() {
        return mealprice;
    }

    public String getMealdescription() {
        return mealdescription;
    }

    public String getResturantid() {
        return resturantid;
    }
}