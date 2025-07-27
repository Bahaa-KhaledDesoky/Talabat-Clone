package com.example.test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

//import data.Database;
import data.customerConstants;
import data.ownerConstants;
import data.userConstants;

import static android.content.ContentUris.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent intent = new Intent(getApplicationContext(), Sign_in.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
//    EditText email ;
//    EditText password ;
//    EditText nameF ;
//    EditText nameL ;
//    EditText address ;
//    EditText phone ;
//    Customer customer;
////    Database db;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        email = (EditText) findViewById(R.id.EmailAddress);
//        nameF = (EditText) findViewById(R.id.namef);
//        password = (EditText) findViewById(R.id.Password);
//        address = (EditText) findViewById(R.id.address);
//        nameL = (EditText) findViewById(R.id.namel);
//        phone = (EditText) findViewById(R.id.phone);
////        db = new Database(MainActivity.this);
//    }
//
////    @RequiresApi(api = Build.VERSION_CODES.R)
//    @RequiresApi(api = Build.VERSION_CODES.R)
//    public void button1 (View view)
//    {
//
/////////////////////////////////////////// send
//        Intent intent = new Intent(MainActivity.this,FirstPage.class);
//
//        startActivity(intent);
////
//
//        ///////////// sign in
////
//
//        String[] projection=new String[]{userConstants.USER_EMAIL,userConstants.USER_PASSWORD,userConstants.USER_FLAG,userConstants.USER_ID_REAL};
//        String selection = userConstants.USER_EMAIL+" =?"+" AND "+userConstants.USER_PASSWORD+" =?";
//        String[] selectionArgs=new String[]{email.getText().toString().trim(),password.getText().toString()};
//        Cursor cursor =  getContentResolver().query(userConstants.USER_URI,projection,selection,selectionArgs,null);
//        if(cursor.moveToFirst()==false)
//        {
//        // error
//            Toast.makeText(MainActivity.this,"There is an error in the email or the password",Toast.LENGTH_LONG).show();
//        }
//        else {
//
//      //  move to his page
//            nameF.setText(cursor.getString(0));
//            nameL.setText(cursor.getString(1));
//            address.setText(cursor.getString(2));
//            phone.setText(cursor.getString(3));
//        }
//
//////        sdgsdg|gsdgsdg
//





















//
//
//        ContentValues cv =new ContentValues();
////        if()  radio button
//        cv.put(ownerConstants.OWNER_EMAIL,email.getText().toString().trim());
//        cv.put(ownerConstants.OWNER_PASSWORD,password.getText().toString().trim());
//        cv.put(ownerConstants.OWNER_NAME_FIRST,nameF.getText().toString().trim());
//        cv.put(ownerConstants.OWNER_NAME_SECOND,nameL.getText().toString().trim());
//        cv.put(ownerConstants.OWNER_ADDRESS,address.getText().toString().trim());
//        cv.put(ownerConstants.OWNER_PHONE,phone.getText().toString().trim());
////        Uri uri = getContentResolver().insert(ownerConstants.OWNER_URI,cv,null);
////        long id = 10;
////        if (uri!=null)
//        {
//            id = parseId(uri);
//            ContentValues cvv = new ContentValues();
//            nameF.setText(id+"");
//            cvv.put(userConstants.USER_EMAIL, email.getText().toString().trim());
//            cvv.put(userConstants.USER_PASSWORD,password.getText().toString().trim());
//            cvv.put(userConstants.USER_FLAG,"1");
//            cvv.put(userConstants.USER_ID_REAL,id);
//            Uri urii = getContentResolver().insert(userConstants.USER_URI,cvv);
//            String[]s=new String[]{id+""};
//            if (urii==null)
//            {
//                int u = getContentResolver().delete(ownerConstants.OWNER_URI,null,s);
//                Toast.makeText(MainActivity.this,"deleted"+u,Toast.LENGTH_LONG).show();
//            }
//        }

////        else
//        cv.put(customerConstants.CUSTOMER_EMAIL,email.getText().toString().trim());
//        cv.put(customerConstants.CUSTOMER_PASSWORD,password.getText().toString().trim());
//        cv.put(customerConstants.CUSTOMER_NAME_FIRST,nameF.getText().toString().trim());
//        cv.put(customerConstants.CUSTOMER_NAME_SECOND,nameL.getText().toString().trim());
//        cv.put(customerConstants.CUSTOMER_ADDRESS,address.getText().toString().trim());
//        cv.put(customerConstants.CUSTOMER_PHONE,phone.getText().toString().trim());
//        String[]ss=new String[]{4+""};
//        Uri uri = getContentResolver().insert(customerConstants.CUSTOMER_URI,cv,null);
//        long id = 10;
//        if (uri!=null)
//        {
//            id = parseId(uri);
//            ContentValues cvv = new ContentValues();
//            nameF.setText(id+"");
//            cvv.put(userConstants.USER_EMAIL, email.getText().toString().trim());
//            cvv.put(userConstants.USER_PASSWORD,password.getText().toString().trim());
//            cvv.put(userConstants.USER_FLAG,"1");
//            cvv.put(userConstants.USER_ID_REAL,id);
//            Uri urii = getContentResolver().insert(userConstants.USER_URI,cvv);
//            String[]s=new String[]{id+""};
//            if (urii==null)
//            {
//                int u = getContentResolver().delete(ownerConstants.OWNER_URI,null,s);
//                Toast.makeText(MainActivity.this,"deleted"+u,Toast.LENGTH_LONG).show();
//            }
//        }



//
//        try {
//            customer = new Customer(email.getText().toString(), name.getText().toString(), password.getText().toString());
//            Toast.makeText(MainActivity.this,"1",Toast.LENGTH_LONG).show();
//        }
//        catch (Exception e){
//            Toast.makeText(MainActivity.this,"2",Toast.LENGTH_LONG).show();
//            customer = new Customer(null,null,null);
//        }
//        boolean cheak = db.AddCustomer(customer);
//        if(cheak==true)
//        {
//            Toast.makeText(MainActivity.this,"3",Toast.LENGTH_LONG).show();
//        }
//        else
//            Toast.makeText(MainActivity.this,"4",Toast.LENGTH_LONG).show();

//    }

//    public void button2 (View view){
//
//        List<Customer> result = db.viewCustomer(MainActivity.this);
//        Toast.makeText(MainActivity.this,result.toString(), Toast.LENGTH_LONG).show();
//    }


//