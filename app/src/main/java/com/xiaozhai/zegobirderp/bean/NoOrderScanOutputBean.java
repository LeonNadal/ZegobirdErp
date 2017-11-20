package com.xiaozhai.zegobirderp.bean;

/**
 * Created by nadal on 2017/11/20.
 */

public class NoOrderScanOutputBean {

    /**
     * ResultCode : 0000
     * Message : 操作成功！
     * Data : {"Product_Id":17743,"ProductCode":"01-001","ProductName":"Geekvape Avocado
     * (Screw+allen key+orings) 螺丝配件包 【GAS4801】","ProductModel":"01-001","SmallUnit":"个",
     * "Quantity":0,"PurchasePrice":14.212,"PurchaseMoney":0,"Rate":0,"RatePrice":0,"RateMoney":0}
     */

    private String ResultCode;
    private String Message;
    private DataBean Data;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Product_Id : 17743
         * ProductCode : 01-001
         * ProductName : Geekvape Avocado (Screw+allen key+orings) 螺丝配件包 【GAS4801】
         * ProductModel : 01-001
         * SmallUnit : 个
         * Quantity : 0
         * PurchasePrice : 14.212
         * PurchaseMoney : 0.0
         * Rate : 0.0
         * RatePrice : 0.0
         * RateMoney : 0.0
         */

        private int Product_Id;
        private String ProductCode;
        private String ProductName;
        private String ProductModel;
        private String SmallUnit;
        private int Quantity;
        private double PurchasePrice;
        private double PurchaseMoney;
        private double Rate;
        private double RatePrice;
        private double RateMoney;

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

        public String getSmallUnit() {
            return SmallUnit;
        }

        public void setSmallUnit(String SmallUnit) {
            this.SmallUnit = SmallUnit;
        }

        public int getQuantity() {
            return Quantity;
        }

        public void setQuantity(int Quantity) {
            this.Quantity = Quantity;
        }

        public double getPurchasePrice() {
            return PurchasePrice;
        }

        public void setPurchasePrice(double PurchasePrice) {
            this.PurchasePrice = PurchasePrice;
        }

        public double getPurchaseMoney() {
            return PurchaseMoney;
        }

        public void setPurchaseMoney(double PurchaseMoney) {
            this.PurchaseMoney = PurchaseMoney;
        }

        public double getRate() {
            return Rate;
        }

        public void setRate(double Rate) {
            this.Rate = Rate;
        }

        public double getRatePrice() {
            return RatePrice;
        }

        public void setRatePrice(double RatePrice) {
            this.RatePrice = RatePrice;
        }

        public double getRateMoney() {
            return RateMoney;
        }

        public void setRateMoney(double RateMoney) {
            this.RateMoney = RateMoney;
        }
    }
}
