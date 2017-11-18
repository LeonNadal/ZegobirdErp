package com.xiaozhai.zegobirderp.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.xiaozhai.zegobirderp.bean.LoginResultBean;
import com.xiaozhai.zegobirderp.common.SpContants;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Nadal on 2017/11/4.
 */

public class HttpUtils {
    public static OkHttpClient client = new OkHttpClient();
    /**
     * post方式提交json
     * @param url
     * @param str      提交的json
     * @param onComplish  成功与否回调借口，必须实现
     */
   public static void post(Context context,String url, String str, final OnComplish onComplish) {

       //创建OkHttpClient对象。
       MediaType JSON = MediaType.parse("application/json;charset=utf-8");//数据类型为json格式，
       RequestBody body = RequestBody.create(JSON, str);
       String id = SpUtils.getString(context, SpContants.STAFFID,"");
       String signToken = SpUtils.getString(context, SpContants.SIGNTOKEN, "");
       String timestamp = getTime();
       String nonce = getNor()+"";
       String signature = md5(timestamp + nonce + id + signToken);
       System.out.println("ddddd--"+id+":"+signature+":"+timestamp+":"+nonce+":"+signature+":"+signToken);
       Request request = new Request.Builder()
               .url(url)
               .post(body).addHeader("staffId",id)
               .addHeader("timestamp",timestamp)
               .addHeader("nonce",nonce)
               .addHeader("signToken",signToken)
               .addHeader("signature",signature)
               .build();
       client.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {
               Log.d("失败了","---"+e.getMessage());
               onComplish.onFailure(e.getMessage());
           }

           @Override
           public void onResponse(Call call, final Response response) throws IOException {
               String data=response.body().string();
               Log.d("success:--->",data);
               onComplish.onResponse(data);
           }
       });

   }

    public static Gson gson=new Gson();
   public static void post(Context context,String url, Map<String,Object> jsonMap, final OnComplish onComplish) {
       String jsonStr = gson.toJson(jsonMap);
       //创建OkHttpClient对象。
       MediaType JSON = MediaType.parse("application/json;charset=utf-8");//数据类型为json格式，
       RequestBody body = RequestBody.create(JSON, jsonStr);
       String id = SpUtils.getString(context, SpContants.STAFFID,"");
       String signToken = SpUtils.getString(context, SpContants.SIGNTOKEN, "");
       String timestamp = getTime();
       String nonce = getNor()+"";
       String signature = md5(timestamp + nonce + id + signToken);
       System.out.println("ddddd--"+id+":"+signature+":"+timestamp+":"+nonce+":"+signature+":"+signToken);
       Request request = new Request.Builder()
               .url(url)
               .post(body).addHeader("staffId",id)
               .addHeader("timestamp",timestamp)
               .addHeader("nonce",nonce)
               .addHeader("signToken",signToken)
               .addHeader("signature",signature)
               .build();
       client.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {
               Log.d("失败了","---"+e.getMessage());
               onComplish.onFailure(e.getMessage());
           }

           @Override
           public void onResponse(Call call, final Response response) throws IOException {
               String data=response.body().string();
               Log.d("success:--->",data);
               onComplish.onResponse(data);
           }
       });

   }

    /**
     *
     * post方式提交json
     * @param url
     * @param str      提交的json
     * @param map      header键值对
     * @param onComplish  成功与否回调借口，必须实现
     */
   public static void post(Context context, String url, String str, Map<String,String> map, final OnComplish onComplish) {

       //创建OkHttpClient对象。
       MediaType JSON = MediaType.parse("application/json;charset=utf-8");//数据类型为json格式，
       RequestBody body = RequestBody.create(JSON, str);
       String id = SpUtils.getString(context, SpContants.STAFFID,"");
       String signToken = SpUtils.getString(context, SpContants.SIGNTOKEN, "");
       String timestamp = getTime();
       String nonce = getNor()+"";
       String signature = md5(timestamp + nonce + id + signToken);
       System.out.println("ddddd--"+id+":"+signature+":"+timestamp+":"+nonce+":"+signature+":"+signToken);
       Request.Builder builder=new Request.Builder()
               .url(url)
               .post(body);
       if(map!=null) {
           for (Map.Entry<String,String>  item: map.entrySet() ) {
           	    builder.addHeader(item.getKey(),item.getValue());
           }
       }
       Request request = builder.addHeader("staffId",id)
               .addHeader("timestamp",timestamp)
               .addHeader("nonce",nonce)
               .addHeader("signToken",signToken)
               .addHeader("signature",signature)
               .build();
       client.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {
               Log.d("失败了","---"+e.getMessage());
               onComplish.onFailure(e.getMessage());
           }

           @Override
           public void onResponse(Call call, final Response response) throws IOException {
               String data=response.body().string();
               Log.d("success:--->",data);
               onComplish.onResponse(data);
           }
       });

   }



    public interface OnComplish{
         void onFailure(String errMsg);
         void onResponse(String resultStr);
    }

    public static void login(final Context context){
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");//数据类型为json格式，
        RequestBody body = RequestBody.create(JSON, "{\n" +
                "    \"UserName\": \"admin\",\n" +
                "    \"UserPwd\": \"mdb123\"\n" +
                "}");
        Request request = new Request.Builder()
                .url("http://119.29.90.197:8087/api/User/Login")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("失败了","---"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String data=response.body().string();
                LoginResultBean loginResultBean = ProcessJsonData.processData(data,
                        LoginResultBean.class);
                SpUtils.putString(context,SpContants.STAFFID,loginResultBean.getData().getStaffId());
                SpUtils.putString(context,SpContants.SIGNTOKEN,loginResultBean.getData().getSignToken());
                SpUtils.putString(context,SpContants.TIMESTAMP,getTime());
                SpUtils.putString(context,SpContants.USER_ID,loginResultBean.getData().getStaffId());
                SpUtils.putString(context,SpContants.USERTRUENAME,loginResultBean.getData().getUserTrueName());
                System.out.println("loginresultbean:"+loginResultBean);
            }
        });
    }

    private static int getNor() {
        return random.nextInt(90) + 10;
    }

    private static Random random=new Random();

    public static String getTime(){
        String  str=String.valueOf( Calendar.getInstance().getTimeInMillis());
        return str;
    }

    public static String md5(String string) {

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    }


