package data;

import android.net.Uri;

public class orderConstant {
    public static final String ORDER_IDC ="idcc";
    public static final String ORDER_TABLE ="ord";
    public static final String ORDER_NAME ="name";
    public static final String ORDER_PRICE ="price";
    public static final String ORDER_NUMOFMEALS ="numofmeals";
    public static final String ORDER_IDR ="idr";
    public static final String ORDER_IDM ="idm";
    public static final String ORDER_COMMENT ="comment";
    public static final String ORDER_FLAG ="flag";
    public static final String ORDER_ACCPETED ="1";
    public static final String ORDER_INCART ="0";
    public static final String ORDER_ID ="id";
    public static final String ORDER_IMAGE ="image";
    public static final String ORDER_date ="date";
    ///////////////////////////////////////////////
    public static final String ORDER_AUTHORITY = "com.example.android.orderData";
    public static final Uri ORDER_URI_BASE = Uri.parse("content://"+ORDER_AUTHORITY);
    public static final Uri ORDER_URI = Uri.withAppendedPath(ORDER_URI_BASE,ORDER_TABLE);
}
