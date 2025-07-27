package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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

import data.customerConstants;
import data.mealConstant;

public class itemDapter extends RecyclerView.Adapter<itemDapter.viewHolder> {
    String flag=-1+"";
    Context context;
    Cursor cursor;
    private ArrayList<Meals> meal = new ArrayList<>();

    public itemDapter(Context context,Cursor cursor,String flag) {
        this.context = context;
        this.cursor = cursor;
        this.flag = flag;
        setArray(cursor);
    }
    public itemDapter(Context context,Cursor cursor) {
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
//        byte[] foodimage = meal.get(position).getImageMeal();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(foodimage,0,foodimage.length);
//        holder.imageView.setImageBitmap(bitmap);
        holder.name.setText(meal.get(position).getMealname());
        holder.price.setText(meal.get(position).getMealprice()+"  L.E");
        Bitmap bitmap = BitmapFactory.decodeByteArray(meal.get(position).getImageMeal(), 0, meal.get(position).getImageMeal().length);
        holder.imageView.setImageBitmap(bitmap);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                if(flag==-1+""){
                    ///////////////////// page of buy
                    //     Toast.makeText(context,meal.get(position).getIdMeal(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, EditorDelete.class);

//                    intent.putExtra(mealConstant.MEAL_IMAGE,meal.get(position).getImageMeal());
                    intent.putExtra(mealConstant.MEAL_ID,meal.get(position).getIdMeal());
                    intent.putExtra(mealConstant.MEAL_NAME,meal.get(position).getMealname());
                    intent.putExtra(mealConstant.MEAL_PRICE,meal.get(position).getMealprice());
                    intent.putExtra(mealConstant.MEAL_DESCRIBE,meal.get(position).getMealdescription());
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent(context, OrderPage.class);
                    intent.putExtra("USER_ID",flag);
                    intent.putExtra(mealConstant.MEAL_ID,meal.get(position).getIdMeal());

//                    intent.putExtra(mealConstant.MEAL_IMAGE,meal.get(position).getImageMeal());
                    intent.putExtra(mealConstant.MEAL_NAME,meal.get(position).getMealname());
                    intent.putExtra(mealConstant.MEAL_PRICE,meal.get(position).getMealprice());
                    intent.putExtra(mealConstant.MEAL_DESCRIBE,meal.get(position).getMealdescription());
                    intent.putExtra(mealConstant.MEAL_RESTURANT_ID,meal.get(position).getResturantid());
                    context.startActivity(intent);
                }
            }
        } );

    }

    @Override
    public int getItemCount() {
        return meal.size();
    }
    private void setArray(Cursor cursor)
    {
//
     if(cursor.moveToFirst()) {
         do{
             meal.add(new Meals(cursor.getBlob(5),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(0)));
         }  while (cursor.moveToNext());
        }
    }



    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;

        TextView price;
        public viewHolder(@NonNull View itemView) {

//            ImageView imgUser = (ImageView) convertView.findViewById(R.id.imgUser);

            super(itemView);
            imageView = itemView.findViewById(R.id.imagemeal);
            name=itemView.findViewById(R.id.nameOfTheMeal);
            price=itemView.findViewById(R.id.priceOfTheMeal);
        }
    }
}





//import android.content.ContentValues;
//        import android.content.Context;
//        import android.content.Intent;
//        import android.database.Cursor;
//        import android.graphics.Bitmap;
//        import android.graphics.BitmapFactory;
//        import android.util.AttributeSet;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.ImageView;
//        import android.widget.TextView;
//        import android.widget.Toast;
//
//        import androidx.annotation.NonNull;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import org.w3c.dom.Text;
//
//        import java.util.ArrayList;
//
//        import data.mealConstant;
//
//public class itemDapter extends RecyclerView.Adapter<itemDapter.viewHolder> {
//    Context context;
//    Cursor cursor;
//    private ArrayList<Meals> meal = new ArrayList<>();
//
//    public itemDapter(Context context,Cursor cursor) {
//        this.context = context;
//        this.cursor = cursor;
//        setArray(cursor);
//    }
//
//    @NonNull
//    @Override
//    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false));
//    }
//    @Override
//    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
//        byte[] foodimage = meal.get(position).getImageMeal();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(foodimage,0,foodimage.length);
//        holder.imageView.setImageBitmap(bitmap);
//        holder.name.setText(meal.get(position).getMealname());
//        holder.price.setText(meal.get(position).getMealprice());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                ///////////////////// page of buy
//                //     Toast.makeText(context,meal.get(position).getIdMeal(),Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(context, EditorDelete.class);
//                intent.putExtra(mealConstant.MEAL_ID,meal.get(position).getIdMeal());
//                intent.putExtra(mealConstant.MEAL_NAME,meal.get(position).getMealname());
//                intent.putExtra(mealConstant.MEAL_PRICE,meal.get(position).getMealprice());
//                intent.putExtra(mealConstant.MEAL_DESCRIBE,meal.get(position).getMealdescription());
//                context.startActivity(intent);
//            }
//        } );
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return meal.size();
//    }
//    private void setArray(Cursor cursor)
//    {
//        if(cursor.moveToFirst()) {
//            do{
//                meal.add(new Meals(cursor.getBlob(5),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(0)));
//            }  while (cursor.moveToNext());
//        }
//    }
//
//
//
//    public class viewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//        TextView name;
//        TextView price;
//        public viewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.imagemeal);
//            name=itemView.findViewById(R.id.nameOfTheMeal);
//            price=itemView.findViewById(R.id.priceOfTheMeal);
//        }
//    }
//}
