package com.xiaozhai.zegobirderp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaozhai.zegobirderp.FuncDetailActivity;
import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;
import com.xiaozhai.zegobirderp.bean.CheckTackBean;
import com.xiaozhai.zegobirderp.utils.HttpUtils;
import com.xiaozhai.zegobirderp.utils.ProcessJsonData;

import java.util.List;

/**
 * Created by Nadal on 2017/10/31.
 */

public class CheckTackFragment extends BaseFragment {

    private ListView lvCheckTack;

    private final int RESULT=0;
    private  final int RESULT_ERR_TIP = 1;
    private CheckTackAdapter checkTackAdapter;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case  RESULT_ERR_TIP:
                    Toast.makeText(mContext, msg.getData().getString("errMsg")+"", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT:
                    if(checkTackBeanResult!=null) {
                         checkTackAdapter = new CheckTackAdapter(checkTackBeanResult.getData());
                         lvCheckTack.setAdapter(checkTackAdapter);
                    }
                    break;
            }
        }
    };


    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_check_tack, null);
        lvCheckTack = (ListView)view.findViewById( R.id.lv_check_tack );
        System.out.println("listview:"+lvCheckTack);
        FuncDetailActivity activity=(FuncDetailActivity)mContext;
        activity.btnCommit.setVisibility(View.GONE);
        initListener();
        return view;
    }

    private void initListener() {
        lvCheckTack.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String checkCode = checkTackAdapter.getCheckCode(position);
                mContext.dataMap.put("checkCode",checkCode);
                mContext.goToFragment(8);
            }
        });
    }

    private CheckTackBean checkTackBeanResult;

    @Override
    protected void initData() {
        super.initData();
        HttpUtils.post(mContext, "http://119.29.90.197:8087/api/Storage/GetStorageCheckList",
                "{\"UserId\":1,\"PageSize\":10,\"PageIndex\":1}", new HttpUtils.OnComplish() {
                    @Override
                    public void onFailure(String errMsg) {
                        Toast.makeText(mContext, "失败了", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(final String resultStr) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                 checkTackBeanResult = ProcessJsonData.processData
                                        (resultStr, CheckTackBean.class);
                                if(checkTackBeanResult.getResultCode().equals("0000")){
                                    mHandler.sendEmptyMessage(RESULT);
                                }else{
                                    Message msg = Message.obtain();
                                    msg.what=RESULT_ERR_TIP;
                                    Bundle bundle = new Bundle();
                                    bundle.putString("errMsg",checkTackBeanResult.getMessage());
                                    msg.setData(bundle);
                                    mHandler.sendMessage(msg);
                                }
                            }
                        }).start();
                    }
                });
    }



    private class CheckTackAdapter extends BaseAdapter {

        private List<CheckTackBean.DataBean > dataList;

        public CheckTackAdapter(List<CheckTackBean.DataBean >  dataList) {
          this.dataList=dataList;
        }



        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public CheckTackBean.DataBean  getItem(int position) {

            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        public String getCheckCode(int position){
            return getItem(position).getCheckCode();
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
            CheckTackBean.DataBean dataBean = dataList.get(position);
            holder.tvCheckId.setText(dataBean.getCheck_Id()+"");
            holder.tvState.setText(dataBean.getStatusTypeName());
            holder.tvStateTask.setText("未分配");
            return convertView;
        }

    }
    static class  ViewHolder{
        TextView tvCheckId;
        TextView tvState;
        TextView tvStateTask;
    }
}
