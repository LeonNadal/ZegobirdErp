package com.xiaozhai.zegobirderp.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;

import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;

/**
 * Created by nadal on 2017/11/27.
 */

public class NormalInputFragment extends BaseFragment {

    private  ListView lvNormalInput;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_normal_input, null);
        lvNormalInput = (ListView) view.findViewById(R.id.lv_normal_input);
        initTile("常规入库任务单");
        mContext.btnCommit.setBackground(new ColorDrawable(Color.GREEN));
        mContext.btnCommit.setText("下载");
        isShowCommit(true);
        return view;
    }
}
