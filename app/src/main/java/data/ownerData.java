package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ownerData extends SQLiteOpenHelper {
    public ownerData(@Nullable Context context) {
        super(context,"owner.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String Sql ="CREATE TABLE " +ownerConstants.OWNER_TABLE+
                " ( "+
                ownerConstants.OWNER_ID+" INTEGER Primary KEY AUTOINCREMENT , "+
                ownerConstants.OWNER_EMAIL+" TEXT , "+
                ownerConstants.OWNER_PASSWORD+" TEXT , "+
                ownerConstants.OWNER_NAME+" TEXT , "+
                ownerConstants.OWNER_ADDRESS+" TEXT , "+
                ownerConstants.OWNER_PHONE+" TEXT "+
                " )";
        db.execSQL(Sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
