package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import data.customerConstants;
import data.mealConstant;
import data.resturantConstant;

public class ResturantAdapter extends RecyclerView.Adapter<ResturantAdapter.viewHolder> {
    String id;
    Context context;
    Cursor cursor;
    private ArrayList<Resturants> resturant = new ArrayList<>();

    public ResturantAdapter (Context context,Cursor cursor,String id) {
        this.id = id;
        this.context = context;
        this.cursor = cursor;
        setArray(cursor);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResturantAdapter.viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.resturantscardview,parent,false));
    }

//    @Override
//    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

//
//    }

    @Override
    public void onBindViewHolder(@NonNull ResturantAdapter.viewHolder holder, int position) {

        holder.name.setText(resturant.get(position).getName());
        Bitmap bitmap = BitmapFactory.decodeByteArray(resturant.get(position).getMealimage(), 0, resturant.get(position).getMealimage().length);
        holder.imageView.setImageBitmap(bitmap);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                ///////////////////// page of buy

                Intent intent = new Intent(context,ResturantDetails.class);
                intent.putExtra("USER_ID",id);
                intent.putExtra(resturantConstant.RESTURANT_ID,resturant.get(position).getId());
                context.startActivity(intent);
            }
        } );
    }

    @Override
    public int getItemCount() {
        return resturant.size();
    }
    private void setArray(Cursor cursor)
    {
//
        if(cursor.moveToFirst()) {
            do{
//             cursor.getBlob(5),
                resturant.add(new Resturants(cursor.getBlob(6),cursor.getString(0),cursor.getString(3),cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getString(5)));
            }  while (cursor.moveToNext());
        }
    }



    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.resturantImage);
            name=itemView.findViewById(R.id.resturantName);
        }
    }
}
