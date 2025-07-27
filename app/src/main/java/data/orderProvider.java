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

public class orderProvider extends ContentProvider {
    orderData order ;
    private static final int case1 = 100;
    private static final int case2 = 101;
    private static final UriMatcher sUri = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        // in case of all order
        sUri.addURI(orderConstant.ORDER_AUTHORITY,orderConstant.ORDER_TABLE,case1);
        // in case of one order
        sUri.addURI(orderConstant.ORDER_AUTHORITY,orderConstant.ORDER_TABLE+"/#",case2);
    }
    @Override
    public boolean onCreate() {
        order=new orderData(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri,String[] projection,String selection, String[] selectionArgs,String sort) {
        SQLiteDatabase db = order.getReadableDatabase();
        Cursor cursor = null;
        cursor =db.query(orderConstant.ORDER_TABLE,projection,selection,selectionArgs,null,null,sort);
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
        SQLiteDatabase db = order.getReadableDatabase();
        int match = sUri.match(uri);
        Cursor cursor = null;
        switch (match) {
            case case1:
                return insert_for_real(uri, cv);
            case case2:
                Toast.makeText(getContext(), "insert case 1 in order provider", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(getContext(),"insert in ownerHasR provider",Toast.LENGTH_LONG).show();
        }
        return null;
    }

        private Uri insert_for_real(Uri uri, ContentValues cv) {
            SQLiteDatabase db = order.getWritableDatabase();

            if(1==1) {
                long id = db.insert(orderConstant.ORDER_TABLE, null, cv);
                if (id == -1) {
                    Toast.makeText(getContext(), "insert private in order provider", Toast.LENGTH_LONG).show();
                    return null;
                }
                else
                    return ContentUris.withAppendedId(uri, id);
            }
            else
                return null;
        }
    private int validation(ContentValues cv) {

        if(null==cv.getAsString(orderConstant.ORDER_IDC)) {
            Toast.makeText(getContext(), "insert the ORDER_IDC", Toast.LENGTH_LONG).show();
            return 0;
        }
        else if(null==cv.getAsString(orderConstant.ORDER_IDR)) {
            Toast.makeText(getContext(), "insert the OWNER_HAS_R_IDR", Toast.LENGTH_LONG).show();
            return 0;
        }
        else if(null==cv.getAsString(orderConstant.ORDER_IDM)) {
            Toast.makeText(getContext(), "insert the ORDER_IDM", Toast.LENGTH_LONG).show();
            return 0;
        }
        else return 1;
    }

    @Override
    public int delete(Uri uri,String selection,String[] selectionArgs) {
        SQLiteDatabase db =order.getWritableDatabase();
        int match = sUri.match(uri);
        switch (match)
        {
            case case1:
                db.delete(orderConstant.ORDER_TABLE,selection,selectionArgs);
            case case2:
                selection=orderConstant.ORDER_IDR+"=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                db.delete(orderConstant.ORDER_TABLE,selection,selectionArgs);
            default:
                Toast.makeText(getContext(), "delete private in order provider", Toast.LENGTH_LONG).show();
        }
        return 0;
    }

    @Override
    public int update(Uri uri,ContentValues cv, String selection, String[] selectionArgs) {
        if (validation(cv)==1)
        {
            ///////////////chk hear
            SQLiteDatabase db = order.getWritableDatabase();
            selection = orderConstant.ORDER_ID+" =?";
            return   db.update(orderConstant.ORDER_TABLE,cv,selection,selectionArgs);
        }
        else
            Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
        return 0;
    }
}
