package data;

import android.net.Uri;

public class ownerHasRConstants {
    public static final String OWNER_HAS_R_IDO =" ido ";
    public static final String OWNER_HAS_R_IDR="idr";
    public static final String OWNER_HAS_R_TABLE ="ownerHasR";
    ///////////////////////////////////////////////////////////////
    public static final String OWNER_HAS_R_AUTHORITY = "com.example.android. ownerHasRData";
    public static final Uri OWNER_HAS_R_URI_BASE = Uri.parse("content://"+OWNER_HAS_R_AUTHORITY);
    public static final Uri OWNER_HAS_R_URI = Uri.withAppendedPath(OWNER_HAS_R_URI_BASE,OWNER_HAS_R_TABLE);
}
