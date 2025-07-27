package data;

import android.net.Uri;

//import java.net.URI;

public class customerConstants {

    public static final String CUSTOMER_ID ="idc";
    public static final String CUSTOMER_TABLE ="customerr";
    public static final String CUSTOMER_NAME ="namefc";
    public static final String CUSTOMER_EMAIL ="emailc";
    public static final String CUSTOMER_PASSWORD ="passwordc";
    public static final String CUSTOMER_ADDRESS ="addressc";
    public static final String CUSTOMER_PHONE ="phonec";
/////////////////////////////////////////////////////////////////////////////
    public static final String CUSTOMER_AUTHORITY = "com.example.android.customerData";
    public static final Uri CUSTOMER_URI_BASE = Uri.parse("content://"+CUSTOMER_AUTHORITY);
    public static final Uri CUSTOMER_URI = Uri.withAppendedPath(CUSTOMER_URI_BASE,CUSTOMER_TABLE);


}
