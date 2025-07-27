package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import data.resturantConstant;

public class EditprofileOwner extends AppCompatActivity {
    EditText name;
    EditText hotline;
    EditText address;
    EditText openhours;
    EditText description;
    String id;

    Resturants resturants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile_owner);
        name = findViewById(R.id.editTextTextPersonName);
        hotline = findViewById(R.id.editTextNumber);
        address = findViewById(R.id.editText3);
        openhours = findViewById(R.id.editTextNumber2);
        description = findViewById(R.id.editText6);
        Toast.makeText(EditprofileOwner.this,id+"",Toast.LENGTH_LONG).show();
        if (getIntent().hasExtra("USER_ID")) {
            id = getIntent().getStringExtra("USER_ID");
        }
        String[] projection_resturant=new String[]{resturantConstant.RESTURANT_ID,resturantConstant.RESTURANT_ADDRESS,resturantConstant.RESTURANT_DESCRIPTION,resturantConstant.RESTURANT_NAME,resturantConstant.RESTURANT_OPENING_HOURS,resturantConstant.RESTURANT_PHONE};
        String[] selectionArgs=new String[]{id+""};
        String selection = resturantConstant.RESTURANT_ID+"=?";
        Cursor cursor_resturant =  getContentResolver().query(resturantConstant.RESTURANT_URI,projection_resturant,selection,selectionArgs,null);
        if(cursor_resturant.moveToFirst()) {

            resturants= new Resturants(id+"",cursor_resturant.getString(3),cursor_resturant.getString(1)
                    ,cursor_resturant.getString(2),cursor_resturant.getString(4)
                    ,cursor_resturant.getString(5));

            description.setText(resturants.getDescreption());
            openhours.setText(resturants.getOpening_hours());
            hotline.setText(resturants.getNumber());
            address.setText(resturants.getAddress());
            name.setText(resturants.getName());

        }
    }

    public void save_resturant_data(View view) {
        String selection = resturantConstant.RESTURANT_ID+"=?";
        String[] selectionArgs = new  String[]{id+""};
        ContentValues cv =new ContentValues();

        cv.put(resturantConstant.RESTURANT_NAME,resturants.getName());
        cv.put(resturantConstant.RESTURANT_PHONE,resturants.getNumber());
        cv.put(resturantConstant.RESTURANT_ADDRESS,resturants.getAddress());
        cv.put(resturantConstant.RESTURANT_OPENING_HOURS,resturants.getOpening_hours());
        cv.put(resturantConstant.RESTURANT_DESCRIPTION,resturants.getDescreption());
        cv.put(resturantConstant.RESTURANT_ID,id+"");
        int uri = getContentResolver().update(resturantConstant.RESTURANT_URI,cv,selection,selectionArgs);

    }
}