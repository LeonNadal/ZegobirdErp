package com.xiaozhai.zegobirderp.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaozhai.zegobirderp.FuncDetailActivity;
import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;
import com.xiaozhai.zegobirderp.bean.PickingRackBean;

import java.util.ArrayList;

/**
 * Prooject name：拣货下架主界面
 * Created by KAKA on ${DATE}.
 */

public class PickingRackFragment extends BaseFragment implements View.OnClickListener {

    private ListView lvPickingRack;


    @Override
    protected View initView() {
        initTile("拣货下架");
        isShowCommit(true);
        View view = View.inflate(mContext, R.layout.fragment_picking_rack, null);
        lvPickingRack = (ListView)view.findViewById( R.id.lv_picking_rack);
        initListener();
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        lvPickingRack.setAdapter(new PickingRackAdapter(mContext));
    }

    private void initListener() {
        btnCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_commit){
            mContext.goToFragment(6);
        }
    }

    private class PickingRackAdapter extends BaseAdapter {
        private final FuncDetailActivity context;
        private ArrayList<PickingRackBean> dataList;

        public PickingRackAdapter(FuncDetailActivity context) {
            this.context=context;
            initListData();
        }

        private void initListData() {
            dataList=new ArrayList<PickingRackBean>();
            for (int i = 0; i < 10; i++) {
                PickingRackBean PickingRackBean = new PickingRackBean();
                PickingRackBean.setGoods_loc("1");
                PickingRackBean.setDanw("1");
                PickingRackBean.setName("荣耀8");
                PickingRackBean.setTma(1);
                PickingRackBean.setSel_sl(1);
                PickingRackBean.setSmsl(1);
                PickingRackBean.setGsize("荣耀8");
                PickingRackBean.setLog("荣耀8");
                dataList.add(PickingRackBean);
            }
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public PickingRackBean getItem(int position) {
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
                convertView=View.inflate(mContext,R.layout.item_picking_rack,null);
                holder=new ViewHolder();
                holder.tvGoodLoc = (TextView)convertView.findViewById( R.id.tv_goods_loc );
                holder.tvDanw = (TextView)convertView.findViewById( R.id.tv_danw );
                holder.tvName = (TextView)convertView.findViewById( R.id.tv_name );
                holder. tvTma = (TextView)convertView.findViewById( R.id.tv_tma );
                holder. tvSelsl = (TextView)convertView.findViewById( R.id.tv_sel_sl);
                holder.etSmsl = (EditText)convertView.findViewById( R.id.et_smsl);
                holder.tvGoodSize = (TextView)convertView.findViewById( R.id.tv_size );
                holder.tvLog = (TextView)convertView.findViewById( R.id.tv_log );
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            PickingRackBean bean = dataList.get(position);
            holder.tvGoodLoc.setText("货位："+bean.getGoods_loc());
            holder.tvDanw.setText("单位："+bean.getDanw());
            holder.tvName.setText("名称："+bean.getName());
            holder.tvTma.setText("条码"+bean.getTma());
            holder. tvSelsl.setText("应拣数量："+bean.getSel_sl());
            holder.etSmsl.setText("扫描数量："+bean.getSmsl());
            holder.tvGoodSize.setText("规格："+bean.getGsize());
            holder.tvLog.setText("备注："+bean.getLog());
            return convertView;
        }

    }



    static class ViewHolder{
        TextView tvGoodLoc;
        TextView tvDanw;
        TextView tvName;
        TextView tvTma;
        EditText etSmsl;
        TextView  tvSelsl;
        TextView tvGoodSize;
        TextView tvLog;
    }


}
