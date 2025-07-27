package data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class customerProvider extends ContentProvider {

    customerData customer;

    private static final int case1 = 100;
    private static final int case2 = 101;
    private static final UriMatcher sUri = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        // in case of all customer
        sUri.addURI(customerConstants.CUSTOMER_AUTHORITY,customerConstants.CUSTOMER_TABLE,case1);
        // in case of one customer
        sUri.addURI(customerConstants.CUSTOMER_AUTHORITY,customerConstants.CUSTOMER_TABLE+"/#",case2);

    }
    @Override
    public boolean onCreate() {
        customer = new customerData(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri,String[] projection,String selection, String[] selectionArgs,String sort) {
        SQLiteDatabase db = customer.getReadableDatabase();
        int match = sUri.match(uri);
        Cursor cursor = null;
        switch (match) {
            case case1 :
                cursor =db.query(customerConstants.CUSTOMER_TABLE,projection,selection,selectionArgs,null,null,sort);
                break;
            case case2 :
                selection = customerConstants.CUSTOMER_ID+"=?";
               cursor=db.query(customerConstants.CUSTOMER_TABLE,projection,selection,selectionArgs,null,null,sort);
                break;
            default:
                Toast.makeText(getContext(),"query in customer provider",Toast.LENGTH_LONG).show();
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri,ContentValues cv) {
        //SQLiteDatabase db = customer.getReadableDatabase();
        int match = sUri.match(uri);
        Cursor cursor = null;
        switch (match) {
            case case1 :
                return insert_for_real(uri,cv);

            case case2 :
                Toast.makeText(getContext(),"insert case 1 in customer provider",Toast.LENGTH_LONG).show();
                break;

            default:
                Toast.makeText(getContext(),"insert in customer provider",Toast.LENGTH_LONG).show();
        }
        return null;
    }



    private Uri insert_for_real (Uri uri, ContentValues cv)
    {
//        userProvider user = new userProvider();
//        ContentValues cvUser = new ContentValues();
//        cvUser.put(userConstants.USER_EMAIL,cv.getAsString(customerConstants.CUSTOMER_EMAIL));
//        cvUser.put(userConstants.USER_PASSWORD,cv.getAsString(customerConstants.CUSTOMER_PASSWORD));
//        cvUser.put(userConstants.USER_ID_REAL,cv.getAsString(customerConstants.CUSTOMER_ID));
//        cvUser.put(userConstants.USER_FLAG,userConstants.USER_CUSTOMER_FLAG);
        if(validation(cv)==1) {
//            if(user.insert(userConstants.USER_URI,cv)==null){
//                return null;
//            }
            SQLiteDatabase db = customer.getWritableDatabase();
            long id = db.insert(customerConstants.CUSTOMER_TABLE, null, cv);
            if (id == -1) {
                Toast.makeText(getContext(), "insert private in customer provider", Toast.LENGTH_LONG).show();
                db.close();
//                user.delete(null,null, new String[]{customerConstants.CUSTOMER_ID, String.valueOf(userConstants.USER_CUSTOMER_FLAG)});
                return null;
            }
            else{
                Toast.makeText(getContext(), "insert private in customer provider successfully", Toast.LENGTH_LONG).show();

                db.close();
                return ContentUris.withAppendedId(uri, id);
            }
        }

        else
        {
            return null;
        }
    }

    @Override
    public int delete(Uri uri,String selection,String[] selectionArgs) {
        SQLiteDatabase db =customer.getWritableDatabase();
//        userProvider user = new userProvider();
        int match = sUri.match(uri);
        switch (match)
        {
            case case1:
                selection=customerConstants.CUSTOMER_ID+"=?";
//                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
//                user.delete(null,null, new String[]{String.valueOf(ContentUris.parseId(uri)), String.valueOf(userConstants.USER_CUSTOMER_FLAG)});
                db.delete(customerConstants.CUSTOMER_TABLE,selection,selectionArgs);
            default:
                Toast.makeText(getContext(), "delete private in customer provider", Toast.LENGTH_LONG).show();
        }
        return 0;
    }

    @Override
        public int update(Uri uri,ContentValues cv, String selection, String[] selectionArgs) {
        SQLiteDatabase db =customer.getWritableDatabase();
        userProvider user = new userProvider();
        ContentValues cvUser = new ContentValues();
        cvUser.put(userConstants.USER_EMAIL,cv.getAsString(customerConstants.CUSTOMER_EMAIL));
        cvUser.put(userConstants.USER_PASSWORD,cv.getAsString(customerConstants.CUSTOMER_PASSWORD));
        cvUser.put(userConstants.USER_ID_REAL,cv.getAsString(customerConstants.CUSTOMER_ID));
        cvUser.put(userConstants.USER_FLAG,userConstants.USER_CUSTOMER_FLAG);
        int match = sUri.match(uri);
        if(validation(cv)==1) {
            switch (match) {
                case case1:
                    if (user.update(null,cv,null,null)==0)
                    {
                        Toast.makeText(getContext(),"update in customer 1 provider",Toast.LENGTH_LONG).show();
                        return 0;
                    }
                  return   db.update(customerConstants.CUSTOMER_TABLE,cv,selection,selectionArgs);
                case case2:
                    selection = customerConstants.CUSTOMER_ID+"=?";
                    selectionArgs = new  String[]{String.valueOf(ContentUris.parseId(uri))};
                    if (user.update(null,cv,null,null)==0)
                    {
                        user.delete(null,null, new String[]{ownerConstants.OWNER_ID, String.valueOf(userConstants.USER_OWNER_FLAG)});

                        Toast.makeText(getContext(),"update in customer 1 provider",Toast.LENGTH_LONG).show();
                        return 0;
                    }
                    int update = db.update(customerConstants.CUSTOMER_TABLE, cv, selection, selectionArgs);
                    return update;

                default:
                    Toast.makeText(getContext(),"update in customer provider",Toast.LENGTH_LONG).show();
            }
        }
        return 0;
    }
    private int validation (ContentValues cv)
    {
          if(cv.getAsString(customerConstants.CUSTOMER_EMAIL).isEmpty()){
        Toast.makeText(getContext(), "insert the EMAIL", Toast.LENGTH_LONG).show();
        return 0;
         }
        else if(cv.getAsString(customerConstants.CUSTOMER_NAME).isEmpty()) {
            Toast.makeText(getContext(), "insert the CUSTOMER_NAME", Toast.LENGTH_LONG).show();
            return 0;
        }

        else if(cv.getAsString(customerConstants.CUSTOMER_PASSWORD).isEmpty()){
            Toast.makeText(getContext(), "insert the password", Toast.LENGTH_LONG).show();
            return 0;
        }
        else if(cv.getAsString(customerConstants.CUSTOMER_PHONE).isEmpty()){
            Toast.makeText(getContext(), "insert the phone", Toast.LENGTH_LONG).show();
            return 0;
        }
        else if(cv.getAsString(customerConstants.CUSTOMER_ADDRESS).isEmpty()){
            Toast.makeText(getContext(), "insert the CUSTOMER_ADDRESS", Toast.LENGTH_LONG).show();
            return 0;
        }

        else return 1;

    }
}
