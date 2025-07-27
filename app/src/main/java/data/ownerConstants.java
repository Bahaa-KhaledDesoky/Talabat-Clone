package data;

import android.net.Uri;

public class ownerConstants {
    public static final String OWNER_ID =" ido ";
    public static final String OWNER_TABLE ="owner";
    public static final String OWNER_NAME ="namefo";
    public static final String OWNER_EMAIL ="emailo";
    public static final String OWNER_PASSWORD ="passwordo";
    public static final String OWNER_ADDRESS ="addresso";
    public static final String OWNER_PHONE ="phoneo";
    ///////////////////////////////////////////
    public static final String OWNER_AUTHORITY = "com.example.android.ownerData";
    public static final Uri OWNER_URI_BASE = Uri.parse("content://"+OWNER_AUTHORITY);
    public static final Uri OWNER_URI = Uri.withAppendedPath(OWNER_URI_BASE,OWNER_TABLE);
}
