package com.xiaozhai.zegobirderp.bean;

import android.content.Context;

import java.util.LinkedList;

/**
 * Project name：上架货位位置实体
 * Created by KAKA on 2017/11/2 at 10:27.
 */
public class ShelfTaskBean {
    private String name;
    private int bhao;
    private int tma;
    private int sl;
    private int up_loc;
    private int up_sl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBhao() {
        return bhao;
    }

    public void setBhao(int bhao) {
        this.bhao = bhao;
    }

    public int getTma() {
        return tma;
    }

    public void setTma(int tma) {
        this.tma = tma;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getUp_loc() {
        return up_loc;
    }

    public void setUp_loc(int up_loc) {
        this.up_loc = up_loc;
    }

    public int getUp_sl() {
        return up_sl;
    }

    public void setUp_sl(int up_sl) {
        this.up_sl = up_sl;
    }
}
