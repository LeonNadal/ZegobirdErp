package com.xiaozhai.zegobirderp.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;
import com.xiaozhai.zegobirderp.bean.CheckTackBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Project name：上架任务主界面
 * Created by KAKA on 2017/11/2.
 */
public class EntryTaskFragment extends BaseFragment implements View.OnClickListener ,AdapterView.OnItemClickListener {
    private ListView lvEntryTask;
    
    @Override
    protected View initView() {
        initTile("上架任务");
        isShowCommit(true);
        btnCommit.setText("生成上架任务");
        View view = View.inflate(mContext, R.layout.fragment_entry_task, null);
        lvEntryTask = (ListView)view.findViewById( R.id.lv_entry_task );
        initListener();
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        lvEntryTask.setAdapter(new EntryTaskFragment.EntryTaskAdapter(mContext));
    }

    private void initListener() {
        btnCommit.setOnClickListener(this);
        lvEntryTask.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(view.getId()==R.id.lv_entry_task){
            mContext.goToFragment(3);
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_commit){
            mContext.initTitle("上架货位设置");
            btnCommit.setText("提交");
            mContext.goToFragment(15);


        }
    }



    private class EntryTaskAdapter extends BaseAdapter {
        private final Context context;

        private List<CheckTackBean> dataList;

        public EntryTaskAdapter(Context context) {
            this.context=context;
            initList();
        }

        //假数据
        private void initList() {
            dataList=new ArrayList<CheckTackBean>();
            for(int i=0;i<10;i++){
                CheckTackBean bean = new CheckTackBean();
                bean.setId("14545845"+i);
                bean.setState("待上架");
                bean.setTack_state("未分配");
                dataList.add(bean);
            }
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public CheckTackBean getItem(int position) {

            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            EntryTaskFragment.ViewHolder holder=null;
            if(convertView==null){
                convertView=View.inflate(mContext,R.layout.item_check_tack,null);
                holder=new ViewHolder();
                holder.tvCheckId= (TextView) convertView.findViewById(R.id.tv_check_id);
                holder.tvState= (TextView) convertView.findViewById(R.id.tv_state);
                holder.tvStateTask= (TextView) convertView.findViewById(R.id.tv_state_task);
                convertView.setTag(holder);

            }else {
                holder= (ViewHolder) convertView.getTag();
            }
            CheckTackBean checkTackBean = dataList.get(position);
            holder.tvCheckId.setText(checkTackBean.getId());
            holder.tvState.setText(checkTackBean.getState());
            holder.tvStateTask.setText(checkTackBean.getTack_state());
            return convertView;
        }

    }
    static class  ViewHolder{
        TextView tvCheckId;
        TextView tvState;
        TextView tvStateTask;
    }
    }
