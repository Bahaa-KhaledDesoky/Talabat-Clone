package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import data.resturantConstant;

public class SigninOwnerEnter extends AppCompatActivity {
    TextView textView;
    String Ownerid;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_owner_enter);
        if (getIntent().hasExtra("USER_ID")) {
            Ownerid = getIntent().getStringExtra("USER_ID");
        }
            textView=findViewById(R.id.textViewname);
            imageView=findViewById(R.id.imageView4);
//            String[] projection_resturant=new String[]{resturantConstant.RESTURANT_NAME};
//            String[] selectionArgs=new String[]{Ownerid};
//            String selection = resturantConstant.RESTURANT_ID+"=?";
//            Cursor cursor_resturant =  getContentResolver().query(resturantConstant.RESTURANT_URI,projection_resturant,selection,selectionArgs,null);
//            if(cursor_resturant.moveToFirst()) {textView.setText(cursor_resturant.getString(0));}
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
        }

    public void movetoeditprofile(View view) {
        Intent intent = new Intent(SigninOwnerEnter.this,EditprofileOwner.class);
        intent.putExtra("USER_ID",Ownerid);
        startActivity(intent);
    }

    public void movetomymeals(View view) {
        Intent intent = new Intent(SigninOwnerEnter.this,OwnerHome.class);
        intent.putExtra("USER_ID",Ownerid);
        startActivity(intent);
    }

    public void movetoorders(View view) {
        Intent intent = new Intent(SigninOwnerEnter.this,ownermyorders.class);
        intent.putExtra("USER_ID",Ownerid);
        startActivity(intent);
    }
}