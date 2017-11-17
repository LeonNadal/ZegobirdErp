package com.xiaozhai.zegobirderp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xiaozhai.zegobirderp.FuncDetailActivity;

/**
 * Created by Nadal on 2017/10/31.
 */

public abstract class BaseFragment extends Fragment {

    protected  FuncDetailActivity mContext;
    protected  TextView tvFuncTitle;
    protected  Button btnCommit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        mContext=(FuncDetailActivity)getActivity();
         tvFuncTitle = mContext.tvFuncTitle;
         btnCommit = mContext.btnCommit;
        return initView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    /**
     * 初始化标题
     * @param titleName
     */
    protected void initTile(String titleName){
        tvFuncTitle.setText(titleName);
    }

    /**
     * 是否显示提交按钮
     * @param isShow
     */
    protected void isShowCommit(boolean isShow){
        btnCommit.setVisibility(isShow?View.VISIBLE:View.GONE);
    }


    /**
     * 布局完成后，加载数据
     */
    protected  void initData(){};

    /**
     * 初始化布局
     * @return
     */
    protected abstract View initView();

}
