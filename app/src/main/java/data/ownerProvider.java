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

public class ownerProvider extends ContentProvider {

    ownerData owner;
    private static final int case1 = 100;
    private static final int case2 = 101;
    private static final UriMatcher sUri = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        // in case of all OWNER
        sUri.addURI(ownerConstants.OWNER_AUTHORITY,ownerConstants.OWNER_TABLE,case1);
        // in case of one OWNER
        sUri.addURI(ownerConstants.OWNER_AUTHORITY,ownerConstants.OWNER_TABLE+"/#",case2);

    }
    @Override
    public boolean onCreate() {
        owner = new ownerData(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri,String[] projection,String selection, String[] selectionArgs,String sort) {
        SQLiteDatabase db = owner.getReadableDatabase();
        int match = sUri.match(uri);
        Cursor cursor = null;
        switch (match) {
            case case1 :
                cursor =db.query(ownerConstants.OWNER_TABLE,projection,selection,selectionArgs,null,null,sort);
                break;
            case case2 :
                selection = ownerConstants.OWNER_ID+"=?";
                cursor=db.query(ownerConstants.OWNER_TABLE,projection,selection,selectionArgs,null,null,sort);
                break;
            default:
                Toast.makeText(getContext(),"query in owner provider",Toast.LENGTH_LONG).show();
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
        SQLiteDatabase db = owner.getReadableDatabase();
        int match = sUri.match(uri);
        Cursor cursor = null;
        switch (match) {
            case case1 :
                return insert_for_real(uri,cv);

            case case2 :
                Toast.makeText(getContext(),"insert case 1 in owner provider",Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(getContext(),"insert in owner provider",Toast.LENGTH_LONG).show();
        }
        return null;
    }



    private Uri insert_for_real (Uri uri, ContentValues cv)
    {
//        userProvider user = new userProvider();
//        ContentValues cvUser = new ContentValues();
//        cvUser.put(userConstants.USER_EMAIL,cv.getAsString(ownerConstants.OWNER_EMAIL));
//        cvUser.put(userConstants.USER_PASSWORD,cv.getAsString(ownerConstants.OWNER_PASSWORD));
//        cvUser.put(userConstants.USER_ID_REAL,cv.getAsString(ownerConstants.OWNER_ID));
//        cvUser.put(userConstants.USER_FLAG,userConstants.USER_OWNER_FLAG);
        if(validation(cv)==1) {
//            if(user.insert_for_real(cv)==false){
//                return null;
//            }
            SQLiteDatabase db = owner.getWritableDatabase();
            long id = db.insert(ownerConstants.OWNER_TABLE, null, cv);
            if (id == -1) {
                Toast.makeText(getContext(), "insert private in owner provider", Toast.LENGTH_LONG).show();
                db.close();
//                user.delete(null,null, new String[]{ownerConstants.OWNER_ID, String.valueOf(userConstants.USER_OWNER_FLAG)});
                return null;
            }
            else{
                Toast.makeText(getContext(), "insert private in owner provider successfully", Toast.LENGTH_LONG).show();

                db.close();
                return ContentUris.withAppendedId(uri, id);
            }
        }
        else
            return null;
    }

    @Override
    public int delete(Uri uri,String selection,String[] selectionArgs) {
        SQLiteDatabase db =owner.getWritableDatabase();
//        userProvider user = new userProvider();
        int match = sUri.match(uri);
        switch (match)
        {
            case case1:
                selection=ownerConstants.OWNER_ID+"=?";
//                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
//                user.delete(null,null, new String[]{String.valueOf(ContentUris.parseId(uri)), String.valueOf(userConstants.USER_OWNER_FLAG)});
                db.delete(ownerConstants.OWNER_TABLE,selection,selectionArgs);
            default:
                Toast.makeText(getContext(), "delete private in owner provider", Toast.LENGTH_LONG).show();
        }
        return 0;
    }
    @Override
    public int update(Uri uri,ContentValues cv, String selection, String[] selectionArgs) {
        SQLiteDatabase db =owner.getWritableDatabase();
        userProvider user = new userProvider();
        ContentValues cvUser = new ContentValues();
        cvUser.put(userConstants.USER_EMAIL,cv.getAsString(ownerConstants.OWNER_EMAIL));
        cvUser.put(userConstants.USER_PASSWORD,cv.getAsString(ownerConstants.OWNER_PASSWORD));
        cvUser.put(userConstants.USER_ID_REAL,cv.getAsString(ownerConstants.OWNER_ID));
        cvUser.put(userConstants.USER_FLAG,userConstants.USER_OWNER_FLAG);
        int match = sUri.match(uri);
        if(validation(cv)==1) {
            switch (match) {
                case case1:
                    if (user.update(null,cv,null,null)==0)
                    {
                        Toast.makeText(getContext(),"update in owner 1 provider",Toast.LENGTH_LONG).show();
                        return 0;
                    }
                    return   db.update(ownerConstants.OWNER_TABLE,cv,selection,selectionArgs);
                case case2:
                    selection = ownerConstants.OWNER_ID+"=?";
                    selectionArgs = new  String[]{String.valueOf(ContentUris.parseId(uri))};
                    if (user.update(null,cv,null,null)==0)
                    {
                        Toast.makeText(getContext(),"update in owner 1 provider",Toast.LENGTH_LONG).show();
                        return 0;
                    }
                    int update = db.update(ownerConstants.OWNER_TABLE, cv, selection, selectionArgs);
                    if(update==0)
                    {
                        Toast.makeText(getContext(),"update in owner 1 provider",Toast.LENGTH_LONG).show();

                        user.delete(null,null, new String[]{ownerConstants.OWNER_ID, String.valueOf(userConstants.USER_OWNER_FLAG)});
                    }
                    return update;

                default:
                    Toast.makeText(getContext(),"update in owner provider",Toast.LENGTH_LONG).show();
            }
        }
        return 0;
    }
    private int validation (ContentValues cv)
    {
        if(cv.getAsString(ownerConstants.OWNER_NAME).isEmpty()) {
            Toast.makeText(getContext(), "insert the NAME", Toast.LENGTH_LONG).show();
            return 0;
        }
        else if(cv.getAsString(ownerConstants.OWNER_EMAIL).isEmpty()){
            Toast.makeText(getContext(), "insert the EMAIL", Toast.LENGTH_LONG).show();
            return 0;
        }
        else if(cv.getAsString(ownerConstants.OWNER_PASSWORD).isEmpty()){
            Toast.makeText(getContext(), "insert the password", Toast.LENGTH_LONG).show();
            return 0;
        }
        else if(cv.getAsString(ownerConstants.OWNER_PHONE).isEmpty()){
            Toast.makeText(getContext(), "insert the phone", Toast.LENGTH_LONG).show();
            return 0;
        }
        else if(cv.getAsString(ownerConstants.OWNER_ADDRESS).isEmpty()){
            Toast.makeText(getContext(), "insert the owner_ADDRESS", Toast.LENGTH_LONG).show();
            return 0;
        }

        else return 1;

    }
}
