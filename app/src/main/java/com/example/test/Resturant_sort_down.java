package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import data.resturantConstant;

public class Resturant_sort_down extends AppCompatActivity {
    EditText editText;
    String id;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_sort_down);
        editText=findViewById(R.id.search);
        if (getIntent().hasExtra("USER_ID")) {
            id = getIntent().getStringExtra("USER_ID");
        }
        String[] projection_resturant=new String[]{resturantConstant.RESTURANT_ID,resturantConstant.RESTURANT_ADDRESS,resturantConstant.RESTURANT_DESCRIPTION,resturantConstant.RESTURANT_NAME,resturantConstant.RESTURANT_OPENING_HOURS,resturantConstant.RESTURANT_PHONE,resturantConstant.RESTURANT_IMAGE};
        Cursor cursor=  getContentResolver().query(resturantConstant.RESTURANT_URI,projection_resturant,null,null,resturantConstant.RESTURANT_NAME+" DESC");
        ResturantAdapter item = new ResturantAdapter (Resturant_sort_down.this,cursor,id);
        recyclerView=findViewById(R.id.resturants);
        recyclerView.setAdapter(item);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        Toast.makeText(Resturant_sort_down.this,""+item.getItemCount(),Toast.LENGTH_LONG).show();
        recyclerView.setLayoutManager(linearLayoutManager);
        Toast.makeText(this,""+item.getItemCount(),Toast.LENGTH_LONG).show();
    }

    public void random(View view) {
        Intent intent=new Intent(Resturant_sort_down.this,SearchForResturants_random.class);
        intent.putExtra("USER_ID",id);
        startActivity(intent);
    }

    public void sort_up(View view) {
        Intent intent=new Intent(Resturant_sort_down.this,Resturants_sort_up.class);
        intent.putExtra("USER_ID",id);
        startActivity(intent);
    }

    public void movetomysearch(View view) {
        Intent intent=new Intent(Resturant_sort_down.this,SearchResult.class);
        intent.putExtra(resturantConstant.RESTURANT_NAME,editText.getText().toString());
        intent.putExtra("USER_ID",id);
        startActivity(intent);
    }
}