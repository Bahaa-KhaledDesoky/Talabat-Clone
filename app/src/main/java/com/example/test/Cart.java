package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import data.customerConstants;
import data.mealConstant;
import data.orderConstant;
import data.resturantConstant;

public class Cart extends AppCompatActivity {
    String id,idcustomer;
    TextView num;
    RecyclerView recyclerView;
    Cursor cursor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        num=findViewById(R.id.numberofitems);
        if (getIntent().hasExtra("USER_ID")) {
            idcustomer = getIntent().getStringExtra("USER_ID");
        }

        String[] selectionArgs=new String[]{idcustomer,orderConstant.ORDER_INCART};
        /////////////////////////// meal data
        String selectionmeal = orderConstant.ORDER_IDC+"=?"+" AND "+orderConstant.ORDER_FLAG+"=?";
        String[] projection=new String[]{orderConstant.ORDER_IDC,orderConstant.ORDER_COMMENT,orderConstant.ORDER_NAME,orderConstant.ORDER_PRICE,orderConstant.ORDER_NUMOFMEALS,orderConstant.ORDER_IDR,orderConstant.ORDER_IDM,orderConstant.ORDER_FLAG,orderConstant.ORDER_ID,orderConstant.ORDER_IMAGE};
        cursor = getContentResolver().query(orderConstant.ORDER_URI,projection,selectionmeal,selectionArgs,null);
        cartDapter item = new cartDapter (Cart.this,cursor);
        recyclerView=findViewById(R.id.mealsadded);
        recyclerView.setAdapter(item);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        Toast.makeText(Cart.this,""+item.getItemCount(),Toast.LENGTH_LONG).show();
        recyclerView.setLayoutManager(linearLayoutManager);
        num.setText(item.getItemCount()+"");
    }

    public void orderAll(View view) {

        if(cursor.moveToFirst()) {
            do{
                String[] selectionArgs=new String[]{cursor.getString(8)};
                /////////////////////////// meal data
                ContentValues cv = new ContentValues();
                Calendar calendar = Calendar.getInstance();

                int day =  calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year =  calendar.get(Calendar.YEAR);
                cv.put(orderConstant.ORDER_IDC,cursor.getString(0));
                cv.put(orderConstant.ORDER_COMMENT, cursor.getString(1));
                cv.put(orderConstant.ORDER_NAME, cursor.getString(2));
                cv.put(orderConstant.ORDER_PRICE, cursor.getString(3));
                cv.put(orderConstant.ORDER_NUMOFMEALS, cursor.getString(4));
                cv.put(orderConstant.ORDER_IDR,cursor.getString(5));
                cv.put(orderConstant.ORDER_IDM, cursor.getString(6));
                cv.put(orderConstant.ORDER_FLAG,orderConstant.ORDER_ACCPETED);
                cv.put(orderConstant.ORDER_date,day+" / "+month+" / "+year);

                String selectionmeal = orderConstant.ORDER_ID+"=?";
                int cursorrr =  getContentResolver().update(orderConstant.ORDER_URI,cv,selectionmeal,selectionArgs);
            }  while (cursor.moveToNext());
        }



    }
}