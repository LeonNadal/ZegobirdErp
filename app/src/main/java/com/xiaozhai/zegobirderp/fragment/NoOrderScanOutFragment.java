package com.xiaozhai.zegobirderp.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;
import com.xiaozhai.zegobirderp.bean.NoOrderScanOutputBean;

import java.util.List;

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

    public class NoOrderScanOutAdapter extends BaseAdapter {

        private List<NoOrderScanOutputBean.DataBean > data;

        public NoOrderScanOutAdapter(List<NoOrderScanOutputBean.DataBean > data) {
            this.data=data;
        }

        public void clear(){
            data.clear();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public NoOrderScanOutputBean.DataBean  getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView==null){
                convertView=View.inflate(mContext,R.layout.item_noorder_scan_out,null);
                holder.tvName = (TextView)convertView.findViewById( R.id.tv_name );
                holder.tvCode = (TextView)convertView.findViewById( R.id.tv_code );
                holder.tvNumber = (TextView)convertView.findViewById( R.id.tv_number );
                holder.tvSize = (TextView)convertView.findViewById( R.id.tv_size );
                holder.tvTime = (TextView)convertView.findViewById( R.id.tv_time );
                holder.tvRemark = (TextView)convertView.findViewById( R.id.tv_remark );
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            NoOrderScanOutputBean.DataBean  item = getItem(position);
            holder.tvName.setText("名称："+item.getProductName());
            holder.tvCode.setText("商品条码："+item.getProductModel());
            holder.tvNumber.setText("商品编号："+item.getProductCode());
            holder.tvSize.setText("数量："+item.getQuantity());
            holder.tvTime.setText("生产日期："+"2017.10.10");
            return convertView;
        }

    }


    static class ViewHolder{
         TextView tvName;
         TextView tvCode;
         TextView tvNumber;
         TextView tvSize;
         TextView tvTime;
         TextView tvRemark;
    }
}
