package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CustomerHome extends AppCompatActivity {
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
        if (getIntent().hasExtra("USER_ID")) {
            id = getIntent().getStringExtra("USER_ID");
        }
    }

    public void movetoSearch(View view) {
        Intent intent=new Intent(CustomerHome.this,SearchForResturants_random.class);
        intent.putExtra("USER_ID",id);
        startActivity(intent);
    }

    public void movetocart(View view) {
        Intent intent=new Intent(CustomerHome.this,Cart.class);
        intent.putExtra("USER_ID",id);
        startActivity(intent);
    }
}

//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//import data.mealConstant;
//
//public class CustomerHome extends AppCompatActivity {
//    Customer customer ;
//    RecyclerView recyclerView =null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_customer_home);
//        String[] projection=new String[]{mealConstant.MEAL_ID,mealConstant.MEAL_NAME,mealConstant.MEAL_PRICE,mealConstant.MEAL_DESCRIBE,mealConstant.MEAL_RESTURANT_ID};
//        Cursor cursor =  getContentResolver().query(data.mealConstant.MEAL_URI,projection,null,null,null);
////        ArrayList<Meals> meal =new ArrayList<>();
////        if(cursor.moveToFirst()) {
////            Toast.makeText(CustomerHome.this,"adaptre 3rs",Toast.LENGTH_LONG).show();
////            do{
////                meal.add(new Meals(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
////            }while (cursor.moveToNext());
////        }
//        itemDapter item = new itemDapter(CustomerHome.this,cursor);
//        recyclerView=findViewById(R.id.meals);
//        recyclerView.setAdapter(item);
//        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
//        Toast.makeText(CustomerHome.this,""+item.getItemCount(),Toast.LENGTH_LONG).show();
//        recyclerView.setLayoutManager(linearLayoutManager);
//   }
//
//
//}