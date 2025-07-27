package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class mealData extends SQLiteOpenHelper {
    public mealData(@Nullable Context context){
        super(context,"meal.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Sql ="CREATE TABLE " +mealConstant.MEAL_TABLE+
                " ( "+
                mealConstant.MEAL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+
                mealConstant.MEAL_NAME+" TEXT , "+
                mealConstant.MEAL_PRICE+" TEXT , "+

                mealConstant.MEAL_DESCRIBE+" TEXT , "+
                mealConstant.MEAL_RESTURANT_ID+" TEXT ,"+
                mealConstant.MEAL_IMAGE+" blob "+
                " )";
        db.execSQL(Sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
