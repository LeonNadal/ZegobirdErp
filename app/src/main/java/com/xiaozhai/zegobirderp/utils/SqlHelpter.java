package com.xiaozhai.zegobirderp.utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nadal on 2017/11/27.
 */

public class SqlHelpter extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String TEST_DB_NAME = "myTest.db";
    public static final String TABLE_NORMAL_INPUT = "normal_input";
    public static final String test_table="test_1";



    public SqlHelpter(Context context, String name, SQLiteDatabase.CursorFactory factory, int
            version) {
        super(context, name, factory, version);
    }

    public SqlHelpter(Context context){
        super(context,TEST_DB_NAME,null,DB_VERSION);
    }

    public SqlHelpter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists " + TABLE_NORMAL_INPUT + " (Id integer primary key, CustomName text, OrderPrice integer, Country text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NORMAL_INPUT;
        db.execSQL(sql);
        onCreate(db);
    }


}
