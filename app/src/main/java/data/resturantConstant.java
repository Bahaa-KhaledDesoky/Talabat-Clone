package data;

import android.net.Uri;

public class resturantConstant {

    public static final String RESTURANT_ID ="idr";
    public static final String RESTURANT_TABLE ="resturant";
    public static final String RESTURANT_NAME ="namer";
    public static final String RESTURANT_DESCRIPTION ="describer";
    public static final String RESTURANT_ADDRESS ="addressr";
    public static final String RESTURANT_PHONE ="phoner";
    public static final String RESTURANT_IMAGE ="image";
    public static final String RESTURANT_OPENING_HOURS ="opening";
    ////////////////////////////////////////////////////////////////////
    public static final String RESTURANT_AUTHORITY = "com.example.android.resturantData";
    public static final Uri RESTURANT_URI_BASE = Uri.parse("content://"+RESTURANT_AUTHORITY);
    public static final Uri RESTURANT_URI = Uri.withAppendedPath(RESTURANT_URI_BASE,RESTURANT_TABLE);
}
