package com.example.test;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import data.*;
import android.widget.Toast;
import static android.widget.Toast.*;

public class Sign_in extends AppCompatActivity {
    EditText email;
    EditText password;
    User user;
    public static final String Type = "USER_FLAG" ;
    public static final String ID = "USER_ID" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.emailedittext);
        password = findViewById(R.id.passwordedittext);

    }

    public void Sign_In(View view){
        /////////// sign in
        user=new User(email.getText().toString().trim(),password.getText().toString().trim());
        String[] projection=new String[]{userConstants.USER_EMAIL,userConstants.USER_PASSWORD,userConstants.USER_FLAG,userConstants.USER_ID_REAL};
        String selection = userConstants.USER_EMAIL+" =?"+" AND "+userConstants.USER_PASSWORD+" =?";
        String[] selectionArgs=new String[]{user.getEmail(),user.getPassword()};
        Cursor cursor =  getContentResolver().query(userConstants.USER_URI,projection,selection,selectionArgs,null);
        if(cursor.moveToFirst()==false)
        {
            //         error
            makeText(Sign_in.this,"There is an Error in the Email or the Password !", LENGTH_LONG).show();
        }
        else {
            makeText(Sign_in.this,"Okay Baby", LENGTH_LONG).show();
            Toast.makeText(Sign_in.this,"Correct",Toast.LENGTH_LONG).show();
            Intent intent;
            if (cursor.getString(2).equals("1")){
                intent=new Intent(Sign_in.this,CustomerHome.class);
                intent.putExtra(ID,cursor.getString(3));
                startActivity(intent);
            }
            else if(cursor.getString(2).equals("0")) {
                intent=new Intent(Sign_in.this,SigninOwnerEnter.class);
                intent.putExtra(ID,cursor.getString(3));
                startActivity(intent);
            }
//         move to his page
//            nameF.setText(cursor.getString(0));
//            nameL.setText(cursor.getString(1));
//            address.setText(cursor.getString(2));
//            phone.setText(cursor.getString(3));
        }
    }

    public void MoveToSign_up(View view) {
        Intent intent=new Intent(Sign_in.this,Sign_upp.class);
        startActivity(intent);
    }
}