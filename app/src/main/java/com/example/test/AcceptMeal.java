package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import data.customerConstants;
import data.mealConstant;
import data.orderConstant;
import data.resturantConstant;

public class AcceptMeal extends AppCompatActivity {
String name,allprice,date,address,phone,nameofmeals,numofmealsorderd,comment,idCustomer,idOrder;
TextView namee,allpricee,addresss,phonee,nameofmealss,numofmealsorderdd,commentt,datee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_meal);
        idCustomer=getIntent().getStringExtra(orderConstant.ORDER_IDC);
        date=getIntent().getStringExtra(orderConstant.ORDER_date);
        comment=getIntent().getStringExtra(orderConstant.ORDER_COMMENT);
        nameofmeals=getIntent().getStringExtra(orderConstant.ORDER_NAME);
        allprice=getIntent().getStringExtra(orderConstant.ORDER_PRICE);
        numofmealsorderd=getIntent().getStringExtra(orderConstant.ORDER_NUMOFMEALS);
        namee=findViewById(R.id.bodyname);
        allpricee=findViewById(R.id.Pricemealorder);
        addresss=findViewById(R.id.address);
        datee=findViewById(R.id.date);
        phonee=findViewById(R.id.phonebody);
        nameofmealss=findViewById(R.id.nameofmealorderd);
        numofmealsorderdd=findViewById(R.id.descreptionmealorderd);
        commentt=findViewById(R.id.commentmealorderd);
        String[] projection_resturant=new String[]{customerConstants.CUSTOMER_NAME,customerConstants.CUSTOMER_ADDRESS,customerConstants.CUSTOMER_PHONE};
        String[] selectionArgs=new String[]{idCustomer};
        String selection = customerConstants.CUSTOMER_ID+"=?";
        Cursor cursor_resturant =  getContentResolver().query(customerConstants.CUSTOMER_URI,projection_resturant,selection,selectionArgs,null);
        if(cursor_resturant.moveToFirst()) {
            namee.setText(cursor_resturant.getString(0));
            addresss.setText(cursor_resturant.getString(1));
            phonee.setText(cursor_resturant.getString(2));
        }
        nameofmealss.setText(nameofmeals);
        numofmealsorderdd.setText(numofmealsorderd);
        commentt.setText(comment);
        allpricee.setText(allprice);
        datee.setText(date);
    }

    public void Done(View view) {
        idOrder=getIntent().getStringExtra(orderConstant.ORDER_ID);
        String selection=orderConstant.ORDER_ID+"=?";
        String [] selectionArgs = new String[]{idOrder};
        int uri = getContentResolver().delete(orderConstant.ORDER_URI,selection,selectionArgs);
        if (uri ==0)
            Toast.makeText(AcceptMeal.this, "Error Delete Meal ", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(AcceptMeal.this, ownermyorders.class);
        startActivity(intent);
    }
}