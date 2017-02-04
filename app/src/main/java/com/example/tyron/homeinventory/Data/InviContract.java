package com.example.tyron.homeinventory.Data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by tyron on 28/11/2016.
 */

public final class InviContract {

    public static final String CONTENT_AUTHORITY = "com.example.tyron.homeinvnetory";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_ITEMS = "items";

    private InviContract() {}

    public static final class InviEntry implements BaseColumns{

        public static final String TABLE_NAME = "InviItems";

        public final static String _ID = BaseColumns._ID;

        public static final String COLUMN_ITEM_PICID = "pic_ID";

        public static final String COLUMN_ITEM_NAME = "name";

        public static final String COLUMN_ITEM_QTY = "quantity";

        public static final String COLUMN_ITEM_LOCATION = "location";

        public static final String COLUMN_ITEM_NOTES = "notes";

    }


}
