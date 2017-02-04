package com.example.tyron.homeinventory.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.tyron.homeinventory.Data.InviContract.InviEntry;

/**
 * Created by tyron on 28/11/2016.
 */

public class InviProvider extends ContentProvider {

    public static final String LOG_TAG = InviProvider.class.getSimpleName();

    private static final int INVI = 100;
    private static final int INVI_ID = 101;

    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(InviContract.CONTENT_AUTHORITY, InviContract.PATH_ITEMS, INVI);

        sUriMatcher.addURI(InviContract.CONTENT_AUTHORITY, InviContract.PATH_ITEMS + "/#", INVI_ID);
    }

    private  InviDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new InviDbHelper(getContext());

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);

        switch (match){
            case INVI:
                cursor = database.query(InviEntry.TABLE_NAME, projection, selection, selectionArgs, null,null, sortOrder);
                break;
            case INVI_ID:
                selection = InviEntry._ID+ "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(InviEntry.TABLE_NAME, projection, selection, selectionArgs, null,null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }


        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        final int match = sUriMatcher.match(uri);

        switch (match){
            case INVI:
                return insertInvi(uri, values);

            default:
            throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }

    }

    public Uri insertInvi(Uri uri, ContentValues values){
        String name = values.getAsString(InviEntry.COLUMN_ITEM_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Item requires a name");
        }

        Integer qty = values.getAsInteger(InviEntry.COLUMN_ITEM_QTY);
        if (qty == null || qty < 0) {
            throw new IllegalArgumentException("Item requires a valid Quantity");
        }
        SQLiteDatabase database = mDbHelper.getWritableDatabase();


        long id = database.insert(InviEntry.TABLE_NAME, null, values);

        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        return ContentUris.withAppendedId(uri, id);


    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case INVI:
                return updateInvi(uri, contentValues, selection, selectionArgs);
            case INVI_ID:
                selection = InviEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri))};

                return updateInvi(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateInvi(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        // TODO: 30/11/2016  update this space add return statement
    return 1;

    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        // TODO: 30/11/2016 implement delete
        return 0;
    }


    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }


}
