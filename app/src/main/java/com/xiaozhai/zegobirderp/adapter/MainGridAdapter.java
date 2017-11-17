package com.xiaozhai.zegobirderp.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaozhai.zegobirderp.R;

import java.util.ArrayList;

/**
 * Created by User on 2017/10/30.
 */

public class MainGridAdapter extends BaseAdapter {


    private final Context context;
    private  ArrayList<String> list;
    private int[] icons;

    public MainGridAdapter(Context context){
        this.context=context;
        initListData();
    }

    private void initListData() {
        list = new ArrayList<>();
        list.add("常规入库");
        list.add("无单入库");
        list.add("上架任务");

        list.add("常规上架");
        list.add("无单上架");
        list.add("商品查询");
        list.add("货位转移");
        list.add("盘点任务");
        list.add("盘点扫描");
        list.add("无单出库");
        list.add("打包检验");
        list.add("发货称重");
        icons=new int[]{R.drawable.normal_input,R.drawable.no_order_input,R.drawable.up_tack,R.drawable.normal_up,R.drawable.no_order_up,R.drawable.good_query,R.drawable.position_trans,R.drawable.check_tack,R.drawable.check_scan,R.drawable.no_order_output,R.drawable.package_check,R.drawable.good_weight};

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.item_grid_main,null);
            viewHolder=new ViewHolder();
            viewHolder.ivIcon= (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tvFunc= (TextView) convertView.findViewById(R.id.tv_func);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tvFunc.setText(list.get(position));
        viewHolder.ivIcon.setBackground(context.getResources().getDrawable(icons[position]));
        return convertView;
    }

    static class ViewHolder{
        ImageView ivIcon;
        TextView tvFunc;
    }
}
