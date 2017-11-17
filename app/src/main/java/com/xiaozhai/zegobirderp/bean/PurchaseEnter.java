package com.xiaozhai.zegobirderp.bean;

/**
 * Created by nadal on 2017/11/15.
 */

public class PurchaseEnter {

    /**
     * OrderList_Id : 25766
     * Product_Id : 3367800001
     * ProductCode : T001-1
     * ProductName : T001
     * ProductModel : T001-1
     * PositionName : 12
     * SmallUnit : 个
     * Quantity : 3
     */

    private int OrderList_Id;
    private String Product_Id;
    private String ProductCode;
    private String ProductName;
    private String ProductModel;
    private String PositionName;
    private String SmallUnit;
    private int Quantity;

    public PurchaseEnter(int orderList_Id, String product_Id, String productCode, String
            productName, String productModel, String positionName, String smallUnit, int quantity) {
        OrderList_Id = orderList_Id;
        Product_Id = product_Id;
        ProductCode = productCode;
        ProductName = productName;
        ProductModel = productModel;
        PositionName = positionName;
        SmallUnit = smallUnit;
        Quantity = quantity;
    }

    public PurchaseEnter(){}

    public int getOrderList_Id() {
        return OrderList_Id;
    }

    public void setOrderList_Id(int OrderList_Id) {
        this.OrderList_Id = OrderList_Id;
    }

    public String getProduct_Id() {
        return Product_Id;
    }

    public void setProduct_Id(String Product_Id) {
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

    public String getPositionName() {
        return PositionName;
    }

    public void setPositionName(String positionName) {
        PositionName = positionName;
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
}
