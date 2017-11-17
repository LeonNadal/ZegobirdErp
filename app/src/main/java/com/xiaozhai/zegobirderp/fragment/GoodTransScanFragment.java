package com.xiaozhai.zegobirderp.fragment;

import android.view.View;

import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;

/**
 * Created by Nadal on 2017/10/31.
 */

public class GoodTransScanFragment extends BaseFragment {
    @Override
    protected View initView() {
        initTile("货位转移扫描");
        isShowCommit(true);
        View view = View.inflate(mContext, R.layout.fragment_good_trans_scan, null);
        return view;
    }


}
