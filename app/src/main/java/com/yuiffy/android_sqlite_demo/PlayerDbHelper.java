package com.yuiffy.android_sqlite_demo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yuiffy.android_sqlite_demo.MyContract;

import java.security.AccessControlContext;
import java.util.ArrayList;
import java.util.List;

public class PlayerDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PlayerDemo.db";

    private static final String TEXT_TYPE = " TEXT";//TODO: test the integer type
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MyContract.PlayerEntry.TABLE_NAME + " (" +
                    MyContract.PlayerEntry._ID + " INTEGER PRIMARY KEY," +
                    MyContract.PlayerEntry.COLUMN_NAME_PLAYER_NAME + TEXT_TYPE + COMMA_SEP +
                    MyContract.PlayerEntry.COLUMN_NAME_SEX + TEXT_TYPE + COMMA_SEP +
                    MyContract.PlayerEntry.COLUMN_NAME_AGE + TEXT_TYPE +
                    " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MyContract.PlayerEntry.TABLE_NAME;

    public PlayerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public Cursor getAllPlayerCursor(SQLiteDatabase db) {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                MyContract.PlayerEntry._ID,
                MyContract.PlayerEntry.COLUMN_NAME_PLAYER_NAME,
                MyContract.PlayerEntry.COLUMN_NAME_SEX,
                MyContract.PlayerEntry.COLUMN_NAME_AGE
        };
        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                MyContract.PlayerEntry.COLUMN_NAME_PLAYER_NAME + " DESC";


        Cursor c = db.query(
                MyContract.PlayerEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        return c;
    }

    public List<String> getAllPlayerName(SQLiteDatabase db) {
        Cursor c = getAllPlayerCursor(db);
        c.moveToFirst();
        List<String> list = new ArrayList<String>();
        while (c.isAfterLast() == false) {
            String name = c.getString(c.getColumnIndexOrThrow(MyContract.PlayerEntry.COLUMN_NAME_PLAYER_NAME));
            list.add(name);
            c.moveToNext();
        }
        return list;
    }
}