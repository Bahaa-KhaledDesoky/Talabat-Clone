package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import data.resturantConstant;

public class SearchResult extends AppCompatActivity {
    String id;
    RecyclerView recyclerView;
    TextView noData;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        if (getIntent().hasExtra("USER_ID")) {
            id = getIntent().getStringExtra("USER_ID");
        }
        noData=findViewById(R.id.no);
        if (getIntent().hasExtra(resturantConstant.RESTURANT_NAME)) {
            name = getIntent().getStringExtra(resturantConstant.RESTURANT_NAME);
        }
            String[] projection_resturant=new String[]{resturantConstant.RESTURANT_ID,resturantConstant.RESTURANT_ADDRESS,resturantConstant.RESTURANT_DESCRIPTION,resturantConstant.RESTURANT_NAME,resturantConstant.RESTURANT_OPENING_HOURS,resturantConstant.RESTURANT_PHONE,resturantConstant.RESTURANT_IMAGE};
            String[] selectionArgs=new String[]{name};
            String selection = resturantConstant.RESTURANT_NAME+"=?";
            Cursor cursor=  getContentResolver().query(resturantConstant.RESTURANT_URI,projection_resturant,selection,selectionArgs,null);
            ResturantAdapter item = new ResturantAdapter (SearchResult.this,cursor,id);
            recyclerView=findViewById(R.id.search_result);
            recyclerView.setAdapter(item);
            LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
            Toast.makeText(SearchResult.this,""+item.getItemCount(),Toast.LENGTH_LONG).show();
            recyclerView.setLayoutManager(linearLayoutManager);
            Toast.makeText(this,""+item.getItemCount(),Toast.LENGTH_LONG).show();
            if (item.getItemCount()==0){
                noData.setText("Not Found");
            }

    }
}