package com.xiaozhai.zegobirderp.bean;

import java.util.List;

/**
 * Created by Nadal on 2017/11/1.
 */

public class GoodQueryBean {

    /**
     * ResultCode : 0000
     * Message : 操作成功！
     * Data : [{"Product_Id":17743,"ProductCode":"01-001","ProductName":"Geekvape Avocado
     * (Screw+allen key+orings) 螺丝配件包 【GAS4801】","ProductModel":"01-001","ProductStorage":67,
     * "ValidStorage":67}]
     */

    private String ResultCode;
    private String Message;
    private List<DataBean> Data;

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String ResultCode) {
        this.ResultCode = ResultCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Product_Id : 17743
         * ProductCode : 01-001
         * ProductName : Geekvape Avocado (Screw+allen key+orings) 螺丝配件包 【GAS4801】
         * ProductModel : 01-001
         * ProductStorage : 67
         * ValidStorage : 67
         */

        private int Product_Id;
        private String ProductCode;
        private String ProductName;
        private String ProductModel;
        private int ProductStorage;
        private int ValidStorage;

        public int getProduct_Id() {
            return Product_Id;
        }

        public void setProduct_Id(int Product_Id) {
            this.Product_Id = Product_Id;
        }

        public String getProductCode() {
            return ProductCode;
        }

        public void setProductCode(String ProductCode) {
            this.ProductCode = ProductCode;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public String getProductModel() {
            return ProductModel;
        }

        public void setProductModel(String ProductModel) {
            this.ProductModel = ProductModel;
        }

        public int getProductStorage() {
            return ProductStorage;
        }

        public void setProductStorage(int ProductStorage) {
            this.ProductStorage = ProductStorage;
        }

        public int getValidStorage() {
            return ValidStorage;
        }

        public void setValidStorage(int ValidStorage) {
            this.ValidStorage = ValidStorage;
        }
    }
}
