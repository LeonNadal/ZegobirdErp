package com.xiaozhai.zegobirderp.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaozhai.zegobirderp.FuncDetailActivity;
import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;
import com.xiaozhai.zegobirderp.bean.CheckTackBean;

import java.util.List;

/**
 * Project name：拣货任务主界面
 * Created by KAKA on 2017/11/2.
 */
public class PickingTaskFragment extends BaseFragment {
    private ListView lvPickingTask;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_picking_task, null);
        lvPickingTask = (ListView)view.findViewById( R.id.lv_picking_task );
        System.out.println("listview:"+lvPickingTask);
        FuncDetailActivity activity=(FuncDetailActivity)mContext;
        activity.btnCommit.setVisibility(View.GONE);
        initTile("拣货任务");
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        lvPickingTask.setAdapter(new PickingTaskFragment.PickingTaskAdapter(mContext));
    }

    private class PickingTaskAdapter extends BaseAdapter {
        private final Context context;

        private List<CheckTackBean> dataList;

        public PickingTaskAdapter(Context context) {
            this.context=context;
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
            PickingTaskFragment.ViewHolder holder=null;
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
            return convertView;
        }

    }
    static class  ViewHolder{
        TextView tvCheckId;
        TextView tvState;
        TextView tvStateTask;
    }
}
