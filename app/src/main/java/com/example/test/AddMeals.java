package com.example.test;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import data.customerConstants;
import data.mealConstant;
import data.userConstants;

import static android.content.ContentUris.parseId;

public class AddMeals extends AppCompatActivity {
    private static final int SELECT_PHOTO =100;
   EditText name, price;
    EditText descreption;
    Meals meal;
        String Ownerid;
        ImageView pickImag;

        byte[] image =null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meals);
        name =(EditText) findViewById(R.id.editTextTextPersonName);
        price = (EditText)findViewById(R.id.editTextNumber);
        descreption = (EditText) findViewById(R.id.editText6);
        if (getIntent().hasExtra("USER_ID")) {
            Ownerid = getIntent().getStringExtra("USER_ID");
        }

        pickImag=findViewById(R.id.imageView2);
    }

//
//    @Override
//    protected void onActivityResult(int requestCode,int resultCode, Intent data){
//        super.onActivityResult(requestCode,resultCode,data);
//        if(requestCode==SELECT_PHOTO) {
//            if (resultCode == RESULT_OK) {
//                Uri selectImage = data.getData();
//                try {
//                    imageToStore= MediaStore.Images.Media.getBitmap(getContentResolver(),selectImage);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                imageView.setImageBitmap(imageToStore);
//                InputStream inputStream = null;
//                try {
//                    assert selectImage != null;
//                    inputStream = getContentResolver().openInputStream(selectImage);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                BitmapFactory.decodeStream(inputStream);
//                imageView.setImageURI(selectImage);
//            }
//        }
//    }
//    public static byte[] getBytes(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
//        return stream.toByteArray();
//    }
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

    public void save_meal(View view) {
        ContentValues cv = new ContentValues();
//        meal = new Meals(,, , 0 + "");
        //byte[] bp=imageInBytes;
//        Bitmap imagetostoreBitmap=imageToStore;
//        objectbyteArrayOutputStream=new ByteArrayOutputStream();
//        imagetostoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,objectbyteArrayOutputStream);
//        imageInBytes=objectbyteArrayOutputStream.toByteArray();
//        cv.put(mealConstant.MEAL_IMAGE,imageToStore);

        BitmapDrawable drawable = (BitmapDrawable) pickImag.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        image = getBytes(bitmap);

        cv.put(mealConstant.MEAL_NAME, name.getText().toString().trim());
        cv.put(mealConstant.MEAL_PRICE, price.getText().toString().trim());
        cv.put(mealConstant.MEAL_DESCRIBE, descreption.getText().toString().trim());
        cv.put(mealConstant.MEAL_IMAGE, image);
        cv.put(mealConstant.MEAL_RESTURANT_ID,Ownerid);

//        cv.put(mealConstant.MEAL_IMAGE, imagebyte());
        Uri uri = getContentResolver().insert(mealConstant.MEAL_URI, cv);
        if (uri == null)
            Toast.makeText(AddMeals.this, "error insert meal ", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(AddMeals.this, OwnerHome.class);
        startActivity(intent);

    }

}








//    private byte[] imagebyte(){
//        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
//        ByteArrayOutputStream stream=new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
//        byte[] byteArray =stream.toByteArray();
//        return byteArray;
//    }

//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        MyLayoutBinding binding = MyLayoutBinding.inflate(getLayoutInflater());
//
//        setContentView(binding.root);
//    }
//
//
//        itemDapter adapter=new itemDapter();




//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.ContentValues;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.io.ByteArrayOutputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//
//import data.mealConstant;
//
//        Meals meal;
//
//    private static final int SELECT_PHOTO =100;
//    TextView name;
//    TextView price;
//    TextView descreption;
//    TextView resturantID;
//    ImageView imageView;
//    String Ownerid;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_meals);
//        name = findViewById(R.id.nameOfTheMeal);
//        price =  findViewById(R.id.priceOfTheMeal);
//        if (getIntent().hasExtra("USER_ID")) {
//            Ownerid = getIntent().getStringExtra("USER_ID");
//        }
//        imageView=findViewById(R.id.imageView2);
//    }
//    public void openGallery(View view)
//    {
//        Intent intent=new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent,SELECT_PHOTO);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data){
//        super.onActivityResult(requestCode,resultCode,data);
//        if(requestCode==SELECT_PHOTO) {
//            if (resultCode == RESULT_OK) {
//                Uri selectImage = data.getData();
//                InputStream inputStream = null;
//                try {
//                    assert selectImage != null;
//                    inputStream = getContentResolver().openInputStream(selectImage);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                BitmapFactory.decodeStream(inputStream);
//                imageView.setImageURI(selectImage);
//            }
//        }
//    }
//    private byte[] imagebyte(){
//        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
//        ByteArrayOutputStream stream=new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
//        byte[] byteArray =stream.toByteArray();
//        return byteArray;
//    }
//
//    public void AddMeals(View view)
//    {
//
//      ContentValues cv = new ContentValues();
//        meal = new Meals(name.getText().toString().trim(), price.getText().toString().trim(), descreption.getText().toString().trim(), 0+"");
//        cv.put(mealConstant.MEAL_NAME, meal.getMealname());
//        cv.put(mealConstant.MEAL_PRICE, meal.getMealprice());
//        cv.put(mealConstant.MEAL_DESCRIBE, meal.getMealdescription());
//        cv.put(mealConstant.MEAL_RESTURANT_ID, 0+"");
//        Uri uri = getContentResolver().insert(mealConstant.MEAL_URI, cv);
//        if (uri == null)
//            Toast.makeText(AddMeals.this, "error insert meal ", Toast.LENGTH_LONG).show();
//
//    }
////        cv.put(mealConstant.MEAL_IMAGE, meal.getImageMeal());
//    }


