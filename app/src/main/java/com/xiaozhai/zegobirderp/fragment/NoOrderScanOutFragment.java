package com.xiaozhai.zegobirderp.fragment;

import android.view.View;

import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;

/**
 * Created by Nadal on 2017/11/1.
 */

public class NoOrderScanOutFragment extends BaseFragment {
    @Override
    protected View initView() {
        initTile("无单扫描出库");
        isShowCommit(true);
        View view = View.inflate(mContext, R.layout.fragment_noorder_scan_out, null);
        return view;
    }
}
