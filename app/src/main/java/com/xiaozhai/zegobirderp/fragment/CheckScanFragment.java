package com.xiaozhai.zegobirderp.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.device.ScanManager;
import android.device.scanner.configuration.PropertyID;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaozhai.zegobirderp.FuncDetailActivity;
import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;
import com.xiaozhai.zegobirderp.bean.CheckScanBean;

import java.util.ArrayList;

import static com.xiaozhai.zegobirderp.FuncDetailActivity.SCAN_ACTION;
import static com.xiaozhai.zegobirderp.common.ScanConstant.SCANNER_APP_ENABLE;

/**
 * Created by Nadal on 2017/10/31.
 */

public class CheckScanFragment extends BaseFragment implements View.OnClickListener {

    private ListView lvCheckScan;
    private  ArrayList<View> views;

    @Override
    protected View initView() {
        initTile("盘点扫描");
        isShowCommit(true);
        View view = View.inflate(mContext, R.layout.fragment_check_scan, null);
        lvCheckScan = (ListView)view.findViewById( R.id.lv_check_scan );
        tv_OrderCode = (EditText) view.findViewById(R.id.tv_OrderCode);
        etCheckPosition = (EditText)view.findViewById( R.id.et_check_position );
        etGoodCode = (EditText)view.findViewById( R.id.et_good_code );

        initListener();
        return view;
    }

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
            switch (currentIndex) {
                case  0:
                    tv_OrderCode.setText("");
                    tv_OrderCode.setText(barcodeStr);
                    tv_OrderCode.setSelection(tv_OrderCode.getText().length());
                    break;
                case  1:
                    etCheckPosition.setText("");
                    etCheckPosition.setText(barcodeStr);
                    etCheckPosition.setSelection(etCheckPosition.getText().length());
                    break;
                case  2:
                    etGoodCode.setText("");
                    etGoodCode.setText(barcodeStr);
                    etGoodCode.setSelection(etGoodCode.getText().length());
                    break;
                default:
                    openScan("off");
                    return;
            }
            nextFocus();
        }

    };

    private void nextFocus(){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        openScan("on");
        currentIndex++;

        if(views.size()!=currentIndex){
            View view = views.get(currentIndex);
            view.requestFocus();
        }
    }

    /**
     * 是否开启scan
     * @param on off关闭， on开启
     */
    private void openScan(String on) {
        Intent intent = new Intent(SCANNER_APP_ENABLE);
        intent.putExtra("scanner_app_enable", on);
        mContext.sendBroadcast(intent);
    }

    private EditText tv_OrderCode;
    private EditText etCheckPosition;
    private EditText etGoodCode;




    @Override
    protected void initData() {
        mVibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        super.initData();
        lvCheckScan.addHeaderView(View.inflate(mContext,R.layout.item_good_select_header,null));
        lvCheckScan.setAdapter(new CheckScanAdapter(mContext));
        views = new ArrayList<>();
        views.add(tv_OrderCode);
        views.add(etCheckPosition);
        views.add(etGoodCode);
    }



    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        initScan();
        tv_OrderCode.setText("");
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

    private void initScan() {
        // TODO Auto-generated method stub
        mScanManager = new ScanManager();
        mScanManager.openScanner();

        mScanManager.switchOutputMode( 0);
        soundpool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100); // MODE_RINGTONE
        soundid = soundpool.load("/etc/Scan_new.ogg", 1);
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if(mScanManager != null) {
            mScanManager.stopDecode();
            isScaning = false;
        }
        mContext.unregisterReceiver(mScanReceiver);
    }

    private void initListener() {
        btnCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_commit){
            mContext.initTitle("盘点任务");
            mContext.goToFragment(13);
        }
    }

    private class CheckScanAdapter extends BaseAdapter {
        private final FuncDetailActivity context;
        private ArrayList<CheckScanBean> dataList;

        public CheckScanAdapter(FuncDetailActivity context) {
            this.context=context;
            initListData();
        }

        private void initListData() {
            dataList=new ArrayList<CheckScanBean>();
            for (int i = 0; i < 10; i++) {
                CheckScanBean checkScanBean = new CheckScanBean();
                checkScanBean.setCode("961234567");
                checkScanBean.setName("荣耀8");
                checkScanBean.setPosition("pc_01010101010");
                checkScanBean.setNumber(0);
                dataList.add(checkScanBean);
            }
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public CheckScanBean getItem(int position) {
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
                convertView=View.inflate(mContext,R.layout.item_check_scan,null);
                holder=new ViewHolder();
                holder.tvName = (TextView)convertView.findViewById( R.id.tv_name );
                holder.tvPosition = (TextView)convertView.findViewById( R.id.tv_position );
                holder.tvNumber = (EditText)convertView.findViewById( R.id.tv_number );
                holder.tvCode = (TextView)convertView.findViewById( R.id.tv_code );
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            CheckScanBean bean = dataList.get(position);
            holder.tvName.setText("名称："+bean.getName());
            holder.tvPosition.setText("货位："+bean.getPosition());
            holder.tvNumber.setText("扫描数量："+bean.getNumber());
            holder.tvCode.setText("商品条码："+bean.getCode());
            return convertView;
        }

    }



    static class ViewHolder{
         TextView tvName;
         TextView tvPosition;
         EditText tvNumber;
         TextView tvCode;
    }


}
