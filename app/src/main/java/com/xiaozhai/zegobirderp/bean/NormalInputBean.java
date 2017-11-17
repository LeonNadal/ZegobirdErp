package com.xiaozhai.zegobirderp.bean;

import java.util.List;

/**
 * 常规入库bean
 * Created by Nadal on 2017/11/4.
 */

public class NormalInputBean {


    /**
     * ResultCode : 0000
     * Message : 操作成功！
     * Data : {"Order_Id":9146,"OrderCode":"PO171012001","Storage_Id":7,"Provider_Id":1217,
     * "StatusText":"入库中","ProductInfo":[{"OrderList_Id":25762,"Order_Id":9146,
     * "Product_Id":3367800005,"ProductCode":"T001-5","ProductName":"T001",
     * "ProductModel":"T001-5","Quantity":8,"EnterQuantity":7,"ProduceDate":null,"SmallUnit":"个",
     * "Remark":null},{"OrderList_Id":25763,"Order_Id":9146,"Product_Id":3367800004,
     * "ProductCode":"T001-4","ProductName":"T001","ProductModel":"T001-4","Quantity":8,
     * "EnterQuantity":3,"ProduceDate":null,"SmallUnit":"个","Remark":null}]}
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
         * Order_Id : 9146
         * OrderCode : PO171012001
         * Storage_Id : 7
         * Provider_Id : 1217
         * StatusText : 入库中
         * ProductInfo : [{"OrderList_Id":25762,"Order_Id":9146,"Product_Id":3367800005,
         * "ProductCode":"T001-5","ProductName":"T001","ProductModel":"T001-5","Quantity":8,
         * "EnterQuantity":7,"ProduceDate":null,"SmallUnit":"个","Remark":null},
         * {"OrderList_Id":25763,"Order_Id":9146,"Product_Id":3367800004,"ProductCode":"T001-4",
         * "ProductName":"T001","ProductModel":"T001-4","Quantity":8,"EnterQuantity":3,
         * "ProduceDate":null,"SmallUnit":"个","Remark":null}]
         */

        private int Order_Id;
        private String OrderCode;
        private int Storage_Id;
        private int Provider_Id;
        private String StatusText;
        private List<ProductInfoBean> ProductInfo;

        public int getOrder_Id() {
            return Order_Id;
        }

        public void setOrder_Id(int Order_Id) {
            this.Order_Id = Order_Id;
        }

        public String getOrderCode() {
            return OrderCode;
        }

        public void setOrderCode(String OrderCode) {
            this.OrderCode = OrderCode;
        }

        public int getStorage_Id() {
            return Storage_Id;
        }

        public void setStorage_Id(int Storage_Id) {
            this.Storage_Id = Storage_Id;
        }

        public int getProvider_Id() {
            return Provider_Id;
        }

        public void setProvider_Id(int Provider_Id) {
            this.Provider_Id = Provider_Id;
        }

        public String getStatusText() {
            return StatusText;
        }

        public void setStatusText(String StatusText) {
            this.StatusText = StatusText;
        }

        public List<ProductInfoBean> getProductInfo() {
            return ProductInfo;
        }

        public void setProductInfo(List<ProductInfoBean> ProductInfo) {
            this.ProductInfo = ProductInfo;
        }

        public static class ProductInfoBean {
            /**
             * OrderList_Id : 25762
             * Order_Id : 9146
             * Product_Id : 3367800005
             * ProductCode : T001-5
             * ProductName : T001
             * ProductModel : T001-5
             * Quantity : 8
             * EnterQuantity : 7
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

            public ProductInfoBean(int orderList_Id, int order_Id, long product_Id, String
                    productCode, String productName, String productModel, int quantity, int
                    enterQuantity, Object produceDate, String smallUnit, Object remark) {
                OrderList_Id = orderList_Id;
                Order_Id = order_Id;
                Product_Id = product_Id;
                ProductCode = productCode;
                ProductName = productName;
                ProductModel = productModel;
                Quantity = quantity;
                EnterQuantity = enterQuantity;
                ProduceDate = produceDate;
                SmallUnit = smallUnit;
                Remark = remark;
            }

            public ProductInfoBean(){}

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
}
