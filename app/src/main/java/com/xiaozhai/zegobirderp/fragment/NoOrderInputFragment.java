package com.xiaozhai.zegobirderp.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.device.ScanManager;
import android.device.scanner.configuration.PropertyID;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;
import com.xiaozhai.zegobirderp.bean.NoOrderInputBean;
import com.xiaozhai.zegobirderp.common.UrlConstants;
import com.xiaozhai.zegobirderp.utils.HttpUtils;
import com.xiaozhai.zegobirderp.utils.ProcessJsonData;

import java.util.ArrayList;
import java.util.List;

import static com.xiaozhai.zegobirderp.FuncDetailActivity.SCAN_ACTION;
import static com.xiaozhai.zegobirderp.common.ScanConstant.SCANNER_APP_ENABLE;

/**
 * Class name：无单入库主界面
 * Created by KAKA、Nadal on 2017/11/2 at 10:28.
 */

public class NoOrderInputFragment extends BaseFragment implements View.OnClickListener, View
        .OnKeyListener, View.OnFocusChangeListener {

    private static final int ADD =7 ;
    private ListView lvNoOrderInput;

    private EditText etPosition;
    private EditText etGoodCode;
    private TextView tvScanNumber;

    private  final int DATA_EMPTY = 5;
    private  final int RESULT_ERR_TIP = 4;
    private  final int RESULT_NO_ORDER =1 ;
    private  final int RESULT =2 ;
    private final int WHAT_COMPLESH=0;
    private final int GO_TO_SELECT=3;
    private final int FINISH=6;
    private NoOrderInputBean noOrderInputBeanResult;
    private  NoOrderInputAdapter noOrderInputAdapter;

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
                    Toast.makeText(mContext, "此商品不存在，请联系系统管理员新增商品信息", Toast.LENGTH_SHORT).show();
                    break;
                case  RESULT:
                    Toast.makeText(mContext, "ok", Toast.LENGTH_SHORT).show();
                    if(noOrderInputBeanResult!=null){
                         noOrderInputAdapter = new NoOrderInputAdapter
                                (noOrderInputBeanResult.getData());
                        lvNoOrderInput.setAdapter(noOrderInputAdapter);
                        etPosition.requestFocus();
                    }
                    tvScanNumber.setText("扫描数量："+noOrderInputAdapter.getTotalScan());
                    break;
                case  RESULT_ERR_TIP:
                    Toast.makeText(mContext, msg.getData().getString("errMsg")+"", Toast.LENGTH_SHORT).show();
                    break;
                case DATA_EMPTY:
                    Toast.makeText(mContext, "单号不能为空", Toast.LENGTH_SHORT).show();
                    break;
                case FINISH:
                    Toast.makeText(mContext, "入库成功了", Toast.LENGTH_SHORT)
                            .show();
                    mContext.finish();
                    break;
                case ADD:
                    tvScanNumber.setText("扫描数量："+noOrderInputAdapter.getTotalScan());
                    noOrderInputAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    //-------------------------
    private Vibrator mVibrator;
    private ScanManager mScanManager;
    private SoundPool soundpool = null;
    private int soundid;
    private String barcodeStr;
    private boolean isScaning = false;
    private int currentIndex=0;
    private BroadcastReceiver mScanReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            openScan("off");
            isScaning = false;
            soundpool.play(soundid, 1, 1, 0, 0, 1);
            mVibrator.vibrate(100);

            byte[] barcode = intent.getByteArrayExtra(ScanManager.DECODE_DATA_TAG);
            int barcodelen = intent.getIntExtra(ScanManager.BARCODE_LENGTH_TAG, 0);
            byte temp = intent.getByteExtra(ScanManager.BARCODE_TYPE_TAG, (byte) 0);
            android.util.Log.i("debug", "----codetype--" + temp);
            barcodeStr = new String(barcode, 0, barcodelen);

            //扫描得出来的直接查后台
            switch (currentIndex) {
                case  0:
                    etPosition.setText("");
                    etPosition.setText(barcodeStr);
                    etPosition.setSelection(etPosition.getText().length());
                    break;
                case  1:
                    etGoodCode.setText("");
                    etGoodCode.setText(barcodeStr);
                    etGoodCode.setSelection(etPosition.getText().length());
                    requestData(barcodeStr);
                    break;
               /* case  2:
                    etGoodCode.setText("");
                    etGoodCode.setText(barcodeStr);
                    etGoodCode.setSelection(etGoodCode.getText().length());
                    break;*/
                default:
                    return;
            }
            nextFocus();
        }

    };

    //-----------------------------

    private ArrayList<View> views;

    /**
     * 是否开启scan
     * @param on off关闭， on开启
     */
    private void openScan(String on) {
        Intent intent = new Intent(SCANNER_APP_ENABLE);
        intent.putExtra("scanner_app_enable", on);
        mContext.sendBroadcast(intent);
    }

    private void nextFocus(){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        openScan("on");
        currentIndex++;
        if(currentIndex>=views.size()){
            currentIndex=views.size()-1;
        }

        if(views.size()>currentIndex){
            View view = views.get(currentIndex);
            view.requestFocus();
        }
    }



    @Override
    protected View initView() {
        initTile("无单入库");
        isShowCommit(true);
        View view = View.inflate(mContext, R.layout.fragment_no_order_input, null);
        lvNoOrderInput = (ListView)view.findViewById( R.id.lv_no_input);
        etPosition = (EditText)view.findViewById( R.id.et_position );
        etGoodCode = (EditText)view.findViewById( R.id.et_good_code );
        tvScanNumber = (TextView)view.findViewById( R.id.tv_scan_number );
        views=new ArrayList<>();
        views.add(etPosition);
        views.add(etGoodCode);
        initListener();
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        mVibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
    }

    //----------------------------
    private void initScan() {
        // TODO Auto-generated method stub
        mScanManager = new ScanManager();
        mScanManager.openScanner();

        mScanManager.switchOutputMode( 0);
        soundpool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100); // MODE_RINGTONE
        soundid = soundpool.load("/etc/Scan_new.ogg", 1);
    }

    @Override
    public void onResume() {
        super.onResume();
        //        etOrderCode.addTextChangedListener(textWatcher);
        initScan();
        IntentFilter filter = new IntentFilter();
        int[] idbuf = new int[]{PropertyID.WEDGE_INTENT_ACTION_NAME, PropertyID.WEDGE_INTENT_DATA_STRING_TAG};
        String[] value_buf = mScanManager.getParameterString(idbuf);
        if(value_buf != null && value_buf[0] != null && !value_buf[0].equals("")) {
            filter.addAction(value_buf[0]);
        } else {
            filter.addAction(SCAN_ACTION);
        }
        mContext.registerReceiver(mScanReceiver, filter);
        startScan();
        mContext.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        //        etOrderCode.removeTextChangedListener(textWatcher);

        if(mScanManager != null) {
            mScanManager.stopDecode();
            isScaning = false;
        }
        mContext.unregisterReceiver(mScanReceiver);
        System.out.println("onPause");
        mContext.hideSoftInputFromWindow(mContext.getCurrentFocus());
    }

    private void startScan() {
        mScanManager.stopDecode();
        isScaning = true;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mScanManager.startDecode();
    }

    private void initListener() {
        btnCommit.setOnClickListener(this);
        etGoodCode.setOnKeyListener(this);
        etGoodCode.setOnFocusChangeListener(this);
        etPosition.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_commit){
            return ;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
            switch (v.getId()) {
                case  R.id.et_good_code:
                    String content = etGoodCode.getText().toString();
                    if(!TextUtils.isEmpty(content)){
                        //请求数据
                        requestData(content);
                    }else{
                        handler.sendEmptyMessage(DATA_EMPTY);
                    }
                    break;
                case  R.id.et_position:
                    mContext.hideSoftInputFromWindow(etPosition);
                    break;
            }
            mContext.hideSoftInputFromWindow(v);
            return true;
        }
        return false;
    }

    private void requestData(String content) {
        HttpUtils.post(mContext, UrlConstants.NO_ORDER_INPUT,"{\n" +
                "    \"ProductModel\": \""+content+"\"\n" +
                "}", new HttpUtils
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
                         noOrderInputBeanResult = ProcessJsonData
                                .processData(resultStr, NoOrderInputBean.class);
                        if(noOrderInputBeanResult.getResultCode().equals("0000")){
                            if(noOrderInputAdapter!=null && noOrderInputAdapter.getCount()>0) {
                                noOrderInputAdapter.addData(noOrderInputBeanResult.getData());
                                int total=noOrderInputAdapter.getTotalScan();
                                handler.sendEmptyMessage(ADD);
                            }else{
                                handler.sendEmptyMessage(RESULT);
                            }
                        }else{
                            Message msg = Message.obtain();
                            msg.what=RESULT_ERR_TIP;
                            Bundle bundle = new Bundle();
                            bundle.putString("errMsg",noOrderInputBeanResult.getMessage());
                            msg.setData(bundle);
                            handler.sendMessage(msg);
                        }
                    }
                }).start();

            }
        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            switch (v.getId()) {
                case  R.id.et_good_code:
                     currentIndex=1;
                    break;
                case  R.id.et_position:
                     currentIndex=0;
                    break;
                default:
                    break;
            }
        }
    }

    private class NoOrderInputAdapter extends BaseAdapter {
        private NoOrderInputBean.DataBean  data;
        private List<NoOrderInputBean.DataBean> dataList;

        public NoOrderInputAdapter(NoOrderInputBean.DataBean data) {
            this.data=data;
            dataList=new ArrayList<>();
            addData(data);
        }


        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public NoOrderInputBean.DataBean  getItem(int position) {
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
                holder=new ViewHolder();
                convertView=View.inflate(mContext,R.layout.item_noorder_scan_input,null);
                holder.tvName = (TextView)convertView.findViewById( R.id.tv_name );
                holder.tvCode = (TextView)convertView.findViewById( R.id.tv_code );
                holder.tvNumber = (TextView)convertView.findViewById( R.id.tv_number );
                holder.tvSize = (TextView)convertView.findViewById( R.id.tv_size );
                holder.tvTime = (TextView)convertView.findViewById( R.id.tv_time );
                holder.tvRemark = (TextView)convertView.findViewById( R.id.tv_remark );
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            NoOrderInputBean.DataBean  item = getItem(position);
            holder.tvName.setText("名称："+item.getProductName());
            holder.tvCode.setText("商品条码："+item.getProductModel());
            holder.tvNumber.setText("商品编号："+item.getProductCode());
            holder.tvSize.setText("数量："+item.getQuantity());
            holder.tvTime.setText("生产日期："+"2017.10.10");
            return convertView;
        }

        private boolean isContains(String code){
            for(NoOrderInputBean.DataBean  item: dataList ) {
            	if(item.getProductModel().equals(code)) {
            	    return true;
            	}
            }
            return false;
        }

        private NoOrderInputBean.DataBean getDataBean(String code){
            for(NoOrderInputBean.DataBean  item: dataList ) {
                if(item.getProductModel().equals(code)) {
                    return item;
                }
            }
            return  null;
        }

        public void addData(NoOrderInputBean.DataBean data) {
            NoOrderInputBean.DataBean dataBean=null;
            String productModel = data.getProductModel();
            if(isContains(productModel)) {
                 dataBean = this.getDataBean(productModel);
            }else{
                dataBean=data;
                dataList.add(data);
            }
            int quantity = dataBean.getQuantity();
            quantity++;
            dataBean.setQuantity(quantity);
            handler.sendEmptyMessage(ADD);
        }

        public int getTotalScan() {
            int total=0;
            for (int i = 0; i < dataList.size(); i++) {
                NoOrderInputBean.DataBean dataBean = dataList.get(i);
                total+=dataBean.getQuantity();
            }
            return  total;
        }
    }

    static class ViewHolder{
        TextView tvName;
        TextView tvCode;
        TextView tvNumber;
        TextView tvSize;
        TextView tvTime;
        TextView tvRemark;
    }
    }

