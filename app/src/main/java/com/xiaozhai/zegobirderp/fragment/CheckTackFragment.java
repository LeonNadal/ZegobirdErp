package com.xiaozhai.zegobirderp.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaozhai.zegobirderp.FuncDetailActivity;
import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;
import com.xiaozhai.zegobirderp.bean.CheckTackBean;
import com.xiaozhai.zegobirderp.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nadal on 2017/10/31.
 */

public class CheckTackFragment extends BaseFragment {

    private ListView lvCheckTack;




    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_check_tack, null);
        lvCheckTack = (ListView)view.findViewById( R.id.lv_check_tack );
        System.out.println("listview:"+lvCheckTack);
        FuncDetailActivity activity=(FuncDetailActivity)mContext;
        activity.btnCommit.setVisibility(View.GONE);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        lvCheckTack.setAdapter(new CheckTackAdapter(mContext));
        HttpUtils.post(mContext, "http://119.29.90.197:8087/api/Storage/GetStorageCheckList",
                "{\"UserId\":1,\"PageSize\":10,\"PageIndex\":1}", new HttpUtils.OnComplish() {
                    @Override
                    public void onFailure(String errMsg) {
                        Toast.makeText(mContext, "失败了", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String resultStr) {
                        System.out.println("result:"+resultStr);
                    }
                });
    }

    private class CheckTackAdapter extends BaseAdapter {
        private final Context context;

        private List<CheckTackBean> dataList;

        public CheckTackAdapter(Context context) {
            this.context=context;
            initList();
        }

        //假数据
        private void initList() {
            dataList=new ArrayList<CheckTackBean>();
            for(int i=0;i<10;i++){
                CheckTackBean bean = new CheckTackBean();
                bean.setId("14545845"+i);
                bean.setState("盘点");
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
            ViewHolder holder=null;
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
