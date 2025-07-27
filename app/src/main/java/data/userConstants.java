package data;

import android.net.Uri;

public class userConstants {

    public static final String USER_ID_REAL = "idur";
    public static final String USER_CUSTOMER_FLAG = "1" ;
    public static final String USER_OWNER_FLAG = "0";
    public static final String USER_TABLE = "users";
    public static final String USER_FLAG = "flag";
    public static final String USER_EMAIL = "emailu";
    public static final String USER_PASSWORD = "passwordu";
    ///////////////////////////////////////////////////////////
    public static final String USER_AUTHORITY = "com.example.android.userData";
    public static final Uri USER_URI_BASE = Uri.parse("content://"+USER_AUTHORITY);
    public static final Uri USER_URI = Uri.withAppendedPath(USER_URI_BASE,USER_TABLE);
}
