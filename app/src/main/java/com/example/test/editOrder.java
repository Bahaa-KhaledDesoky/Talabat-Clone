package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import data.mealConstant;
import data.orderConstant;

public class editOrder extends AppCompatActivity {
    String id,ownerid,customrid;
    TextView name,price,descreption,numberorder,allprice;
    ImageView imageView;
    EditText comment;
    int num=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);
        if (getIntent().hasExtra(orderConstant.ORDER_IDC)) {
            customrid = getIntent().getStringExtra(orderConstant.ORDER_IDC);
        }
        name = findViewById(R.id.namemealorder);
        imageView = findViewById(R.id.imageView);
        price = findViewById(R.id.pricemealorder);
        descreption = findViewById(R.id.descreptionmealorder);
        comment = findViewById(R.id.comment);
        numberorder = findViewById(R.id.numberoforders);
        allprice = findViewById(R.id.totalprice);
        numberorder.setText(getIntent().getStringExtra(orderConstant.ORDER_NUMOFMEALS));
        id=getIntent().getStringExtra(orderConstant.ORDER_ID);
        name.setText(getIntent().getStringExtra(orderConstant.ORDER_NAME));
        allprice.setText(getIntent().getStringExtra(orderConstant.ORDER_PRICE));
        price.setText(Float.valueOf(allprice.getText().toString())/Float.valueOf(numberorder.getText().toString())+"");
        comment.setText(getIntent().getStringExtra(orderConstant.ORDER_COMMENT));
        ownerid=getIntent().getStringExtra(orderConstant.ORDER_IDR);
        String[] projection_resturant=new String[]{mealConstant.MEAL_IMAGE};
        String[] selectionArgs=new String[]{id};
        String selection = orderConstant.ORDER_ID+"=?";
        Cursor cursor_resturant =  getContentResolver().query(orderConstant.ORDER_URI,projection_resturant,selection,selectionArgs,null);
        if(cursor_resturant.moveToFirst()) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(cursor_resturant.getBlob(0)
                    , 0,         cursor_resturant.getBlob(0).length);
            imageView.setImageBitmap(bitmap);
        }

    }
    public void plus_order(View view){
        num = Integer.valueOf(numberorder.getText().toString());
        num++;
        numberorder.setText(num+"");
        total(num);
    }

    public void minus_order(View view) {
        num = Integer.valueOf(numberorder.getText().toString());
        if (num!=1)
        {
            num--;
            numberorder.setText(num+"");
            total(num);
        }
    }
    public void total(int num)
    {
        float x = Float.valueOf(price.getText().toString())*num;
        allprice.setText(x+"");

    }

    public void Save(View view) {
        String [] selectionArgs = new String[]{id};
        String selection = orderConstant.ORDER_ID+" =?";

        ContentValues cv = new ContentValues();
//        meal = new Meals(,, , 0 + "");
        cv.put(orderConstant.ORDER_IDC,customrid);
        cv.put(orderConstant.ORDER_COMMENT, comment.getText().toString().trim());
        cv.put(orderConstant.ORDER_NAME, name.getText().toString().trim());
        cv.put(orderConstant.ORDER_PRICE, allprice.getText().toString().trim());
        cv.put(orderConstant.ORDER_NUMOFMEALS, num+"");
        cv.put(orderConstant.ORDER_IDR,ownerid);
        cv.put(orderConstant.ORDER_IDM, getIntent().getStringExtra(orderConstant.ORDER_IDM));
        cv.put(orderConstant.ORDER_FLAG,orderConstant.ORDER_INCART);
        int uri = getContentResolver().update(orderConstant.ORDER_URI, cv,selection,selectionArgs);
        if (uri ==0)
            Toast.makeText(editOrder.this, "Error Edit Meal ", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(editOrder.this,Cart.class);
        intent.putExtra("USER_ID",customrid);
        startActivity(intent);
    }

    public void deleteOrder(View view) {
        String [] selectionArgs = new String[]{id};
        String selection = orderConstant.ORDER_ID+" =?";
        int uri = getContentResolver().delete(orderConstant.ORDER_URI,selection,selectionArgs);
        if (uri ==0)
            Toast.makeText(editOrder.this, "Error Delete Meal ", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(editOrder.this, Cart.class);
        intent.putExtra("USER_ID",customrid);
        startActivity(intent);

    }
}