package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class resturantData extends SQLiteOpenHelper
{

    public resturantData(@Nullable Context context){
        super(context,"resturant.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Sql ="CREATE TABLE " +resturantConstant.RESTURANT_TABLE+
                " ( "+
                resturantConstant.RESTURANT_ID+" INTEGER Primary KEY , "+
                resturantConstant.RESTURANT_ADDRESS+" TEXT UNIQUE, "+
                resturantConstant.RESTURANT_DESCRIPTION+" TEXT , "+
                resturantConstant.RESTURANT_NAME+" TEXT UNIQUE, "+
                resturantConstant.RESTURANT_OPENING_HOURS+" TEXT , "+
                resturantConstant.RESTURANT_PHONE+" TEXT , "+
                resturantConstant.RESTURANT_IMAGE+" blob "+

                " )";
        db.execSQL(Sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
