package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import data.mealConstant;
import data.resturantConstant;

public class EditorDelete extends AppCompatActivity {
    EditText name,price,descreption;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_delete);
        name = findViewById(R.id.textView2);
        price = findViewById(R.id.textView5);
        descreption = findViewById(R.id.textView6);
        if (getIntent().hasExtra(mealConstant.MEAL_ID)) {
            id = getIntent().getStringExtra(mealConstant.MEAL_ID);
        }
        name.setText(getIntent().getStringExtra(mealConstant.MEAL_NAME));


        price.setText(getIntent().getStringExtra(mealConstant.MEAL_PRICE));
        descreption.setText(getIntent().getStringExtra(mealConstant.MEAL_DESCRIBE));
    }

    public void EditInformation(View view) {

        String [] selectionArgs = new String[]{id};
        String selection = mealConstant.MEAL_ID+" =?";

        ContentValues cv = new ContentValues();
//        meal = new Meals(,, , 0 + "");
        cv.put(mealConstant.MEAL_NAME, name.getText().toString().trim());
        cv.put(mealConstant.MEAL_PRICE, price.getText().toString().trim());
        cv.put(mealConstant.MEAL_DESCRIBE, descreption.getText().toString().trim());
        int uri = getContentResolver().update(mealConstant.MEAL_URI, cv,null,selectionArgs);
        if (uri ==0)
            Toast.makeText(EditorDelete.this, "error insert meal ", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(EditorDelete.this, OwnerHome.class);
        String[] projection_resturant=new String[]{mealConstant.MEAL_RESTURANT_ID};
//        String[] selectionArgss=new String[]{id};
        String selectionn = mealConstant.MEAL_RESTURANT_ID+"=?";
        Cursor cursor_resturant =  getContentResolver().query(mealConstant.MEAL_URI,projection_resturant,selectionn,selectionArgs,null);
        if(cursor_resturant.moveToFirst()) { intent.putExtra("USER_ID",cursor_resturant.getString(0));}
        startActivity(intent);

    }

    public void DeleteData(View view) {
        String [] selectionArgs = new String[]{id};
        int uri = getContentResolver().delete(mealConstant.MEAL_URI,null,selectionArgs);
        if (uri ==0)
            Toast.makeText(EditorDelete.this, "Error Delete Meal ", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(EditorDelete.this, OwnerHome.class);
        startActivity(intent);


    }
}