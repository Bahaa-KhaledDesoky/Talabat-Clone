package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import data.customerConstants;
import data.orderConstant;

public class cartDapter extends RecyclerView.Adapter<cartDapter.viewHolder> {
    Context context;
    Cursor cursor;
    private ArrayList<Order> order = new ArrayList<>();

    public cartDapter(Context context,Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
        setArray(cursor);
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new cartDapter.viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cartcardview,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.name.setText(order.get(position).getOrder_name());
        Bitmap bitmap = BitmapFactory.decodeByteArray(order.get(position).get_orderimage(), 0, order.get(position).get_orderimage().length);
        holder.imageView.setImageBitmap(bitmap);

        holder.price.setText(order.get(position).getOrder_price()+"  L.E");
        holder.numofoneitem.setText("( "+order.get(position).getOrder_numofMeals()+" )");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                    Intent intent = new Intent(context, editOrder.class);
//                    intent.putExtra(customerConstants.CUSTOMER_ID,flag);
                    intent.putExtra(orderConstant.ORDER_IDC,order.get(position).getOrder_idc());
                    intent.putExtra(orderConstant.ORDER_COMMENT,order.get(position).getOrder_comment());
                    intent.putExtra(orderConstant.ORDER_NAME,order.get(position).getOrder_name());
                    intent.putExtra(orderConstant.ORDER_PRICE,order.get(position).getOrder_price());
                    intent.putExtra(orderConstant.ORDER_NUMOFMEALS,order.get(position).getOrder_numofMeals());
                    intent.putExtra(orderConstant.ORDER_IDR,order.get(position).getOrder_idr());
                    intent.putExtra(orderConstant.ORDER_IDM,order.get(position).getOrder_idr());
                    intent.putExtra(orderConstant.ORDER_FLAG,order.get(position).getOrder_flag());
                    intent.putExtra(orderConstant.ORDER_ID,order.get(position).getOrder_id());
                    context.startActivity(intent);
            }
        } );

    }

    @Override
    public int getItemCount() {
        return order.size();
    }
    private void setArray(Cursor cursor)
    {
//
        if(cursor.moveToFirst()) {
            do{

                order.add(new Order(cursor.getBlob(9),cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8)));
            }  while (cursor.moveToNext());
        }
    }



    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView price;
        TextView numofoneitem;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagemeal);
            name=itemView.findViewById(R.id.namemeal);
            price=itemView.findViewById(R.id.pricemeal);
            numofoneitem=itemView.findViewById(R.id.numberofmealsofthis);
        }
    }
}