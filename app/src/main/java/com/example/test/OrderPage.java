package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import data.mealConstant;
import data.orderConstant;
import data.resturantConstant;

import static com.example.test.AddMeals.getBytes;

public class OrderPage extends AppCompatActivity {
    String id,ownerid,customrid;
TextView name,price,descreption,numberorder,allprice;
ImageView imageView;
byte[] imagee=null ;
EditText comment;
    int num=1;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_page);
        if (getIntent().hasExtra("USER_ID")) {
            customrid = getIntent().getStringExtra("USER_ID");
        }
        name = findViewById(R.id.namemealorder);
        imageView = findViewById(R.id.imageView);
        price = findViewById(R.id.pricemealorder);
        descreption = findViewById(R.id.descreptionmealorder);
        comment = findViewById(R.id.comment);
        numberorder = findViewById(R.id.numberoforders);
        allprice = findViewById(R.id.totalprice);
        id=getIntent().getStringExtra(mealConstant.MEAL_ID);
        String[] projection_resturant=new String[]{mealConstant.MEAL_IMAGE};
        String[] selectionArgs=new String[]{id};
        String selection = mealConstant.MEAL_ID+"=?";
        Cursor cursor_resturant =  getContentResolver().query(mealConstant.MEAL_URI,projection_resturant,selection,selectionArgs,null);
        if(cursor_resturant.moveToFirst()) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(cursor_resturant.getBlob(0)
                , 0,         cursor_resturant.getBlob(0).length);
          imagee = cursor_resturant.getBlob(0);
        imageView.setImageBitmap(bitmap);
        }

        name.setText(getIntent().getStringExtra(mealConstant.MEAL_NAME));
        price.setText(getIntent().getStringExtra(mealConstant.MEAL_PRICE));
        allprice.setText(getIntent().getStringExtra(mealConstant.MEAL_PRICE));
        descreption.setText(getIntent().getStringExtra(mealConstant.MEAL_DESCRIBE));
        ownerid=getIntent().getStringExtra(mealConstant.MEAL_RESTURANT_ID);
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
        float x = Float.valueOf(price.getText().toString())*num ;
        allprice.setText(x+"");

    }

    public void addtocart(View view) {

        ContentValues cv = new ContentValues();
        int day =  calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year =  calendar.get(Calendar.YEAR);
        int hour =  calendar.get(Calendar.HOUR_OF_DAY);
        int minute =  calendar.get(Calendar.MINUTE);
        cv.put(orderConstant.ORDER_IDC,customrid+"");
        cv.put(orderConstant.ORDER_COMMENT, comment.getText().toString().trim());
        cv.put(orderConstant.ORDER_NAME, name.getText().toString().trim());
        cv.put(orderConstant.ORDER_PRICE, allprice.getText().toString().trim());
        cv.put(orderConstant.ORDER_NUMOFMEALS, num+"");
        cv.put(orderConstant.ORDER_IDR,ownerid);
        cv.put(orderConstant.ORDER_IDM, id);
        cv.put(orderConstant.ORDER_FLAG,orderConstant.ORDER_INCART);
        cv.put(orderConstant.ORDER_date,day+" / "+month+" / "+year+"                  "+hour+" : "+minute);

//        String[] projection_resturant=new String[]{mealConstant.MEAL_IMAGE};
//        String[] selectionArgs=new String[]{id};
//        String selection = mealConstant.MEAL_ID+"=?";
//        Cursor cursor_resturant =  getContentResolver().query(mealConstant.MEAL_URI,projection_resturant,selection,selectionArgs,null);
//        if(cursor_resturant.moveToFirst()) {
//            Bitmap bitmapp = BitmapFactory.decodeByteArray(cursor_resturant.getBlob(0)
//                    , 0,         cursor_resturant.getBlob(0).length);
//            imageView.setImageBitmap(bitmap);
//        }
        cv.put(orderConstant.ORDER_IMAGE, imagee );

        Uri uri = getContentResolver().insert(orderConstant.ORDER_URI, cv);
        if (uri == null)
            Toast.makeText(OrderPage.this, "error Add Meal in Cart", Toast.LENGTH_LONG).show();
    }

    public void Order(View view) {
        byte[] image =null;


            ContentValues cv = new ContentValues();
            cv.put(orderConstant.ORDER_IDC,customrid);
            cv.put(orderConstant.ORDER_COMMENT, comment.getText().toString().trim());
            cv.put(orderConstant.ORDER_NAME, name.getText().toString().trim());
            cv.put(orderConstant.ORDER_PRICE, allprice.getText().toString().trim());
            cv.put(orderConstant.ORDER_NUMOFMEALS, num+"");
            cv.put(orderConstant.ORDER_IDR,ownerid);
            cv.put(orderConstant.ORDER_IDM, id);
            cv.put(orderConstant.ORDER_FLAG,orderConstant.ORDER_ACCPETED);
            cv.put(orderConstant.ORDER_IMAGE, imagee );
        int day =  calendar.get(Calendar.DAY_OF_MONTH);
        int hour =  calendar.get(Calendar.HOUR_OF_DAY);
        int minute =  calendar.get(Calendar.MINUTE);
//        int second =  calendar.get(Calendar.SECOND);
        int month = calendar.get(Calendar.MONTH);
        int year =  calendar.get(Calendar.YEAR);
            cv.put(orderConstant.ORDER_date,day+" / "+month+" / "+year+"                  "+hour+" : "+minute);
            Uri uri = getContentResolver().insert(orderConstant.ORDER_URI, cv);
            if (uri == null)
                Toast.makeText(OrderPage.this, "error insert meal ", Toast.LENGTH_LONG).show();

    }
}


//        Intent intent = new Intent(OrderPage.this,Cart.class);
//        intent.putExtra(resturantConstant.RESTURANT_ID,id);
//        intent.putExtra("USER_ID",customrid);
//        intent.putExtra(mealConstant.MEAL_NAME,name.getText().toString());
//        intent.putExtra(mealConstant.MEAL_PRICE,price.getText().toString());
//        startActivity(intent);