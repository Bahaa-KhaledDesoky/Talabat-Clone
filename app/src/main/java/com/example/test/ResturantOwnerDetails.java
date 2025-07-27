package com.example.test;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import data.resturantConstant;

public class ResturantOwnerDetails extends AppCompatActivity {
    private static final int SELECT_PHOTO =100;
    ImageView pickImag;
    byte[] image =null;
    EditText name;
    EditText hotline;
    EditText address;
    EditText openhours;
    EditText description;
    Resturants resturants;

    long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_owner_details);
        name = findViewById(R.id.editTextTextPersonName);
        hotline = findViewById(R.id.editTextNumber);
        address = findViewById(R.id.editText3);
        openhours = findViewById(R.id.editTextNumber2);
        description = findViewById(R.id.editText6);
        pickImag=findViewById(R.id.imageView2);

    }
    public void save_resturant(View view)
    {
        if (getIntent().hasExtra("ownerId")) {
            id = getIntent().getLongExtra("ownerId",-1);
        }
        BitmapDrawable drawable = (BitmapDrawable) pickImag.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        image = getBytes(bitmap);
        resturants= new Resturants(id+"",name.getText().toString().trim(),address.getText().toString().trim(),description.getText().toString().trim(),openhours.getText().toString().trim(),hotline.getText().toString().trim());
        ContentValues cv =new ContentValues();
        cv.put(resturantConstant.RESTURANT_NAME,resturants.getName());
        cv.put(resturantConstant.RESTURANT_PHONE,resturants.getNumber());
        cv.put(resturantConstant.RESTURANT_ADDRESS,resturants.getAddress());
        cv.put(resturantConstant.RESTURANT_OPENING_HOURS,resturants.getAddress());
        cv.put(resturantConstant.RESTURANT_DESCRIPTION,resturants.getDescreption());
        cv.put(resturantConstant.RESTURANT_ID,resturants.getId());
        cv.put(resturantConstant.RESTURANT_IMAGE,image);
        Uri uri = getContentResolver().insert(resturantConstant.RESTURANT_URI,cv);

        if(uri==null)
        {
            Toast.makeText(ResturantOwnerDetails.this, "Delete", Toast.LENGTH_LONG).show();
        }
        else

        {
//            Toast.makeText(ResturantOwnerDetails.this, "Done Baby"+id, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ResturantOwnerDetails.this,Sign_in.class);
            startActivity(intent);
        }
    }

    public void openGallery(View view)
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,SELECT_PHOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 100) {

            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                pickImag.setImageBitmap(decodeStream);

                image = getBytes(decodeStream);

            } catch (Exception ex) {
                Log.e("ex", ex.getMessage());
            }

        }
    }
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
}