package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ownerHasRData extends SQLiteOpenHelper {
    public ownerHasRData (@Nullable Context context) {
        super(context,"customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Sql ="CREATE TABLE " +ownerHasRConstants.OWNER_HAS_R_TABLE+
                " ( "+
                ownerHasRConstants.OWNER_HAS_R_IDO+" INTGER , "+
                ownerHasRConstants.OWNER_HAS_R_IDR+" INTGER "+
                " )";
        db.execSQL(Sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
