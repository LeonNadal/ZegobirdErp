package com.xiaozhai.zegobirderp.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaozhai.zegobirderp.FuncDetailActivity;
import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;
import com.xiaozhai.zegobirderp.bean.CheckScanBean;
import com.xiaozhai.zegobirderp.bean.GoodsInputBean;

import java.util.ArrayList;

/**
 * Class name：无单入库主界面
 * Created by KAKA on 2017/11/2 at 10:28.
 */

public class NoOrderInputFragment extends BaseFragment implements View.OnClickListener {

    private ListView lvNoOrderInput;


    @Override
    protected View initView() {
        initTile("无单入库");
        isShowCommit(true);
        View view = View.inflate(mContext, R.layout.fragment_no_order_input, null);
        lvNoOrderInput = (ListView)view.findViewById( R.id.lv_no_input);
        initListener();
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        lvNoOrderInput.setAdapter(new NoOrderInputAdapter(mContext));
    }

    private void initListener() {
        btnCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_commit){
            return ;
        }
    }

    private class NoOrderInputAdapter extends BaseAdapter {
        private final FuncDetailActivity context;
        private ArrayList<GoodsInputBean> dataList;

        public NoOrderInputAdapter(FuncDetailActivity context) {
            this.context=context;
            initListData();
        }

        private void initListData() {
            dataList=new ArrayList<GoodsInputBean>();
            for (int i = 0; i < 10; i++) {
                GoodsInputBean goodsInputBean = new GoodsInputBean();
                goodsInputBean.setName("荣耀8");
                goodsInputBean.setTiaoma(1);
                goodsInputBean.setBhao(0);
                goodsInputBean.setSl(1);
                goodsInputBean.setQl(1);
                goodsInputBean.setYl(1);
                goodsInputBean.setDay("荣耀8");
                goodsInputBean.setLog("荣耀8");
                dataList.add(goodsInputBean);
            }
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public GoodsInputBean getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView==null){
                convertView=View.inflate(mContext,R.layout.item_good_input,null);
                holder=new ViewHolder();
                holder.tvName = (TextView)convertView.findViewById( R.id.tv_name );
                holder.tvGoodsnum = (TextView)convertView.findViewById( R.id.tv_goods_num );
                holder.tvGoodid = (TextView)convertView.findViewById( R.id.tv_goods_bhao );
                holder.tvSaomiao = (TextView)convertView.findViewById( R.id.tv_sao_miao );
                holder. tvQdan = (TextView)convertView.findViewById( R.id.tv_qdan_num );
                holder.tvYs = (EditText)convertView.findViewById( R.id.et_yshou_num);
                holder.tvDay = (TextView)convertView.findViewById( R.id.tv_day );
                holder.tvLog = (TextView)convertView.findViewById( R.id.tv_log );
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            GoodsInputBean bean = dataList.get(position);
            holder.tvName.setText("名称："+bean.getName());
            holder.tvGoodsnum.setText("商品条码："+bean.getTiaoma());
            holder.tvGoodid.setText("商品编号："+bean.getBhao());
            holder.tvSaomiao.setText("扫描数量："+bean.getSl());
            holder.tvQdan.setText("清单数量："+bean.getQl());
            holder.tvYs.setText("已收数量："+bean.getYl());
            holder.tvDay.setText("生产日期："+bean.getDay());
            holder.tvLog.setText("备注："+bean.getLog());
            return convertView;
        }

    }



    static class ViewHolder{
        TextView tvName;
        TextView tvGoodsnum;
        TextView tvGoodid;
        TextView tvSaomiao;
        TextView tvQdan;
        EditText tvYs;
        TextView tvDay;
        TextView tvLog;
    }

}
