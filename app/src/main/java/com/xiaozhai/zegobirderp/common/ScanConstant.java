package com.xiaozhai.zegobirderp.common;

/**
 * pda扫描常用参数
 * Created by nadal on 2017/11/7.
 */

public class ScanConstant {
    // 打开扫描头
    public static  String SCANNER_APP_ENABLE = "com.android.service_scanner_appenable";
    // 发送条码内容给请求 APP
    public static  String  SCANNER_APP_DATA = "com.android.service_scanner_appdata";
    //扫描声音提示
    public static  String  SCANNER_APP_SOUNDALARM = "com.android.service_scanner_appsoundalarm";
    //扫描振动提示
    public static  String  SCANNER_APP_VIBRATEALARM="com.android.service_scanner_appvibratealarm";
    //扫描加后缀，no-不加后缀、enter-加回车换行后缀、tab-加制表符 Tab 后缀
    public static  String  SCANNER_APP_ADDSUFFIX = "com.android.service_scanner_appaddsuffix";
    //设置 PDA 系统时间
    public static  String  SETTING_SYSTIME = "com.android.service_setting_systime";
    // 获取 PDA 制造厂商名称
    public static  String GET_MANUFACTRUER = "com.android.service_get_manufacturer";
    //回应 PDA 制造厂商名称给请求 APP
    public static  String ECHO_MANUFACTRUER = "com.android.service_echo_manufacturer";
    //关闭 PDA
    public static  String SETTING_SHUTDOWN = "com.android.service_setting_shutdown";
    // 禁止下拉消息栏
    public static  String SETTING_DISABLE_STATUSBAR="com.android.service_setting_disablestatusbar";
}
