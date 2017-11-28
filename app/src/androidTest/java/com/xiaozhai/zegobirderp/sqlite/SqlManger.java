package com.xiaozhai.zegobirderp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.xiaozhai.zegobirderp.utils.SqlHelpter;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by nadal on 2017/11/27.
 */
@RunWith(AndroidJUnit4.class)
public class SqlManger {
    private Context appContext = InstrumentationRegistry.getTargetContext();
    private SqlHelpter mSqlHelpter=new SqlHelpter(appContext);

    @Test
    public void testAdd(){
        SQLiteDatabase db = mSqlHelpter.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", 7);
        contentValues.put("CustomName", "Jne");
        contentValues.put("OrderPrice", 700);
        contentValues.put("Country", "China");
        db.insert(SqlHelpter.TABLE_NORMAL_INPUT,null,contentValues);
    }

    @Test
    public void testQuery(){
        SQLiteDatabase db = mSqlHelpter.getWritableDatabase();
        Cursor cursor = db.query(SqlHelpter.TABLE_NORMAL_INPUT, null, "Id=?", new String[]{"7"}, null,
                null, null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                System.out.println(cursor.getColumnName(0)+":"+cursor.getString(0));
                for(int i=0;i<cursor.getColumnCount();i++){
                    System.out.print(cursor.getColumnName(i)+":"+cursor.getString(i)+" - ");
                }
                System.out.println("");
            }
        }
        db.close();
    }

    @Test
    public void testDeleteAll(){
        SQLiteDatabase db = mSqlHelpter.getReadableDatabase();
        db.delete(SqlHelpter.TABLE_NORMAL_INPUT,null,null);
        db.close();
    }
}
