package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import data.customerConstants;
import data.mealConstant;
import data.resturantConstant;

public class ResturantDetails extends AppCompatActivity {
String id,idcustomer;
TextView descreption,openingHours,phone,address,name;
EditText editText;
ImageView imageView;
RecyclerView recyclerView;
Resturants resturants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_details);
        if (getIntent().hasExtra("USER_ID")) {
            idcustomer = getIntent().getStringExtra("USER_ID");
        }
        if (getIntent().hasExtra(resturantConstant.RESTURANT_ID)) {
            id = getIntent().getStringExtra(resturantConstant.RESTURANT_ID);
        }
            descreption = findViewById(R.id.textViewdescreption);
            imageView = findViewById(R.id.imageView4);
            phone = findViewById(R.id.textViewnumber);
            address = findViewById(R.id.textViewaddress);
            openingHours = findViewById(R.id.textViewoeninghours);
            name = findViewById(R.id.textViewname);
            editText = findViewById(R.id.edittext7);


        //////////////// resturant data

            String[] projection_resturant=new String[]{resturantConstant.RESTURANT_ID,resturantConstant.RESTURANT_ADDRESS,resturantConstant.RESTURANT_DESCRIPTION,resturantConstant.RESTURANT_NAME,resturantConstant.RESTURANT_OPENING_HOURS,resturantConstant.RESTURANT_PHONE,resturantConstant.RESTURANT_IMAGE};
            String[] selectionArgs=new String[]{id};
            String selection = resturantConstant.RESTURANT_ID+"=?";
            Cursor cursor_resturant =  getContentResolver().query(resturantConstant.RESTURANT_URI,projection_resturant,selection,selectionArgs,null);
            if(cursor_resturant.moveToFirst()) {

                Bitmap bitmap = BitmapFactory.decodeByteArray(cursor_resturant.getBlob(6)
                        , 0,         cursor_resturant.getBlob(6).length);
                imageView.setImageBitmap(bitmap);
                resturants= new Resturants(id+"",cursor_resturant.getString(3),cursor_resturant.getString(1)
                        ,cursor_resturant.getString(2),cursor_resturant.getString(4)
                        ,cursor_resturant.getString(5));

                descreption.setText(resturants.getDescreption());
                openingHours.setText(resturants.getOpening_hours());
                phone.setText(resturants.getNumber());
                address.setText(resturants.getAddress());
                name.setText(resturants.getName());
            }
            /////////////////////////// meal data
            String selectionmeal = mealConstant.MEAL_RESTURANT_ID+"=?";
            String[] projection=new String[]{mealConstant.MEAL_ID,mealConstant.MEAL_NAME,mealConstant.MEAL_PRICE,mealConstant.MEAL_DESCRIBE,mealConstant.MEAL_RESTURANT_ID,mealConstant.MEAL_IMAGE};
            Cursor cursor =  getContentResolver().query(data.mealConstant.MEAL_URI,projection,selectionmeal,selectionArgs,null);
        itemDapter item = new itemDapter (ResturantDetails.this,cursor,idcustomer);
        recyclerView=findViewById(R.id.mealss);
        recyclerView.setAdapter(item);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        Toast.makeText(ResturantDetails.this,""+item.getItemCount(),Toast.LENGTH_LONG).show();
        recyclerView.setLayoutManager(linearLayoutManager);
        Toast.makeText(this,""+item.getItemCount(),Toast.LENGTH_LONG).show();
        }



    public void sort_up(View view) {
        Intent intent=new Intent(ResturantDetails.this,ResturantDetailsSortUp.class);
        intent.putExtra(resturantConstant.RESTURANT_ID,id);
        intent.putExtra("USER_ID",idcustomer);
        startActivity(intent);
    }

    public void sort_down(View view) {
        Intent intent=new Intent(ResturantDetails.this,ResturantDetailsSortDown.class);
        intent.putExtra(resturantConstant.RESTURANT_ID,id);
        intent.putExtra("USER_ID",idcustomer);
        startActivity(intent);
    }

    public void searchmeals(View view) {
        Intent intent=new Intent(ResturantDetails.this,SearchinMeals.class);
        intent.putExtra(resturantConstant.RESTURANT_ID,id);
        intent.putExtra("USER_ID",idcustomer);
        intent.putExtra(mealConstant.MEAL_NAME,editText.getText().toString());
        startActivity(intent);
    }

}