package com.xiaozhai.zegobirderp.bean;

import android.content.Context;

import java.util.LinkedList;

/**
 * Project name：常规上架实体
 * Created by KAKA on ${DATE}.
 */

public class GoodsEntryBean {
    private String name;
    private int tiaoma;
    private int sup;
    private int smsl;
    private String t_loc;
    private String loc;
    private String day;
    private String log;
    private String goods_size;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTiaoma() {
        return tiaoma;
    }

    public void setTiaoma(int tiaoma) {
        this.tiaoma = tiaoma;
    }

    public int getSup() {
        return sup;
    }

    public void setSup(int sup) {
        this.sup = sup;
    }

    public int getSmsl() {
        return smsl;
    }

    public void setSmsl(int smsl) {
        this.smsl = smsl;
    }

    public String getT_loc() {
        return t_loc;
    }

    public void setT_loc(String t_loc) {
        this.t_loc = t_loc;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getGoods_size() {
        return goods_size;
    }

    public void setGoods_size(String goods_size) {
        this.goods_size = goods_size;
    }
}
