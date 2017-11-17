package com.xiaozhai.zegobirderp.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaozhai.zegobirderp.FuncDetailActivity;
import com.xiaozhai.zegobirderp.MainActivity;
import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;
import com.xiaozhai.zegobirderp.bean.GoodsEntryBean;

import java.util.ArrayList;

/**
 * Prooject name：常规上架主界面
 * Created by KAKA on ${DATE}.
 */

public class GoodEntryFragment extends BaseFragment implements View.OnClickListener {

    private ListView lvGoodInput;


    @Override
    protected View initView() {
        initTile("常规上架");
        isShowCommit(true);
        View view = View.inflate(mContext, R.layout.fragment_good_entry, null);
        lvGoodInput = (ListView)view.findViewById( R.id.lv_good_entry );
        initListener();
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        lvGoodInput.setAdapter(new GoodEntryAdapter(mContext));
    }

    private void initListener() {
        btnCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_commit){
        }
    }

    private class GoodEntryAdapter extends BaseAdapter {
        private final FuncDetailActivity context;
        private ArrayList<GoodsEntryBean> dataList;

        public GoodEntryAdapter(FuncDetailActivity context) {
            this.context=context;
            initListData();
        }

        private void initListData() {
            dataList=new ArrayList<GoodsEntryBean>();
            for (int i = 0; i < 10; i++) {
                GoodsEntryBean GoodsEntryBean = new GoodsEntryBean();
                GoodsEntryBean.setName("荣耀8");
                GoodsEntryBean.setTiaoma(1);
                GoodsEntryBean.setSup(0);
                GoodsEntryBean.setSmsl(1);
                GoodsEntryBean.setT_loc("1");
                GoodsEntryBean.setLoc("1");
                GoodsEntryBean.setDay("荣耀8");
                GoodsEntryBean.setLog("荣耀8");
                GoodsEntryBean.setGoods_size("荣耀8");
                dataList.add(GoodsEntryBean);
            }
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public GoodsEntryBean getItem(int position) {
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
                convertView=View.inflate(mContext,R.layout.item_good_entry,null);
                holder=new ViewHolder();
                holder.tvName = (TextView)convertView.findViewById( R.id.tv_name );
                holder.tvGoodsnum = (TextView)convertView.findViewById( R.id.tv_tiaoma );
                holder.tvSup = (TextView)convertView.findViewById( R.id.tv_sup );
                holder.etSmsl = (EditText)convertView.findViewById( R.id.et_smsl);
                holder. tvTloc = (TextView)convertView.findViewById( R.id.tv_t_loc );
                holder.tvLoc = (TextView)convertView.findViewById( R.id.tv_loc);
                holder.tvDay = (TextView)convertView.findViewById( R.id.tv_day );
                holder.tvLog = (TextView)convertView.findViewById( R.id.tv_log );
                holder.tvGoodSize = (TextView)convertView.findViewById( R.id.tv_goods_size );
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            GoodsEntryBean bean = dataList.get(position);
            holder.tvName.setText("名称："+bean.getName());
            holder.tvGoodsnum.setText("条码："+bean.getTiaoma());
            holder.tvSup.setText("应上数量："+bean.getSup());
            holder.etSmsl.setText("扫描数量："+bean.getSmsl());
            holder.tvTloc.setText("推荐位："+bean.getT_loc());
            holder.tvLoc.setText("上架位："+bean.getLoc());
            holder.tvDay.setText("生产日期："+bean.getDay());
            holder.tvLog.setText("备注："+bean.getLog());
            holder.tvGoodSize.setText("商品规格："+bean.getGoods_size());
            return convertView;
        }

    }



    static class ViewHolder{
        TextView tvName;
        TextView tvGoodsnum;
        TextView tvSup;
        EditText etSmsl;
        TextView tvTloc;
        TextView tvLoc;
        TextView tvDay;
        TextView tvLog;
        TextView tvGoodSize;
    }


}
