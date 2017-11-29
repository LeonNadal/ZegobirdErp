package com.xiaozhai.zegobirderp.bean;

import java.util.List;

/**
 * Created by Nadal on 2017/10/31.
 */

public class CheckScanBean {

    /**
     * Data : {"CheckCode":"SC171109003","Check_Id":754,"StorageProductList":[{"BigUnit":"个",
     * "CheckList_Id":45196,"Check_Id":754,"CreateDate":"2017-11-09T15:05:23.293","CreateID":1,
     * "Creator":"mdb-A","Enable":1,"LossMoney":0,"LossQuantity":0,"PositionName":"12",
     * "ProductCode":"TY001-3","ProductModel":"TY001-3","ProductName":"TY001","ProductSpec":"2",
     * "ProductStorage":10,"Product_Id":"3367900003","ProfitMoney":0,"ProfitQuantity":0,
     * "PurchaseMoney":10,"PurchasePrice":1,"SmallUnit":"个","UnitConvert":0,
     * "UnitConvertText":"0"}],"TotalCheckQuantity":0,"TotalProductStorage":10,
     * "UserTrueName":"mdb-A","User_Id":1}
     * Message : 操作成功！
     * ResultCode : 0000
     */

    private DataBean Data;
    private String Message;
    private String ResultCode;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String ResultCode) {
        this.ResultCode = ResultCode;
    }

    public static class DataBean {
        /**
         * CheckCode : SC171109003
         * Check_Id : 754
         * StorageProductList : [{"BigUnit":"个","CheckList_Id":45196,"Check_Id":754,
         * "CreateDate":"2017-11-09T15:05:23.293","CreateID":1,"Creator":"mdb-A","Enable":1,
         * "LossMoney":0,"LossQuantity":0,"PositionName":"12","ProductCode":"TY001-3",
         * "ProductModel":"TY001-3","ProductName":"TY001","ProductSpec":"2","ProductStorage":10,
         * "Product_Id":"3367900003","ProfitMoney":0,"ProfitQuantity":0,"PurchaseMoney":10,
         * "PurchasePrice":1,"SmallUnit":"个","UnitConvert":0,"UnitConvertText":"0"}]
         * TotalCheckQuantity : 0
         * TotalProductStorage : 10
         * UserTrueName : mdb-A
         * User_Id : 1
         */

        private String CheckCode;
        private int Check_Id;
        private int TotalCheckQuantity;
        private int TotalProductStorage;
        private String UserTrueName;
        private int User_Id;
        private List<StorageProductListBean> StorageProductList;

        public String getCheckCode() {
            return CheckCode;
        }

        public void setCheckCode(String CheckCode) {
            this.CheckCode = CheckCode;
        }

        public int getCheck_Id() {
            return Check_Id;
        }

        public void setCheck_Id(int Check_Id) {
            this.Check_Id = Check_Id;
        }

        public int getTotalCheckQuantity() {
            return TotalCheckQuantity;
        }

        public void setTotalCheckQuantity(int TotalCheckQuantity) {
            this.TotalCheckQuantity = TotalCheckQuantity;
        }

        public int getTotalProductStorage() {
            return TotalProductStorage;
        }

        public void setTotalProductStorage(int TotalProductStorage) {
            this.TotalProductStorage = TotalProductStorage;
        }

        public String getUserTrueName() {
            return UserTrueName;
        }

        public void setUserTrueName(String UserTrueName) {
            this.UserTrueName = UserTrueName;
        }

        public int getUser_Id() {
            return User_Id;
        }

        public void setUser_Id(int User_Id) {
            this.User_Id = User_Id;
        }

        public List<StorageProductListBean> getStorageProductList() {
            return StorageProductList;
        }

        public void setStorageProductList(List<StorageProductListBean> StorageProductList) {
            this.StorageProductList = StorageProductList;
        }

        public static class StorageProductListBean {
            /**
             * BigUnit : 个
             * CheckList_Id : 45196
             * Check_Id : 754
             * CreateDate : 2017-11-09T15:05:23.293
             * CreateID : 1
             * Creator : mdb-A
             * Enable : 1
             * LossMoney : 0.0
             * LossQuantity : 0
             * PositionName : 12
             * ProductCode : TY001-3
             * ProductModel : TY001-3
             * ProductName : TY001
             * ProductSpec : 2
             * ProductStorage : 10
             * Product_Id : 3367900003
             * ProfitMoney : 0.0
             * ProfitQuantity : 0
             * PurchaseMoney : 10.0
             * PurchasePrice : 1.0
             * SmallUnit : 个
             * UnitConvert : 0.0
             * UnitConvertText : 0
             */

            private String BigUnit;
            private int CheckList_Id;
            private int Check_Id;
            private String CreateDate;
            private int CreateID;
            private String Creator;
            private int Enable;
            private double LossMoney;
            private int LossQuantity;
            private String PositionName;
            private String ProductCode;
            private String ProductModel;
            private String ProductName;
            private String ProductSpec;
            private int ProductStorage;
            private String Product_Id;
            private double ProfitMoney;
            private int ProfitQuantity;
            private double PurchaseMoney;
            private double PurchasePrice;
            private String SmallUnit;
            private double UnitConvert;
            private String UnitConvertText;

            public String getBigUnit() {
                return BigUnit;
            }

            public void setBigUnit(String BigUnit) {
                this.BigUnit = BigUnit;
            }

            public int getCheckList_Id() {
                return CheckList_Id;
            }

            public void setCheckList_Id(int CheckList_Id) {
                this.CheckList_Id = CheckList_Id;
            }

            public int getCheck_Id() {
                return Check_Id;
            }

            public void setCheck_Id(int Check_Id) {
                this.Check_Id = Check_Id;
            }

            public String getCreateDate() {
                return CreateDate;
            }

            public void setCreateDate(String CreateDate) {
                this.CreateDate = CreateDate;
            }

            public int getCreateID() {
                return CreateID;
            }

            public void setCreateID(int CreateID) {
                this.CreateID = CreateID;
            }

            public String getCreator() {
                return Creator;
            }

            public void setCreator(String Creator) {
                this.Creator = Creator;
            }

            public int getEnable() {
                return Enable;
            }

            public void setEnable(int Enable) {
                this.Enable = Enable;
            }

            public double getLossMoney() {
                return LossMoney;
            }

            public void setLossMoney(double LossMoney) {
                this.LossMoney = LossMoney;
            }

            public int getLossQuantity() {
                return LossQuantity;
            }

            public void setLossQuantity(int LossQuantity) {
                this.LossQuantity = LossQuantity;
            }

            public String getPositionName() {
                return PositionName;
            }

            public void setPositionName(String PositionName) {
                this.PositionName = PositionName;
            }

            public String getProductCode() {
                return ProductCode;
            }

            public void setProductCode(String ProductCode) {
                this.ProductCode = ProductCode;
            }

            public String getProductModel() {
                return ProductModel;
            }

            public void setProductModel(String ProductModel) {
                this.ProductModel = ProductModel;
            }

            public String getProductName() {
                return ProductName;
            }

            public void setProductName(String ProductName) {
                this.ProductName = ProductName;
            }

            public String getProductSpec() {
                return ProductSpec;
            }

            public void setProductSpec(String ProductSpec) {
                this.ProductSpec = ProductSpec;
            }

            public int getProductStorage() {
                return ProductStorage;
            }

            public void setProductStorage(int ProductStorage) {
                this.ProductStorage = ProductStorage;
            }

            public String getProduct_Id() {
                return Product_Id;
            }

            public void setProduct_Id(String Product_Id) {
                this.Product_Id = Product_Id;
            }

            public double getProfitMoney() {
                return ProfitMoney;
            }

            public void setProfitMoney(double ProfitMoney) {
                this.ProfitMoney = ProfitMoney;
            }

            public int getProfitQuantity() {
                return ProfitQuantity;
            }

            public void setProfitQuantity(int ProfitQuantity) {
                this.ProfitQuantity = ProfitQuantity;
            }

            public double getPurchaseMoney() {
                return PurchaseMoney;
            }

            public void setPurchaseMoney(double PurchaseMoney) {
                this.PurchaseMoney = PurchaseMoney;
            }

            public double getPurchasePrice() {
                return PurchasePrice;
            }

            public void setPurchasePrice(double PurchasePrice) {
                this.PurchasePrice = PurchasePrice;
            }

            public String getSmallUnit() {
                return SmallUnit;
            }

            public void setSmallUnit(String SmallUnit) {
                this.SmallUnit = SmallUnit;
            }

            public double getUnitConvert() {
                return UnitConvert;
            }

            public void setUnitConvert(double UnitConvert) {
                this.UnitConvert = UnitConvert;
            }

            public String getUnitConvertText() {
                return UnitConvertText;
            }

            public void setUnitConvertText(String UnitConvertText) {
                this.UnitConvertText = UnitConvertText;
            }
        }
    }
}
