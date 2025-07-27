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

public class resturantProvider extends ContentProvider {

    resturantData resturant;
    private static final int case1 = 100;
    private static final int case2 = 101;
    private static final UriMatcher sUri = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        // in case of all RESTURANT
        sUri.addURI(resturantConstant.RESTURANT_AUTHORITY,resturantConstant.RESTURANT_TABLE,case1);
        // in case of one RESTURANT
        sUri.addURI(resturantConstant.RESTURANT_AUTHORITY,resturantConstant.RESTURANT_TABLE+"/#",case2);

    }
    @Override
    public boolean onCreate() {
        resturant = new resturantData(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri,String[] projection,String selection, String[] selectionArgs,String sort) {
        SQLiteDatabase db = resturant.getReadableDatabase();
        int match = sUri.match(uri);
        Cursor cursor = null;
        switch (match) {
            case case1 :
                cursor =db.query(resturantConstant.RESTURANT_TABLE,projection,selection,selectionArgs,null,null,sort);
                Toast.makeText(getContext(),"query in resturant provider  111",Toast.LENGTH_LONG).show();
                break;
            case case2 :
                selection = resturantConstant.RESTURANT_ID+"=?";
                selectionArgs= new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor=db.query(resturantConstant.RESTURANT_TABLE,projection,selection,selectionArgs,null,null,sort);
                Toast.makeText(getContext(),"query in resturant provider  222 ",Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(getContext(),"query in resturant provider",Toast.LENGTH_LONG).show();
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
        SQLiteDatabase db = resturant.getReadableDatabase();
        int match = sUri.match(uri);
        Cursor cursor = null;
        switch (match) {
            case case1 :
                return insert_for_real(uri,cv);

            case case2 :

                Toast.makeText(getContext(),"insert case 1 in resturant provider",Toast.LENGTH_LONG).show();

                break;
            default:
                Toast.makeText(getContext(),"insert in resturant provider",Toast.LENGTH_LONG).show();
        }
        return null;
    }



    private Uri insert_for_real (Uri uri, ContentValues cv)
    {
        SQLiteDatabase db = resturant.getWritableDatabase();
        if(validation(cv)==1) {

            long id = db.insert(resturantConstant.RESTURANT_TABLE, null, cv);
            if (id == -1) {
                Toast.makeText(getContext(), "this name is TAKEN!", Toast.LENGTH_LONG).show();
                return null;
            } else
                return ContentUris.withAppendedId(uri, id);
        }
        else
            return null;
    }

    @Override
    public int delete(Uri uri,String selection,String[] selectionArgs) {
        SQLiteDatabase db =resturant.getWritableDatabase();
        int match = sUri.match(uri);
        switch (match)
        {
            case case1:
                db.delete(resturantConstant.RESTURANT_TABLE,selection,selectionArgs);
            case case2:
                selection=resturantConstant.RESTURANT_ID+"=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                db.delete(resturantConstant.RESTURANT_TABLE,selection,selectionArgs);
            default:
                Toast.makeText(getContext(), "delete private in resturant provider", Toast.LENGTH_LONG).show();
        }
        return 0;
    }


    @Override
    public int update(Uri uri,ContentValues cv, String selection, String[] selectionArgs) {
        SQLiteDatabase db =resturant.getWritableDatabase();
        int match = sUri.match(uri);
        if(validation(cv)==1) {
            switch (match) {
                case case1:
                    return   db.update(resturantConstant.RESTURANT_TABLE,cv,selection,selectionArgs);
                case case2:
                    selection = resturantConstant.RESTURANT_ID+"=?";
                    selectionArgs = new  String[]{String.valueOf(ContentUris.parseId(uri))};
                    return   db.update(resturantConstant.RESTURANT_TABLE,cv,selection,selectionArgs);

                default:
                    Toast.makeText(getContext(),"update in resturant provider",Toast.LENGTH_LONG).show();
            }
        }
        return 0;
    }
    private int validation (ContentValues cv)
    {
        if(cv.getAsString(resturantConstant.RESTURANT_NAME).isEmpty()) {
            Toast.makeText(getContext(), "insert the RESTURANT_NAME_FIRST", Toast.LENGTH_LONG).show();
            return 0;
        }

        else if(cv.getAsString(resturantConstant.RESTURANT_PHONE).isEmpty()){
            Toast.makeText(getContext(), "insert the PHONE", Toast.LENGTH_LONG).show();
            return 0;
        }
        else if(cv.getAsString(resturantConstant.RESTURANT_ADDRESS).isEmpty()){
            Toast.makeText(getContext(), "insert the RESTURANT_ADDRESS", Toast.LENGTH_LONG).show();
            return 0;
        }
        else if(cv.getAsString(resturantConstant.RESTURANT_OPENING_HOURS).isEmpty()){
            Toast.makeText(getContext(), "insert the OPENING_HOURS", Toast.LENGTH_LONG).show();
            return 0;
        }

        else if(cv.getAsString(resturantConstant.RESTURANT_DESCRIPTION).isEmpty()){
            Toast.makeText(getContext(), "insert the DESCRIPTION", Toast.LENGTH_LONG).show();
            return 0;
        }


        else return 1;

    }
}
