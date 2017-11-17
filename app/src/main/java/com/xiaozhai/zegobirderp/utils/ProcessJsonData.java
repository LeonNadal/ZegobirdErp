package com.xiaozhai.zegobirderp.utils;

import com.google.gson.Gson;

/**
 * Created by Nadal on 2017/11/4.
 */

public class ProcessJsonData {
    private static Gson mGson=new Gson();
    public static  <T> T processData(String jsonStr,Class<T> clazz){
        T t = mGson.fromJson(jsonStr, clazz);
        System.out.println("对象："+t);
        return t;
    }
}
