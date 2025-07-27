package data;

import android.net.Uri;

public class mealConstant {

    public static final String  MEAL_NAME ="name_meal";
    public static final String  MEAL_ID ="idm";
    public static final String  MEAL_TABLE ="meal";
    public static final String  MEAL_RESTURANT_ID ="idr";
    public static final String  MEAL_PRICE ="price";
    public static final String  MEAL_DESCRIBE ="describe";
    public static final String  MEAL_IMAGE ="image";
    /////////////////////////////////////////////////////////////////////////////
    public static final String MEAL_AUTHORITY = "com.example.android.mealData";
    public static final Uri MEAL_URI_BASE = Uri.parse("content://"+MEAL_AUTHORITY);
    public static final Uri MEAL_URI = Uri.withAppendedPath(MEAL_URI_BASE,MEAL_TABLE);

}
