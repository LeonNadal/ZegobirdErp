package com.xiaozhai.zegobirderp.bean;

import java.util.List;

/**
 * Created by nadal on 2017/11/11.
 */

public class GoodSelectBean {

    /**
     * ResultCode : 0000
     * Message : 操作成功！
     * Data : [{"OrderList_Id":0,"Order_Id":0,"Product_Id":3367900004,"ProductCode":"TY001-4",
     * "ProductName":"TY001","ProductModel":"TY001-4","Quantity":0,"EnterQuantity":0,
     * "ProduceDate":null,"SmallUnit":"个","Remark":null},{"OrderList_Id":0,"Order_Id":0,
     * "Product_Id":3367900003,"ProductCode":"TY001-3","ProductName":"TY001",
     * "ProductModel":"TY001-3","Quantity":0,"EnterQuantity":0,"ProduceDate":null,
     * "SmallUnit":"个","Remark":null}]
     */

    private String ResultCode;
    private String Message;
    private List<DataBean> Data;

    @Override
    public String toString() {
        return "GoodSelectBean{" +
                "ResultCode='" + ResultCode + '\'' +
                ", Message='" + Message + '\'' +
                ", Data=" + Data +
                '}';
    }

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
         * OrderList_Id : 0
         * Order_Id : 0
         * Product_Id : 3367900004
         * ProductCode : TY001-4
         * ProductName : TY001
         * ProductModel : TY001-4
         * Quantity : 0
         * EnterQuantity : 0
         * ProduceDate : null
         * SmallUnit : 个
         * Remark : null
         */

        private int OrderList_Id;
        private int Order_Id;
        private long Product_Id;
        private String ProductCode;
        private String ProductName;
        private String ProductModel;
        private int Quantity;
        private int EnterQuantity;
        private Object ProduceDate;
        private String SmallUnit;
        private Object Remark;

        @Override
        public String toString() {
            return "DataBean{" +
                    "OrderList_Id=" + OrderList_Id +
                    ", Order_Id=" + Order_Id +
                    ", Product_Id=" + Product_Id +
                    ", ProductCode='" + ProductCode + '\'' +
                    ", ProductName='" + ProductName + '\'' +
                    ", ProductModel='" + ProductModel + '\'' +
                    ", Quantity=" + Quantity +
                    ", EnterQuantity=" + EnterQuantity +
                    ", ProduceDate=" + ProduceDate +
                    ", SmallUnit='" + SmallUnit + '\'' +
                    ", Remark=" + Remark +
                    '}';
        }

        public int getOrderList_Id() {
            return OrderList_Id;
        }

        public void setOrderList_Id(int OrderList_Id) {
            this.OrderList_Id = OrderList_Id;
        }

        public int getOrder_Id() {
            return Order_Id;
        }

        public void setOrder_Id(int Order_Id) {
            this.Order_Id = Order_Id;
        }

        public long getProduct_Id() {
            return Product_Id;
        }

        public void setProduct_Id(long Product_Id) {
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

        public int getQuantity() {
            return Quantity;
        }

        public void setQuantity(int Quantity) {
            this.Quantity = Quantity;
        }

        public int getEnterQuantity() {
            return EnterQuantity;
        }

        public void setEnterQuantity(int EnterQuantity) {
            this.EnterQuantity = EnterQuantity;
        }

        public Object getProduceDate() {
            return ProduceDate;
        }

        public void setProduceDate(Object ProduceDate) {
            this.ProduceDate = ProduceDate;
        }

        public String getSmallUnit() {
            return SmallUnit;
        }

        public void setSmallUnit(String SmallUnit) {
            this.SmallUnit = SmallUnit;
        }

        public Object getRemark() {
            return Remark;
        }

        public void setRemark(Object Remark) {
            this.Remark = Remark;
        }
    }
}
