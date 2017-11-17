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
import com.xiaozhai.zegobirderp.bean.ShelfTaskBean;

import java.util.ArrayList;

/**
 * Prooject name：生成上架任务主界面
 * Created by KAKA on ${DATE}.
 */

public class CreatShelfTaskFragment extends BaseFragment implements View.OnClickListener {

    private ListView lvCreatShelfTask;


    @Override
    protected View initView() {
        initTile("上架货位设置");
        isShowCommit(true);
        View view = View.inflate(mContext, R.layout.fragment_create_shelf_task, null);
        lvCreatShelfTask = (ListView)view.findViewById( R.id.lv_create_task );
        initListener();
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        lvCreatShelfTask.setAdapter(new CreatShelfTaskAdapter(mContext));
    }

    private void initListener() {
        btnCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_commit){
            mContext.goToFragment(2);
        }
    }

    private class CreatShelfTaskAdapter extends BaseAdapter {
        private final FuncDetailActivity context;
        private ArrayList<ShelfTaskBean> dataList;

        public CreatShelfTaskAdapter(FuncDetailActivity context) {
            this.context=context;
            initListData();
        }

        private void initListData() {
            dataList=new ArrayList<ShelfTaskBean>();
            for (int i = 0; i < 10; i++) {
                ShelfTaskBean ShelfTaskBean = new ShelfTaskBean();
                ShelfTaskBean.setName("荣耀8");
                ShelfTaskBean.setBhao(0);
                ShelfTaskBean.setTma(0);
                ShelfTaskBean.setSl(1);
                ShelfTaskBean.setUp_loc(0);
                ShelfTaskBean.setUp_sl(0);
                dataList.add(ShelfTaskBean);
            }
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public ShelfTaskBean getItem(int position) {
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
                convertView=View.inflate(mContext,R.layout.item_create_task,null);
                holder=new ViewHolder();
                holder.tvName = (TextView)convertView.findViewById( R.id.tv_name );
                holder.tvBhao = (TextView)convertView.findViewById( R.id.tv_bhao);
                holder.tvTma = (TextView)convertView.findViewById( R.id.tv_tma);
                holder.tvSl = (TextView)convertView.findViewById( R.id.tv_sl );
                holder. etUploc = (EditText)convertView.findViewById( R.id.et_up_loc );
                holder.etUpsl = (EditText)convertView.findViewById( R.id.et_up_sl);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            ShelfTaskBean bean = dataList.get(position);
            holder.tvName.setText("名称："+bean.getName());
            holder.tvBhao.setText("商品条码："+bean.getBhao());
            holder.tvTma.setText("商品编号："+bean.getTma());
            holder.tvSl.setText("扫描数量："+bean.getSl());
            holder.etUploc.setText("清单数量："+bean.getUp_loc());
            holder.etUpsl.setText("已收数量："+bean.getUp_sl());
            return convertView;
        }

    }



    static class ViewHolder{
        TextView tvName;
        TextView tvBhao;
        TextView tvTma;
        TextView tvSl;
        EditText etUploc;
        EditText etUpsl;
    }

}
