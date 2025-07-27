package com.example.test;

public class Order {
   private String Order_idCustomer;
   private String Order_idResturant ;
   private String Order_idMeal;
   private String Order_date;
   private String Order_comment;
   private String Order_name;
   private String Order_price;
   private String Order_numofMeals;
   private String Order_flag;
   private String Order_id;
   private byte[] Orderimage;



    public Order(byte[] orderimage,String Order_idCustomer,String order_comment,String order_name,String order_price,String order_numofMeals, String Order_idResturant , String Order_idMeal, String order_flag,String order_id) {
        Orderimage= orderimage;
        this.Order_idCustomer = Order_idCustomer;
        Order_comment = order_comment;
        Order_name = order_name;
        Order_price = order_price;
        Order_numofMeals = order_numofMeals;
        this.Order_idResturant  = Order_idResturant ;
        this.Order_idMeal = Order_idMeal;
        Order_flag = order_flag;
        Order_id = order_id;
    }
    public Order(String order_date,byte[] orderimage,String Order_idCustomer,String order_comment,String order_name,String order_price,String order_numofMeals, String Order_idResturant , String Order_idMeal, String order_flag,String order_id) {
        Order_date=order_date;
        Orderimage= orderimage;
        this.Order_idCustomer = Order_idCustomer;
        Order_comment = order_comment;
        Order_name = order_name;
        Order_price = order_price;
        Order_numofMeals = order_numofMeals;
        this.Order_idResturant  = Order_idResturant ;
        this.Order_idMeal = Order_idMeal;
        Order_flag = order_flag;
        Order_id = order_id;
    }
    
    public String getOrder_id() {
        return Order_id;
    }
    public String getOrder_date() {
        return Order_date;
    }
    public byte[] get_orderimage() {
        return Orderimage;
    }
    public String getOrder_comment() {
        return Order_comment;
    }
    public String getOrder_name() {
        return Order_name;
    }
    public String getOrder_idc() {
        return Order_idCustomer;
    }
    public String getOrder_price() {
        return Order_price;
    }
    public String getOrder_numofMeals() {
        return Order_numofMeals;
    }
    public String getOrder_idr() {
        return Order_idResturant ;
    }
    public String getOrder_idm() {
        return Order_idMeal;
    }
    public String getOrder_flag() {
        return Order_flag;
    }

}

