package com.xiaozhai.zegobirderp.utils;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nadal on 2017/11/10.
 */


public class SpUtils {

    private static String packageName=ContentProviderOperation.Builder.class.getPackage().getName();

    public static void putString(Context context,String key,String value){
        SharedPreferences sp = context.getSharedPreferences(packageName, Context
                .MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key,value).commit();
    }

    public static String getString(Context context,String key,String defaultValue){
        SharedPreferences sp = context.getSharedPreferences(packageName, Context
                .MODE_PRIVATE);
        return sp.getString(key,defaultValue);
    }

}
