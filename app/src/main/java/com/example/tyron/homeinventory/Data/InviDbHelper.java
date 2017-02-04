package com.example.tyron.homeinventory.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tyron.homeinventory.Data.InviContract.InviEntry;

/**
 * Created by tyron on 28/11/2016.
 */

public class InviDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = InviDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "Invi.db";

    private static final int DATABASE_VERSION = 1;

    public InviDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
        public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_INVI_TABLE =  "CREATE TABLE " + InviEntry.TABLE_NAME + " ("
                + InviEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InviEntry.COLUMN_ITEM_NAME + " TEXT NOT NULL, "
                + InviEntry.COLUMN_ITEM_LOCATION+ " TEXT, "
                + InviEntry.COLUMN_ITEM_NOTES + " TEXT, "
                + InviEntry.COLUMN_ITEM_QTY + " INTEGER NOT NULL DEFAULT 0, "
                + InviEntry.COLUMN_ITEM_PICID + " INTEGER);";

        db.execSQL(SQL_CREATE_INVI_TABLE);
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
