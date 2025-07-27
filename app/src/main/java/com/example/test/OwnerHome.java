package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import data.mealConstant;
import data.resturantConstant;

public class OwnerHome extends AppCompatActivity {

    String Ownerid;
    RecyclerView recyclerView;
    TextView textView;
    Cursor cursor=null;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);
        textView=findViewById(R.id.textViewname);
        imageView=findViewById(R.id.imageView4);
//        descreption = (EditText) findViewById(R.id.editText6);
        if (getIntent().hasExtra("USER_ID")) {
            Ownerid = getIntent().getStringExtra("USER_ID");
        }
//////////////// resturant data

        String[] projection_resturant=new String[]{resturantConstant.RESTURANT_NAME,resturantConstant.RESTURANT_IMAGE};
        String[] selectionArgs=new String[]{Ownerid};
        String selection = resturantConstant.RESTURANT_ID+"=?";
        Cursor cursor_resturant =  getContentResolver().query(resturantConstant.RESTURANT_URI,projection_resturant,selection,selectionArgs,null);
        if(cursor_resturant.moveToFirst()) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(cursor_resturant.getBlob(1)
                    , 0,         cursor_resturant.getBlob(1).length);
            imageView.setImageBitmap(bitmap);
            textView.setText(cursor_resturant.getString(0));

        }
        /////////////////////////// meal data
        String selectionmeal = mealConstant.MEAL_RESTURANT_ID+"=?";
        String[] projection=new String[]{mealConstant.MEAL_ID,mealConstant.MEAL_NAME,mealConstant.MEAL_PRICE,mealConstant.MEAL_DESCRIBE,mealConstant.MEAL_RESTURANT_ID,mealConstant.MEAL_IMAGE};
        cursor =  getContentResolver().query(data.mealConstant.MEAL_URI,projection,selectionmeal,selectionArgs,null);
        itemDapter item = new itemDapter (OwnerHome.this,cursor);
        recyclerView=findViewById(R.id.meals);
        recyclerView.setAdapter(item);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        Toast.makeText(OwnerHome.this,""+item.getItemCount(),Toast.LENGTH_LONG).show();
        recyclerView.setLayoutManager(linearLayoutManager);
        Toast.makeText(this,""+item.getItemCount(),Toast.LENGTH_LONG).show();

    }

    public void movetoAddMeals(View view) {

        Intent intent = new Intent(OwnerHome.this, AddMeals.class);
        intent.putExtra("USER_ID",Ownerid);
        startActivity(intent);
    }


    public void sort_up(View view) {
        Intent intent = new Intent(OwnerHome.this,Sort_up.class);
        intent.putExtra("USER_ID",Ownerid);
        startActivity(intent);
    }

    public void sort_down(View view) {
        Intent intent = new Intent(OwnerHome.this,Sort_down.class);
        intent.putExtra("USER_ID",Ownerid);
        startActivity(intent);
    }

}

//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.Activity;
//import android.content.ContentValues;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import data.customerConstants;
//import data.mealConstant;
//import data.userConstants;
//
//import static android.content.ContentUris.parseId;
//
//public class OwnerHome extends AppCompatActivity  {
//    EditText name;
//    EditText price;
//    EditText descreption;
//    Meals meal;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_owner_home);
//        name = (EditText) findViewById(R.id.editTextTextPersonName);
//        price = (EditText) findViewById(R.id.editTextNumber);
//        descreption = (EditText) findViewById(R.id.editText6);
//      }
//
//
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////
////        MyLayoutBinding binding = MyLayoutBinding.inflate(getLayoutInflater());
////
////        setContentView(binding.root);
////    }
//
//
////        itemDapter adapter=new itemDapter();
//
//
//
//
//
//   public void save_meal(View view) {
//      ContentValues cv = new ContentValues();
//        meal = new Meals(name.getText().toString().trim(), price.getText().toString().trim(), descreption.getText().toString().trim(), 0+"");
//        cv.put(mealConstant.MEAL_NAME, meal.getMealname());
//        cv.put(mealConstant.MEAL_PRICE, meal.getMealprice());
//        cv.put(mealConstant.MEAL_DESCRIBE, meal.getMealdescription());
//        cv.put(mealConstant.MEAL_RESTURANT_ID, 0+"");
//        Uri uri = getContentResolver().insert(mealConstant.MEAL_URI, cv);
//        if (uri == null)
//            Toast.makeText(OwnerHome.this, "error insert meal ", Toast.LENGTH_LONG).show();
//
//    }
//}