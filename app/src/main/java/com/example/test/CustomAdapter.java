package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.viewHolder> {
    Context context;
    Cursor cursor;
    private ArrayList<Meals> meal = new ArrayList<>();

    public CustomAdapter (Context context,Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
        setArray(cursor);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
//        holder.imageView.setImageResource(meal.get(position).getImageMeal());
//        View row ;
//        byte[] foodimage = meal.get(position).getImageMeal();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(foodimage,0,foodimage.length);
//        holder.imageView.setImageBitmap(bitmap);
        holder.name.setText(meal.get(position).getMealname());
        holder.price.setText(meal.get(position).getMealprice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                ///////////////////// page of buy
                Toast.makeText(context,meal.get(position).getIdMeal(),Toast.LENGTH_LONG).show();
            }
        } );

    }

    @Override
    public int getItemCount() {
        return meal.size();
    }
    private void setArray(Cursor cursor)
    {
        if(cursor.moveToFirst()) {
            do{
//                cursor.getBlob(5),
                meal.add(new Meals(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(0)));
            }  while (cursor.moveToNext());
        }
    }


    public class viewHolder extends RecyclerView.ViewHolder {
        //        ImageView imageView;
        TextView name;
        TextView price;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
//            imageView=itemView.findViewById(R.id.myimageView);
            name=itemView.findViewById(R.id.nameOfTheMeal);
            price=itemView.findViewById(R.id.priceOfTheMeal);
        }
    }
}
