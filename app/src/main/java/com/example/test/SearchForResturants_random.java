package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import data.resturantConstant;

public class SearchForResturants_random extends AppCompatActivity {
    String id;
    EditText search_area;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_resturants_random);
        if (getIntent().hasExtra("USER_ID")) {
            id = getIntent().getStringExtra("USER_ID");
        }
        String[] projection_resturant=new String[]{resturantConstant.RESTURANT_ID,resturantConstant.RESTURANT_ADDRESS,resturantConstant.RESTURANT_DESCRIPTION,
                resturantConstant.RESTURANT_NAME,resturantConstant.RESTURANT_OPENING_HOURS,resturantConstant.RESTURANT_PHONE,resturantConstant.RESTURANT_IMAGE};
        Cursor cursor=  getContentResolver().query(resturantConstant.RESTURANT_URI,projection_resturant,null,null,null);
        ResturantAdapter item = new ResturantAdapter (SearchForResturants_random.this,cursor,id);
        search_area=findViewById(R.id.search);
        recyclerView=findViewById(R.id.resturants);
        recyclerView.setAdapter(item);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        Toast.makeText(SearchForResturants_random.this,""+item.getItemCount(),Toast.LENGTH_LONG).show();
        recyclerView.setLayoutManager(linearLayoutManager);
        Toast.makeText(this,""+item.getItemCount(),Toast.LENGTH_LONG).show();
    }


    public void sort_up(View view) {
        Intent intent = new Intent(SearchForResturants_random.this,Resturants_sort_up.class);
        intent.putExtra("USER_ID",id);
        startActivity(intent);
    }

    public void sort_down(View view) {
        Intent intent = new Intent(SearchForResturants_random.this,Resturant_sort_down.class);
        intent.putExtra("USER_ID",id);
        startActivity(intent);
    }

    public void movetomysearch(View view) {

        Intent intent=new Intent(SearchForResturants_random.this,SearchResult.class);
        intent.putExtra(resturantConstant.RESTURANT_NAME,search_area.getText().toString());
        intent.putExtra("USER_ID",id);
        startActivity(intent);
    }
}





































