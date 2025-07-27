package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class userData extends SQLiteOpenHelper {
    public userData(@Nullable Context context ) {
        super(context,"users.db" ,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Sql ="CREATE TABLE " +userConstants.USER_TABLE+
                " ( "+
                userConstants.USER_EMAIL+" TEXT PRIMARY KEY , "+
                userConstants.USER_PASSWORD+" TEXT , "+
                userConstants.USER_FLAG+" TEXT , "+
                userConstants.USER_ID_REAL+" TEXT"+" )";
        db.execSQL(Sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
