package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class orderData extends SQLiteOpenHelper {
    public orderData(Context context) {
        super(context,"order.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Sql ="CREATE TABLE " +orderConstant.ORDER_TABLE+
                " ( "+
                orderConstant.ORDER_IDC+" TEXT , "+
                orderConstant.ORDER_COMMENT+" TEXT , "+
                orderConstant.ORDER_NAME+" TEXT , "+
                orderConstant.ORDER_PRICE+" TEXT , "+
                orderConstant.ORDER_NUMOFMEALS+" TEXT , "+
                orderConstant.ORDER_IDR+" TEXT , "+
                orderConstant.ORDER_IDM+" TEXT , "+
                orderConstant.ORDER_FLAG+" TEXT , "+

                orderConstant.ORDER_ID+" INTEGER Primary KEY AUTOINCREMENT ,"+
                orderConstant.ORDER_IMAGE+" blob ,"+
                orderConstant.ORDER_date+" TEXT "+

                " )";
        db.execSQL(Sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
