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

public class mealProvider extends ContentProvider {
    mealData meal;
    private static final int case1 = 100;
    private static final int case2 = 101;
    private static final UriMatcher sUri = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        // in case of all order
        sUri.addURI(mealConstant.MEAL_AUTHORITY, mealConstant.MEAL_TABLE, case1);
        // in case of one order
        sUri.addURI(mealConstant.MEAL_AUTHORITY, mealConstant.MEAL_TABLE + "/#", case2);
    }

    @Override
    public boolean onCreate() {
        meal = new mealData(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sort) {
        SQLiteDatabase db = meal.getReadableDatabase();
        Cursor cursor = null;
        cursor = db.query(mealConstant.MEAL_TABLE, projection, selection, selectionArgs, null, null, sort);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues cv) {
        SQLiteDatabase db = meal.getReadableDatabase();
        int match = sUri.match(uri);
        Cursor cursor = null;
        switch (match) {
            case case1:
                return insert_for_real(uri, cv);

            case case2:
                Toast.makeText(getContext(), "insert case 1 in order provider", Toast.LENGTH_LONG).show();
                break;

            default:
                Toast.makeText(getContext(), "insert in ownerHasR provider", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    private Uri insert_for_real(Uri uri, ContentValues cv) {
        SQLiteDatabase db = meal.getWritableDatabase();

        if (validation(cv) == 1) {

            long id = db.insert(mealConstant.MEAL_TABLE, null, cv);
            if (id == -1) {
                Toast.makeText(getContext(), "insert private in order provider", Toast.LENGTH_LONG).show();
                return null;
            } else
                return ContentUris.withAppendedId(uri, id);
        } else
            return null;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = meal.getWritableDatabase();
//        int match = sUri.match(uri);
        //switch (match)
//        {
//            case case1:
//                db.delete(mealConstant.MEAL_TABLE,selection,selectionArgs);
//            case case2:
        selection = mealConstant.MEAL_ID + "=?";
        int ch = db.delete(mealConstant.MEAL_TABLE, selection, selectionArgs);
//            default:
//                Toast.makeText(getContext(), "delete private in order provider", Toast.LENGTH_LONG).show();
//        }
        if (ch != 0)
            //        return 0;
        return  ch;
        else
            return 0;
    }

    @Override
    public int update(Uri uri,ContentValues cv, String selection, String[] selectionArgs) {
        if (validation(cv)==1)
        {
            ///////////////chk hear
            SQLiteDatabase db = meal.getWritableDatabase();
            selection = mealConstant.MEAL_ID+" =?";
            return   db.update(mealConstant.MEAL_TABLE,cv,selection,selectionArgs);
        }
        else
            Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();

        return 0;
    }
    private int validation(ContentValues cv) {
        if(null==cv.getAsString(mealConstant.MEAL_NAME)) {
            Toast.makeText(getContext(), "insert the ORDER_IDC", Toast.LENGTH_LONG).show();
            return 0;
        }
        else if(null==cv.getAsString(mealConstant.MEAL_PRICE)) {
            Toast.makeText(getContext(), "insert the OWNER_HAS_R_IDR", Toast.LENGTH_LONG).show();
            return 0;
        }
        else if(null==cv.getAsString(mealConstant.MEAL_DESCRIBE)) {
            Toast.makeText(getContext(), "insert the ORDER_IDM", Toast.LENGTH_LONG).show();
            return 0;
        }
        else return 1;

    }
}
