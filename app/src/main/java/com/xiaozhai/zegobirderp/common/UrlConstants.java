package com.xiaozhai.zegobirderp.common;

/**
 * 访问erp后台的URI地址
 * Created by Nadal on 2017/11/4.
 */

public class UrlConstants {


    public final static String BASE_URL="http://119.29.90.197:8087/";
    //常规入库url
    public final static  String NORMAL_INPUT=BASE_URL+"api/Purchase/GetPurchaseInfoByCode";
    //商品选择
    public final static String GOOD_SELECT =BASE_URL+"api/Purchase/GetProductList";
    //常规入库添加商品
    public final static String ADDPURCHASEENTERINFO=BASE_URL+"api/Purchase/AddPurchaseEnterInfo";

    //商品查询
    public final static String GOOD_QUERY =BASE_URL+"api/Storage/GetStorageProductCount";

    //无单入库
    public final static String NO_ORDER_INPUT=BASE_URL+"api/Purchase/GetProductInfo";


}
