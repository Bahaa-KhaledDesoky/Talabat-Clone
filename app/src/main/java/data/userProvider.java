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

import java.net.URI;

public class userProvider extends ContentProvider {
    userData user;
    private static final int case1 = 100;
    private static final int case2 = 101;
    private static final UriMatcher sUri = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        // in case of all user
        sUri.addURI(userConstants.USER_AUTHORITY,userConstants.USER_TABLE,case1);
        // in case of one user
        sUri.addURI(userConstants.USER_AUTHORITY,userConstants.USER_TABLE+"/#",case2);
    }
    @Override
    public boolean onCreate() {
        user =new userData(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri,String[] projection,String selection, String[] selectionArgs,String sort) {
        SQLiteDatabase db = user.getReadableDatabase();
        int match = sUri.match(uri);
        Cursor cursor = null;
        switch (match) {
            case case1 :
                cursor = db.query(userConstants.USER_TABLE,projection,selection,selectionArgs,null,null,sort);
                break;
            case case2 :
                Toast.makeText(getContext(),"query in user case 2 provider",Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(getContext(),"query in user provider",Toast.LENGTH_LONG).show();
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
        //SQLiteDatabase db = user.getReadableDatabase();
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
    public Uri insert_for_real(Uri uri, ContentValues cv){
        SQLiteDatabase db = user.getWritableDatabase();
        if(validation(cv)==1) {

            long id = db.insert(userConstants.USER_TABLE, null, cv);
            db.close();
            if(id == -1) {
                Toast.makeText(getContext(), "this email is taken", Toast.LENGTH_LONG).show();
                return null;
            }
            else
            {
                return ContentUris.withAppendedId(uri, id);
            }
        }
        else{
            db.close();
            return null;
        }
    }

    @Override
    public int delete(Uri uri,String selection,String[] selectionArgs) {
        SQLiteDatabase db = user.getWritableDatabase();
        selection=userConstants.USER_ID_REAL+"=?"+" AND "+userConstants.USER_FLAG+" =?";
        db.delete(userConstants.USER_TABLE,selection,selectionArgs);
        return 0;
    }

    @Override
    public int update(Uri uri,ContentValues cv, String selection, String[] selectionArgs) {
        if (validation(cv)==1)
        {
            ///////////////chk hear
            SQLiteDatabase db = user.getWritableDatabase();
            selection = userConstants.USER_FLAG+" = "+cv.getAsInteger(userConstants.USER_FLAG)+" AND "+userConstants.USER_ID_REAL+" = "+cv.getAsInteger(userConstants.USER_ID_REAL);
            return   db.update(userConstants.USER_TABLE,cv,selection,selectionArgs);
        }
        else
            Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();

        return 0;
    }
    private int validation (ContentValues cv) {

        if (cv.getAsString(userConstants.USER_EMAIL).isEmpty()) {
            Toast.makeText(getContext(), "insert the user email", Toast.LENGTH_LONG).show();
            return 0;
        } else if (cv.getAsString(userConstants.USER_PASSWORD).isEmpty()) {
            Toast.makeText(getContext(), "insert the user PASSWORD", Toast.LENGTH_LONG).show();
            return 0;
        } else if (cv.getAsString(userConstants.USER_FLAG).isEmpty()) {
            Toast.makeText(getContext(), "insert the USER_FLAG", Toast.LENGTH_LONG).show();
            return 0;
        } else if (cv.getAsString(userConstants.USER_ID_REAL).isEmpty()) {
            Toast.makeText(getContext(), "insert the USER_ID_REAL", Toast.LENGTH_LONG).show();
            return 0;
        }
        else return 1;

    }
}
