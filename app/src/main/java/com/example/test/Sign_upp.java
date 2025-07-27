package com.example.test;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import static android.widget.Toast.*;
import data.*;
import data.userConstants;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import static android.content.ContentUris.parseId;

public class Sign_upp extends AppCompatActivity {
    EditText email;
    EditText password;
    EditText name;
    EditText address;
    EditText phone;
    RadioGroup radioG;
    Customer customer;
    Owner owner;
    long idd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upp);
        email = (EditText) findViewById(R.id.emailedittext);
        name = (EditText) findViewById(R.id.firstnameedittext);
        password = (EditText) findViewById(R.id.passwordedittext);
        address = (EditText) findViewById(R.id.addressedittext);
        phone = (EditText) findViewById(R.id.numberedittext);
        radioG = (RadioGroup) findViewById(R.id.LinearLayout2);
//        customer = new Customer(email.getText().toString().trim(), name.getText().toString().trim(), password.getText().toString().trim(), address.getText().toString().trim(), phone.getText().toString().trim());
//        owner = new Owner(email.getText().toString().trim(), name.getText().toString().trim(), password.getText().toString().trim(), address.getText().toString().trim(), phone.getText().toString().trim());

    }

    //    @RequiresApi(api = Build.VERSION_CODES.R)
    public void submit_Sign_upp(View view) {
        int check = radioG.getCheckedRadioButtonId();
        switch (check) {
            case -1:
                Toast.makeText(Sign_upp.this, "Are you Customer or Resturant Owner", Toast.LENGTH_LONG).show();
                break;

            case R.id.radioButton1: {
                save_in_customer();
            }
            break;


            case R.id.radioButton2: {
                save_in_owner();
            }
            break;
            default:
                Toast.makeText(Sign_upp.this, "Impossable" + check, Toast.LENGTH_LONG).show();

        }

    }

    //
//   @RequiresApi(api = Build.VERSION_CODES.R)
    private void save_in_owner() {
        ContentValues cv = new ContentValues();
        owner = new Owner(email.getText().toString().trim(), name.getText().toString().trim(), password.getText().toString().trim(), address.getText().toString().trim(), phone.getText().toString().trim());
        cv.put(ownerConstants.OWNER_EMAIL, owner.getEmail());
        cv.put(data.ownerConstants.OWNER_PASSWORD, owner.getPassword());
        cv.put(data.ownerConstants.OWNER_NAME, owner.getName());
        cv.put(data.ownerConstants.OWNER_ADDRESS, owner.getAddress());
        cv.put(data.ownerConstants.OWNER_PHONE, owner.getPhone());
        Uri uri = getContentResolver().insert(data.ownerConstants.OWNER_URI, cv);

        if (uri != null) {
            long id = parseId(uri);
            idd = id;
            ContentValues cvv = new ContentValues();
            cvv.put(userConstants.USER_EMAIL, email.getText().toString().trim());
            cvv.put(userConstants.USER_PASSWORD, password.getText().toString().trim());
            cvv.put(userConstants.USER_FLAG, userConstants.USER_OWNER_FLAG);
            cvv.put(userConstants.USER_ID_REAL, id);
            Uri urii = getContentResolver().insert(userConstants.USER_URI, cvv);
            String[] s = new String[]{id + ""};
            if (urii == null) {
                int u = getContentResolver().delete(data.ownerConstants.OWNER_URI, null, s);
                Toast.makeText(Sign_upp.this, "This Email has already taken !", Toast.LENGTH_LONG).show();
            } else {
//                    Toast.makeText(Sign_upp.this,""+idd, LENGTH_LONG).show();
                Intent intent = new Intent(Sign_upp.this, ResturantOwnerDetails.class);
                intent.putExtra("ownerId", idd);
                startActivity(intent);
            }
        }

    }

    private void save_in_customer() {
        ContentValues cv = new ContentValues();

        customer = new Customer(email.getText().toString().trim(), name.getText().toString().trim(), password.getText().toString().trim(), address.getText().toString().trim(), phone.getText().toString().trim());
        cv.put(data.customerConstants.CUSTOMER_EMAIL, customer.getEmail());
        cv.put(data.customerConstants.CUSTOMER_PASSWORD, customer.getPassword());
        cv.put(data.customerConstants.CUSTOMER_NAME, customer.getName());
        cv.put(data.customerConstants.CUSTOMER_ADDRESS, customer.getAddress());
        cv.put(data.customerConstants.CUSTOMER_PHONE, customer.getPhone());
        Uri uri = getContentResolver().insert(data.customerConstants.CUSTOMER_URI, cv);
        long id = 0;
        if (uri != null) {
            id = parseId(uri);
            ContentValues cvv = new ContentValues();
            cvv.put(userConstants.USER_EMAIL, customer.getEmail());
            cvv.put(userConstants.USER_PASSWORD, customer.getPassword());
            cvv.put(userConstants.USER_FLAG, userConstants.USER_CUSTOMER_FLAG);
            cvv.put(userConstants.USER_ID_REAL, id);
            Uri urii = getContentResolver().insert(userConstants.USER_URI, cvv);
            String[] s = new String[]{id + ""};
            if (urii == null) {
                int u = getContentResolver().delete(data.customerConstants.CUSTOMER_URI, null, s);
                Toast.makeText(Sign_upp.this, "Deleted" + u, Toast.LENGTH_LONG).show();
            } else {
//                    Toast.makeText(Sign_upp.this,""+idd, LENGTH_LONG).show();
                Intent intent = new Intent(Sign_upp.this, Sign_in.class);
                startActivity(intent);
            }
        }
    }

    public void to_sign_in(View view) {
        Intent intent = new Intent(Sign_upp.this, Sign_in.class);
        startActivity(intent);
    }
}