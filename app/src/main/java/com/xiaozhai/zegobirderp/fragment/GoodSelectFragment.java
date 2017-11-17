package com.xiaozhai.zegobirderp.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;
import com.xiaozhai.zegobirderp.bean.GoodSelectBean;
import com.xiaozhai.zegobirderp.utils.ProcessJsonData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nadal on 2017/11/10.
 */

public class GoodSelectFragment extends BaseFragment {

    private ListView lvGoodSelect;
    private final int FROM_NORMAL_INPUT_DATA=0;
    private GoodSelectAdapter goodSelectAdapter;
    private boolean isSubmit=false;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what) {
               case  FROM_NORMAL_INPUT_DATA:
                  if(goodSelectBean!=null) {
                      goodSelectAdapter= new GoodSelectAdapter(goodSelectBean.getData());
                      lvGoodSelect.setAdapter(goodSelectAdapter);
                  }
                   break;
           }
        }
    };

    @Override
    protected View initView() {
        initTile("商品选择");
        isShowCommit(true);
        View view = View.inflate(mContext, R.layout.fragment_good_select, null);
        lvGoodSelect = (ListView) view.findViewById(R.id.lv_good_select);
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            isSubmit=false;
            mContext.ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.goToFragment(0);
                }
            });
            mContext.btnCommit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show();
                    List<GoodSelectBean.DataBean> checkData = goodSelectAdapter.getCheckData();
                    if(checkData.size()==0){
                        Toast.makeText(mContext, "你没有选择任何数据哦", Toast.LENGTH_SHORT).show();
                    }else{
                        mContext.goodSelectUpdate=true;
                        mContext.dataMap.put("checkData",checkData);
                    }

                    goodSelectAdapter.clearCheckData();
                    isSubmit=true;
                    goodSelectAdapter.notifyDataSetChanged();
                    mContext.goToFragment(0);
                }
            });
        }
    }

    private GoodSelectBean goodSelectBean;

    private boolean isCheck=false;

    @Override
    protected void initData() {
        View v = View.inflate(mContext, R.layout.item_good_select, null);
        CheckBox cbHeader = (CheckBox) v.findViewById(R.id.cb_select);
        cbHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheck=(!isCheck);
                goodSelectAdapter.revertCheck();
            }
        });
        lvGoodSelect.addHeaderView(v);
        if(mContext.dataMap.containsKey("goodJson")){
            System.out.println("有");
            final String goodJson = (String) mContext.dataMap.get("goodJson");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    goodSelectBean= ProcessJsonData.processData(goodJson, GoodSelectBean
                            .class);
                    handler.sendEmptyMessage(FROM_NORMAL_INPUT_DATA);
                }
            }).start();

        }else{
             System.out.println("没有");
        }

        initListener();

    }

    private void initListener() {
        mContext.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.goToFragment(0);
            }
        });
        mContext.btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show();
                List<GoodSelectBean.DataBean> checkData = goodSelectAdapter.getCheckData();
                if(checkData.size()==0){
                    Toast.makeText(mContext, "你没有选择任何数据哦", Toast.LENGTH_SHORT).show();
                }else{
                    mContext.goodSelectUpdate=true;
                    mContext.dataMap.put("checkData",checkData);
                }

                goodSelectAdapter.clearCheckData();
                isSubmit=true;
                goodSelectAdapter.notifyDataSetChanged();
                mContext.goToFragment(0);
            }
        });
    }



    private class GoodSelectAdapter extends BaseAdapter {
        private  List<GoodSelectBean.DataBean> data;
        private  List<GoodSelectBean.DataBean> selectData;
        private Map<Integer,Boolean> isCheckMap;
        private ArrayList<Integer> checkList;

        public GoodSelectAdapter(List<GoodSelectBean.DataBean> data) {
            this.data=data;
            isCheckMap=new HashMap<>();
            initNoCheckMap();
            checkList=new ArrayList<>();
            selectData=new ArrayList<>();
        }

        private void initNoCheckMap() {
            for (int i = 0; i < data.size(); i++) {
                isCheckMap.put(i,false);
            }
        }
        private void initCheckMap() {
            for (int i = 0; i < data.size(); i++) {
                isCheckMap.put(i,true);
            }
        }

        private List<GoodSelectBean.DataBean> getCheckData(){
              List<GoodSelectBean.DataBean> timpData=new ArrayList<>();
            for (Map.Entry<Integer,Boolean>  checkItem : isCheckMap.entrySet() ) {
            	if(checkItem.getValue()){
                    checkList.add(checkItem.getKey());
                }
            }
            for (int i = 0; i < checkList.size(); i++) {
                  selectData.add(data.get(checkList.get(i)));
            }
            timpData.addAll(selectData);
            return  timpData;
        }

        private  void clearCheckData(){
            data.removeAll(selectData);
            isCheckMap.clear();
            selectData.clear();
            checkList.clear();
        }

        //        private void initDataList() {
//            dataList=new ArrayList<GoodSelectBean.DataBean>();
//            for(int i=0;i<10;i++){
//                GoodSelectBean.DataBean bean=new GoodSelectBean.DataBean();
//                bean.setEnterQuantity(11);
//                bean.setProductName("随便");
//                bean.setProductCode("000111");
//                data.add(bean);
//            }
//        }


        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public GoodSelectBean.DataBean getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

      /*  @Override
        public int getViewTypeCount() {
            return 2;
        }

        private final  int TYPE_HEADER=0;
        private final  int TYPE_NORMAL=1;

        @Override
        public int getItemViewType(int position) {
            if(position==0){
                return TYPE_HEADER;
            }else{
                return TYPE_NORMAL;
            }
        }*/

        private int currentPosition;

        private void revertCheck(){
            if(isCheck){
                initCheckMap();
            }else{
                initNoCheckMap();
            }
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GoodSelectFragment.ViewHolder holder=null;
            currentPosition=position;
            if(convertView==null){
                convertView=View.inflate(mContext,R.layout.item_good_select,null);
                holder=new GoodSelectFragment.ViewHolder();
                holder.tvGoodNumber = (TextView)convertView.findViewById( R.id.tv_good_number );
                holder.tvGoodCode = (TextView)convertView.findViewById( R.id.tv_good_code );
                holder.tvGoodName = (TextView)convertView.findViewById( R.id.tv_good_name );
                holder.cbSelect = (CheckBox)convertView.findViewById( R.id.cb_select );
                holder.cbSelect.setTag(position);

                holder.cbSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer tag = (Integer) v.getTag();
                        if(isCheckMap!=null && isCheckMap.containsKey(tag)){
                            isCheckMap.put(tag,!isCheckMap.get(tag));
                        }else{
                            isCheckMap.put(tag,true);
                        }
                    }
                });
                convertView.setTag(holder);
            }else{
                holder= (GoodSelectFragment.ViewHolder) convertView.getTag();
            }
            GoodSelectBean.DataBean bean = data.get(position);
            holder.tvGoodName.setText(bean.getProductName()+"");
                if(isCheckMap.containsKey(position)) {
                    holder.cbSelect.setChecked(isCheckMap.get(position));
                }else{
                    holder.cbSelect.setChecked(false);
                }
            holder.cbSelect.setTag(position);

            holder.tvGoodNumber.setText(bean.getEnterQuantity()+"");
            holder.tvGoodCode.setText(bean.getProductCode()+"");
            return convertView;
        }

    }

    static class ViewHolder{
         TextView tvGoodNumber;
         TextView tvGoodCode;
         TextView tvGoodName;
         CheckBox cbSelect;
    }
}
