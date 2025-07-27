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

public class ownerHasRProvider extends ContentProvider {
    ownerHasRData ownerHasR ;
    private static final int case1 = 100;
    private static final int case2 = 101;
    private static final UriMatcher sUri = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        // in case of all ownerHasR
        sUri.addURI(ownerHasRConstants.OWNER_HAS_R_AUTHORITY,ownerHasRConstants.OWNER_HAS_R_TABLE,case1);
        // in case of one ownerHasR
        sUri.addURI(ownerHasRConstants.OWNER_HAS_R_AUTHORITY,ownerHasRConstants.OWNER_HAS_R_TABLE+"/#",case2);
    }
    @Override
    public boolean onCreate() {
        ownerHasR=new ownerHasRData(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri,String[] projection,String selection, String[] selectionArgs,String sort) {
        SQLiteDatabase db = ownerHasR.getReadableDatabase();
        int match = sUri.match(uri);
        Cursor cursor = null;
        switch (match) {
            case case1 :
                cursor =db.query(ownerHasRConstants.OWNER_HAS_R_TABLE,projection,selection,selectionArgs,null,null,sort);
                break;
            case case2 :
                selection = ownerHasRConstants.OWNER_HAS_R_IDO+"=?";
                selectionArgs= new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor=db.query(ownerHasRConstants.OWNER_HAS_R_TABLE,projection,selection,selectionArgs,null,null,sort);
                break;
            default:
                Toast.makeText(getContext(),"query in ownerHasR provider",Toast.LENGTH_LONG).show();
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
        SQLiteDatabase db = ownerHasR.getReadableDatabase();
        int match = sUri.match(uri);
        Cursor cursor = null;
        switch (match) {
            case case1 :
                Toast.makeText(getContext(),"insert case 1 in ownerHasR provider",Toast.LENGTH_LONG).show();

                break;
            case case2 :
                return insert_for_real(uri,cv);
            default:
                Toast.makeText(getContext(),"insert in ownerHasR provider",Toast.LENGTH_LONG).show();
        }
        return null;

    }

    private Uri insert_for_real(Uri uri, ContentValues cv) {
        SQLiteDatabase db = ownerHasR.getWritableDatabase();

        if(validation(cv)==1) {

            long id = db.insert(ownerHasRConstants.OWNER_HAS_R_TABLE, null, cv);
            if (id == -1) {
                Toast.makeText(getContext(), "insert private in ownerHasR provider", Toast.LENGTH_LONG).show();
                return null;
            }
            else
                return ContentUris.withAppendedId(uri, id);
        }
        else
            return null;
    }

    private int validation(ContentValues cv) {

        if(null==cv.getAsString(ownerHasRConstants.OWNER_HAS_R_IDO)) {
            Toast.makeText(getContext(), "insert the OWNER_HAS_R_IDO", Toast.LENGTH_LONG).show();
            return 0;
        }
        else if(null==cv.getAsString(ownerHasRConstants.OWNER_HAS_R_IDR)) {
            Toast.makeText(getContext(), "insert the OWNER_HAS_R_IDR", Toast.LENGTH_LONG).show();
            return 0;
        }
        else return 1;
    }




    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
