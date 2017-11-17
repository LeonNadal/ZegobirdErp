package com.xiaozhai.zegobirderp.fragment;

import android.view.View;

import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;

/**
 * Created by Nadal on 2017/11/1.
 */

public class GoodWeightFragment extends BaseFragment {
    @Override
    protected View initView() {
        initTile("发货称重");
        isShowCommit(true);
        View view = View.inflate(mContext, R.layout.fragment_good_weight, null);
        return view;
    }
}
