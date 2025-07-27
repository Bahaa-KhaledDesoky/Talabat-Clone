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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import data.mealConstant;
import data.resturantConstant;

public class Sort_up extends AppCompatActivity {
    String Ownerid;
    RecyclerView recyclerView;
    TextView textView;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_up);
        textView=findViewById(R.id.textViewname);
        imageView=findViewById(R.id.imageView4);

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
        Cursor cursor =  getContentResolver().query(data.mealConstant.MEAL_URI,projection,selectionmeal,selectionArgs, mealConstant.MEAL_NAME+" ASC");
        itemDapter item = new itemDapter (Sort_up.this,cursor);
        recyclerView=findViewById(R.id.meals);
        recyclerView.setAdapter(item);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        Toast.makeText(Sort_up.this,""+item.getItemCount(),Toast.LENGTH_LONG).show();
        recyclerView.setLayoutManager(linearLayoutManager);
        Toast.makeText(this,""+item.getItemCount(),Toast.LENGTH_LONG).show();

    }

    public void movetoAddMeals(View view) {

        Intent intent = new Intent(Sort_up.this, AddMeals.class);
        intent.putExtra("USER_ID",Ownerid);
        startActivity(intent);
    }

    public void sort_down(View view) {
        Intent intent = new Intent(Sort_up.this,Sort_down.class);
        intent.putExtra("USER_ID",Ownerid);
        startActivity(intent);
    }

    public void sort_random(View view) {
        Intent intent = new Intent(Sort_up.this,OwnerHome.class);
        intent.putExtra("USER_ID",Ownerid);
        startActivity(intent);
    }
}