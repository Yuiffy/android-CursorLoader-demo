package com.yuiffy.android_sqlite_demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by yuiff on 2016/10/13.
 * This is learned from sdk/docs/training/basics/data-storage/databases.html
 */


public final class MyContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public MyContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class PlayerEntry implements BaseColumns {
        public static final String TABLE_NAME = "player_entry";
        public static final String COLUMN_NAME_PLAYER_NAME = "player_name";
        public static final String COLUMN_NAME_SEX = "sex";
        public static final String COLUMN_NAME_AGE = "age";
    }


}
