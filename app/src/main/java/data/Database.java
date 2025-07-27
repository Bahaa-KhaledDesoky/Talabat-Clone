package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
//import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.test.Customer;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context,"customer.db", null, 1);
    }public static final String CUSTOMER_TABLE ="customer";
    public static final String CUSTOMER_NAME ="name";
    public static final String CUSTOMER_EMAIL ="email";
    public static final String CUSTOMER_PASSWORD ="password";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Sql ="CREATE TABLE " +CUSTOMER_TABLE+
                " ( "
                +CUSTOMER_EMAIL+" TEXT , "+
                CUSTOMER_PASSWORD+" TEXT , "+
                CUSTOMER_NAME+" TEXT"+" )";
        db.execSQL(Sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean AddCustomer(Customer customer)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CUSTOMER_EMAIL,customer.getEmail());
        cv.put(CUSTOMER_PASSWORD,customer.getPassword());
        cv.put(CUSTOMER_NAME,customer.getName());
        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if(insert==-1)
        {
            return false;
        }
        else
            return true;
    }
    public List<Customer> viewCustomer(@Nullable Context context) {
        List<Customer> result = new ArrayList<>();
        String Sql = "select * from " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Sql, null);
        
        if (cursor.moveToFirst()) {
        do {
            String email = cursor.getString(0);
            String password = cursor.getString(1);
            String name = cursor.getString(2);
//            Toast.makeText(context, name, Toast.LENGTH_LONG).show();
//            Customer customer = new Customer(email, name, password);
//            result.add(customer);
        } while (cursor.moveToNext());
    }
        cursor.close();
        db.close();
        return result;
    }
}
