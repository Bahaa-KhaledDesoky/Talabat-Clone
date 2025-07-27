package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import data.mealConstant;
import data.resturantConstant;

public class SearchinMeals extends AppCompatActivity {
    String id,namesearch,idcustomer;
    TextView textView;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchin_meals);
        if (getIntent().hasExtra("USER_ID")) {
            idcustomer = getIntent().getStringExtra("USER_ID");
        }
        textView=findViewById(R.id.noo);
        if (getIntent().hasExtra(resturantConstant.RESTURANT_ID)&&getIntent().hasExtra(mealConstant.MEAL_NAME)) {
            id = getIntent().getStringExtra(resturantConstant.RESTURANT_ID);
            namesearch = getIntent().getStringExtra(mealConstant.MEAL_NAME);
        }
        String selectionmeal = mealConstant.MEAL_RESTURANT_ID+"=?"+" AND "+mealConstant.MEAL_NAME+"=?";
        String[] selectionArgs=new String[]{id,namesearch};
        String[] projection=new String[]{mealConstant.MEAL_ID,mealConstant.MEAL_NAME,mealConstant.MEAL_PRICE,mealConstant.MEAL_DESCRIBE,mealConstant.MEAL_RESTURANT_ID,mealConstant.MEAL_IMAGE
        };
        Cursor cursor =  getContentResolver().query(data.mealConstant.MEAL_URI,projection,selectionmeal,selectionArgs,null);
        itemDapter item = new itemDapter (SearchinMeals.this,cursor,idcustomer);
        recyclerView=findViewById(R.id.search_resultt);
        recyclerView.setAdapter(item);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        Toast.makeText(SearchinMeals.this,""+item.getItemCount(),Toast.LENGTH_LONG).show();
        recyclerView.setLayoutManager(linearLayoutManager);
        Toast.makeText(this,""+item.getItemCount(),Toast.LENGTH_LONG).show();
        if (item.getItemCount()==0){
            textView.setText("Not Found");
        }
    }

}

