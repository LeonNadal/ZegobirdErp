package com.xiaozhai.zegobirderp.bean;


/**
 * Project name：拣货下架实体
 * Created by KAKA on 2017/11/2 at 10:27.
 */

public class PickingRackBean {
    private String goods_loc;
    private String danw;
    private String name;
    private int tma;
    private int sel_sl;
    private int smsl;
    private String gsize;
    private String log;


    public String getGoods_loc() {
        return goods_loc;
    }

    public void setGoods_loc(String goods_loc) {
        this.goods_loc = goods_loc;
    }

    public String getDanw() {
        return danw;
    }

    public void setDanw(String danw) {
        this.danw = danw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTma() {
        return tma;
    }

    public void setTma(int tma) {
        this.tma = tma;
    }

    public int getSel_sl() {
        return sel_sl;
    }

    public void setSel_sl(int sel_sl) {
        this.sel_sl = sel_sl;
    }

    public int getSmsl() {
        return smsl;
    }

    public void setSmsl(int smsl) {
        this.smsl = smsl;
    }

    public String getGsize() {
        return gsize;
    }

    public void setGsize(String size) {
        this.gsize = size;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
