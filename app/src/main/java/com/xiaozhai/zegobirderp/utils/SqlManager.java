package com.xiaozhai.zegobirderp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by nadal on 2017/11/27.
 */

public class SqlManager<T> {

    SqlHelpter helpter=null;
    SQLiteDatabase db = null;

    public SqlManager(Context context){
        helpter=new SqlHelpter(context);
        db=helpter.getWritableDatabase();
    }

    /**
     * 数据添加
     * @param tableName 表格名
     * @param nullColumnHack 所要插入的列名
     * @param selection  所要插入的值
     */
    public  void insert(String tableName,String nullColumnHack, WeakHashMap<String,String> selection){

        ContentValues ContentValues=new ContentValues();
        for (Map.Entry<String,String>  item : selection.entrySet() ) {
        	ContentValues.put(item.getKey(),item.getValue());
        }
        db.insert(tableName,null,ContentValues);
    }

    /**
     * 删除所有该表格的数据
     * @param tableName 表格名
     */
    public  void deleteAll( String tableName){
        db.delete(tableName,null,null);
    }

    /**
     * 关闭cursor
     */
    public void close(){
        db.close();
    }

    /**
     * Convenience method for updating rows in the database.
     *
     * @param tableName the table to update in
     * @param contentValues a map from column names to new column values. null is a
     *            valid value that will be translated to NULL.
     * @param whereClause the optional WHERE clause to apply when updating.
     *            Passing null will update all rows.
     * @param whereArgs You may include ?s in the where clause, which
     *            will be replaced by the values from whereArgs. The values
     *            will be bound as Strings.
     */
    public  void update(String tableName,WeakHashMap<String,String> contentValues, String whereClause, String[]
            whereArgs){
        ContentValues ContentValues=new ContentValues(contentValues.size());
        for(Map.Entry<String,String> item: contentValues.entrySet()){
            ContentValues.put(item.getKey(),item.getValue());
        }
        db.update(tableName, ContentValues, whereClause, whereArgs);
    }


    public  Cursor queryAll(String tableName, String selection, String[]
            seletionArgs){
        Cursor cursor = db.query(tableName, null, selection, seletionArgs, null, null, null);
        return cursor;
    }

    public  ArrayList<T> queryAll( String tableName, String selection, String[]
            seletionArgs, Class<T> clazz) throws Exception {

        Field[] fields = clazz.getDeclaredFields();
        String[] fds=new String[fields.length];
        ArrayList<T> list=null;
        for(int i=0;i<fds.length;i++){
            fds[i]=fields[i].getName();
        }
        Cursor cursor = queryAll( tableName, selection, seletionArgs);
        if(cursor!=null){
            list = new ArrayList<>();
            while(cursor.moveToNext()) {
                T t = clazz.newInstance();

                for(int i=0;i<fds.length;i++){
                    Field field = fields[i];
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    if(type==String.class){
                        String value =cursor.getString(cursor.getColumnIndex(fds[i]));
                        field.set(t,value);
                    }else if(type==int.class){
                        int value=cursor.getInt(cursor.getColumnIndex(fds[i]));
                        field.set(t,value);
                    }
                }
                list.add(t);
            }
        }
        return list;
    }

}
