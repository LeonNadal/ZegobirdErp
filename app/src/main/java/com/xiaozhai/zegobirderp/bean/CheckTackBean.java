package com.xiaozhai.zegobirderp.bean;

import java.util.List;

/**
 * Created by Nadal on 2017/10/31.
 */

public class CheckTackBean {

    /**
     * Data : [{"CheckCode":"SC171114001","Check_Id":753,"StatusType":2,"StatusTypeName":"盘点中",
     * "TotalCheckQuantity":0,"TotalLossMoney":0,"TotalLossQuantity":0,"TotalProductStorage":0,
     * "TotalProfitMoney":0,"TotalProfitQuantity":0,"TotalPurchaseMoney":0,
     * "UserTrueName":"mdb-A","User_Id":1},{"CheckCode":"SC171109001","Check_Id":752,
     * "StatusType":2,"StatusTypeName":"盘点中","TotalCheckQuantity":0,"TotalLossMoney":0,
     * "TotalLossQuantity":0,"TotalProductStorage":16,"TotalProfitMoney":0,
     * "TotalProfitQuantity":0,"TotalPurchaseMoney":16,"UserTrueName":"mdb-A","User_Id":1},
     * {"CheckCode":"SC160901001","Check_Id":49,"StatusTypeName":"待盘点",
     * "TotalCheckQuantity":345305,"TotalLossMoney":0,"TotalLossQuantity":0,
     * "TotalProfitMoney":9496302.89,"TotalProfitQuantity":345305,"TotalPurchaseMoney":9488214,
     * "UserTrueName":"超级管理员","User_Id":1}]
     * Message : 操作成功！
     * PageCount : 3
     * ResultCode : 0000
     */

    private String Message;
    private int PageCount;
    private String ResultCode;
    private List<DataBean> Data;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int PageCount) {
        this.PageCount = PageCount;
    }

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String ResultCode) {
        this.ResultCode = ResultCode;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * CheckCode : SC171114001
         * Check_Id : 753
         * StatusType : 2
         * StatusTypeName : 盘点中
         * TotalCheckQuantity : 0
         * TotalLossMoney : 0.0
         * TotalLossQuantity : 0
         * TotalProductStorage : 0
         * TotalProfitMoney : 0.0
         * TotalProfitQuantity : 0
         * TotalPurchaseMoney : 0.0
         * UserTrueName : mdb-A
         * User_Id : 1
         */

        private String CheckCode;
        private int Check_Id;
        private int StatusType;
        private String StatusTypeName;
        private int TotalCheckQuantity;
        private double TotalLossMoney;
        private int TotalLossQuantity;
        private int TotalProductStorage;
        private double TotalProfitMoney;
        private int TotalProfitQuantity;
        private double TotalPurchaseMoney;
        private String UserTrueName;
        private int User_Id;

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

        public int getStatusType() {
            return StatusType;
        }

        public void setStatusType(int StatusType) {
            this.StatusType = StatusType;
        }

        public String getStatusTypeName() {
            return StatusTypeName;
        }

        public void setStatusTypeName(String StatusTypeName) {
            this.StatusTypeName = StatusTypeName;
        }

        public int getTotalCheckQuantity() {
            return TotalCheckQuantity;
        }

        public void setTotalCheckQuantity(int TotalCheckQuantity) {
            this.TotalCheckQuantity = TotalCheckQuantity;
        }

        public double getTotalLossMoney() {
            return TotalLossMoney;
        }

        public void setTotalLossMoney(double TotalLossMoney) {
            this.TotalLossMoney = TotalLossMoney;
        }

        public int getTotalLossQuantity() {
            return TotalLossQuantity;
        }

        public void setTotalLossQuantity(int TotalLossQuantity) {
            this.TotalLossQuantity = TotalLossQuantity;
        }

        public int getTotalProductStorage() {
            return TotalProductStorage;
        }

        public void setTotalProductStorage(int TotalProductStorage) {
            this.TotalProductStorage = TotalProductStorage;
        }

        public double getTotalProfitMoney() {
            return TotalProfitMoney;
        }

        public void setTotalProfitMoney(double TotalProfitMoney) {
            this.TotalProfitMoney = TotalProfitMoney;
        }

        public int getTotalProfitQuantity() {
            return TotalProfitQuantity;
        }

        public void setTotalProfitQuantity(int TotalProfitQuantity) {
            this.TotalProfitQuantity = TotalProfitQuantity;
        }

        public double getTotalPurchaseMoney() {
            return TotalPurchaseMoney;
        }

        public void setTotalPurchaseMoney(double TotalPurchaseMoney) {
            this.TotalPurchaseMoney = TotalPurchaseMoney;
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
    }
}
