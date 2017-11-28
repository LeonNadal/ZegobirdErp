package com.xiaozhai.zegobirderp.bean;

/**
 * Created by nadal on 2017/11/27.
 */

public class NormalInputBean1 {
    /**
     * (Id integer primary key, CustomName text, OrderPrice integer, Country text)
     */

    private int Id;
    private String CustomName;
    private int OrderPrice;
    private String Country;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCustomName() {
        return CustomName;
    }

    public void setCustomName(String customName) {
        CustomName = customName;
    }

    public int getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        OrderPrice = orderPrice;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
