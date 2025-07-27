package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data.orderConstant;

public class ownermyorders extends AppCompatActivity {
    String id;
    TextView num,idCustomerOrder;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownermyorders);
        num=findViewById(R.id.numberofitems);
        if (getIntent().hasExtra("USER_ID")) {
            id = getIntent().getStringExtra("USER_ID");
        }

        String[] selectionArgs=new String[]{id, orderConstant.ORDER_ACCPETED};
        /////////////////////////// meal data
        String selectionmeal = orderConstant.ORDER_IDR+"=?"+" AND "+orderConstant.ORDER_FLAG+"=?";
        String[] projection=new String[]{orderConstant.ORDER_IDC,orderConstant.ORDER_COMMENT,orderConstant.ORDER_NAME,orderConstant.ORDER_PRICE,orderConstant.ORDER_NUMOFMEALS,orderConstant.ORDER_IDR,orderConstant.ORDER_IDM,orderConstant.ORDER_FLAG,orderConstant.ORDER_ID,orderConstant.ORDER_IMAGE,orderConstant.ORDER_date};
        Cursor cursor =  getContentResolver().query(orderConstant.ORDER_URI,projection,selectionmeal,selectionArgs,null);
        OrdersDater item = new OrdersDater (ownermyorders.this,cursor);
        recyclerView=findViewById(R.id.mealsorderd);
        recyclerView.setAdapter(item);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        Toast.makeText(ownermyorders.this,""+item.getItemCount(),Toast.LENGTH_LONG).show();
        recyclerView.setLayoutManager(linearLayoutManager);
        num.setText(item.getItemCount()+"");
//        idCustomerOrder.setText("");
    }
}