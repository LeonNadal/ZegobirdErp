package com.xiaozhai.zegobirderp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;
import com.xiaozhai.zegobirderp.bean.GoodQueryBean;
import com.xiaozhai.zegobirderp.common.UrlConstants;
import com.xiaozhai.zegobirderp.utils.HttpUtils;
import com.xiaozhai.zegobirderp.utils.ProcessJsonData;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by Nadal on 2017/11/1.
 */

public class GoodQueryFragment extends BaseFragment implements View.OnKeyListener {

    private ListView lvGoodQuery;
    private TextView tvGoodName;
    private TextView tvGoodNumber;
    private EditText etGoodCode;

    private  final int DATA_EMPTY = 5;
    private  final int RESULT_ERR_TIP = 4;
    private  final int RESULT_NO_ORDER =1 ;
    private  final int RESULT =2 ;
    private final int WHAT_COMPLESH=0;
    private final int GO_TO_SELECT=3;

    private GoodQueryAdapter goodQueryAdapter;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case  GO_TO_SELECT:
                    mContext.goToFragment(13);
                    break;
                case  WHAT_COMPLESH:

                    break;
                case  RESULT_NO_ORDER:
                    Toast.makeText(mContext, "该单号不存在", Toast.LENGTH_SHORT).show();
                    bottomDivider.setVisibility(View.INVISIBLE);
                    break;
                case  RESULT:
                    if(goodQueryBean!=null && goodQueryBean.getData().size()!=0){
                       /* GoodInputFragment.GoodInputAdapter goodInputAdapter = new GoodInputFragment.GoodInputAdapter
                                ( goodQueryBean.getData().getProductInfo());
                        lvGoodInput.setAdapter(goodInputAdapter);*/
                        tvGoodName.setText(goodQueryBean.getData().get(0).getProductName());
                        tvGoodNumber.setText(goodQueryBean.getData().get(0).getProductCode());
                        if(goodQueryAdapter==null) {
                            goodQueryAdapter = new GoodQueryAdapter(goodQueryBean
                                    .getData());
                            lvGoodQuery.setAdapter(goodQueryAdapter);
                        }
                        goodQueryAdapter.notifyDataSetChanged();
                        bottomDivider.setVisibility(View.VISIBLE);
                    }else{
                        Toast.makeText(mContext, "该单号没有数据", Toast.LENGTH_SHORT).show();
                        tvGoodName.setText("");
                        tvGoodNumber.setText("");
                        if(goodQueryAdapter!=null && goodQueryAdapter.getCount()>0){
                            goodQueryAdapter.clear();
                            goodQueryAdapter.notifyDataSetChanged();
                        }
                        bottomDivider.setVisibility(View.INVISIBLE);
                    }
                    break;
                case  RESULT_ERR_TIP:
                    Toast.makeText(mContext, msg.getData().getString("errMsg")+"", Toast.LENGTH_SHORT).show();
                    break;
                case DATA_EMPTY:
                    Toast.makeText(mContext, "单号不能为空", Toast.LENGTH_SHORT).show();
                    tvGoodName.setText("");
                    tvGoodNumber.setText("");
                    if(goodQueryAdapter!=null && goodQueryAdapter.getCount()>0){
                        goodQueryAdapter.clear();
                        goodQueryAdapter.notifyDataSetChanged();
                    }
                    bottomDivider.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    };
    private GoodQueryBean goodQueryBean;
    private Map<String,String> jsonMap=new WeakHashMap<>();
    private Gson gson=new Gson();
    private String lastOrderCode="";

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
            //处理事件
            switch (v.getId()) {
                case  R.id.et_good_code:
                    String content = etGoodCode.getText().toString();
                   if(!lastOrderCode.equals(content)) {
                       if(!TextUtils.isEmpty(content)){
                           //请求数据
                           lastOrderCode=content;

                           jsonMap.put("ProductCode",content);
                           jsonMap.put("PageSize","100");
                           jsonMap.put("PageIndex","1");
                           String s = gson.toJson(jsonMap).toString();
                           System.out.println("json:"+s);
                           HttpUtils.post(mContext,UrlConstants.GOOD_QUERY,s, new HttpUtils
                                   .OnComplish() {
                               @Override
                               public void onFailure(String errMsg) {
                                   handler.sendEmptyMessage(RESULT_NO_ORDER);
                               }

                               @Override
                               public void onResponse(final String resultStr) {
                                   new Thread(new Runnable() {
                                       @Override
                                       public void run() {
                                           goodQueryBean = ProcessJsonData.processData
                                                   (resultStr, GoodQueryBean.class);
                                           if(goodQueryBean.getResultCode().equals("0000")){
                                               handler.sendEmptyMessage(RESULT);
                                           }else{
                                               Message msg = Message.obtain();
                                               msg.what=RESULT_ERR_TIP;
                                               Bundle bundle = new Bundle();
                                               bundle.putString("errMsg",goodQueryBean.getMessage());
                                               msg.setData(bundle);
                                               handler.sendMessage(msg);
                                           }
                                       }
                                   }).start();

                               }
                           });
                       }else{
                           handler.sendEmptyMessage(DATA_EMPTY);
                       }
                   }
                    break;
            }
            return true;
        }
        return false;
    }

    private  View bottomDivider;

    @Override
    protected View initView() {
        initTile("商品查询");
        isShowCommit(false);
        View view = View.inflate(mContext, R.layout.fragment_good_query, null);
        lvGoodQuery= (ListView) view.findViewById(R.id.lv_good_query);
            tvGoodName = (TextView)view.findViewById( R.id.tv_good_name );
            tvGoodNumber = (TextView)view.findViewById( R.id.tv_good_number );
            etGoodCode= (EditText) view.findViewById(R.id.et_good_code);
         bottomDivider = view.findViewById(R.id.bottom_divider);
        etGoodCode.setOnKeyListener(this);
        etGoodCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mContext.showSoftInput(v);
                }else{
                    mContext.hideSoftInputFromWindow(v);
                }
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        tvGoodName.setText("");
        tvGoodNumber.setText("");
    }


    public class GoodQueryAdapter extends BaseAdapter {

        private List<GoodQueryBean.DataBean> data;

        public GoodQueryAdapter(List<GoodQueryBean.DataBean> data) {
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
        public GoodQueryBean.DataBean getItem(int position) {
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
                convertView=View.inflate(mContext,R.layout.item_good_query,null);
                holder=new ViewHolder();
                holder.tvPosition = (TextView)convertView.findViewById( R.id.tv_position );
                holder.tvNumber = (TextView)convertView.findViewById( R.id.tv_number );
                holder.tvEnableNumber = (TextView)convertView.findViewById( R.id.tv_enable_number );
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            GoodQueryBean.DataBean item = getItem(position);
            holder.tvPosition.setText("所在货位："+item.getPositionName());
            holder.tvNumber.setText("库存数量:"+item.getProductStorage());
            holder.tvEnableNumber.setText("有效库存："+item.getValidStorage());
            return convertView;
        }

    }

    static class ViewHolder{
         TextView tvPosition;
         TextView tvNumber;
         TextView tvEnableNumber;
    }

}
