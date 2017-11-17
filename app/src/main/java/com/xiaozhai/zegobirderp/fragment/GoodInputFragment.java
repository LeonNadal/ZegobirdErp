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
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaozhai.zegobirderp.R;
import com.xiaozhai.zegobirderp.base.BaseFragment;
import com.xiaozhai.zegobirderp.bean.GoodSelectBean;
import com.xiaozhai.zegobirderp.bean.NormalInputBean;
import com.xiaozhai.zegobirderp.bean.PurchaseEnter;
import com.xiaozhai.zegobirderp.common.SpContants;
import com.xiaozhai.zegobirderp.common.UrlConstants;
import com.xiaozhai.zegobirderp.utils.HttpUtils;
import com.xiaozhai.zegobirderp.utils.ProcessJsonData;
import com.xiaozhai.zegobirderp.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import static com.xiaozhai.zegobirderp.FuncDetailActivity.SCAN_ACTION;
import static com.xiaozhai.zegobirderp.common.ScanConstant.SCANNER_APP_ENABLE;

/**
 * Prooject name：常规入库主界面
 * Created by KAKA、Nadal on ${DATE}.
 */

public class GoodInputFragment extends BaseFragment implements View.OnClickListener, View
        .OnKeyListener {

    private  final int DATA_EMPTY = 5;
    private  final int RESULT_ERR_TIP = 4;
    private  final int RESULT_NO_ORDER =1 ;
    private  final int RESULT =2 ;
    private ListView lvGoodInput;
    private final int WHAT_COMPLESH=0;
    private final int GO_TO_SELECT=3;
    private final int FINISH=6;
    private GoodInputAdapter goodInputAdapter;
    private boolean isPressAdded=false;

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
                    if(normalInputBeanResult!=null){
                         goodInputAdapter = new GoodInputAdapter
                                ( normalInputBeanResult.getData().getProductInfo());
                        lvGoodInput.setAdapter(goodInputAdapter);
                        etPosition.requestFocus();
                    }
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
                    etOrderCode.setText("");
                    etOrderCode.setText(barcodeStr);
                    etOrderCode.setSelection(etOrderCode.getText().length());
                    break;
                case  1:
                    etPosition.setText("");
                    etPosition.setText(barcodeStr);
                    etPosition.setSelection(etPosition.getText().length());
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

    private  ArrayList<View> views;

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

        if(views.size()!=currentIndex){
            View view = views.get(currentIndex);
            view.requestFocus();
        }
    }

    private static final String tag=GoodInputFragment.class.getSimpleName();


    private Button btnAdd;
    private EditText etOrderCode;
    private EditText etPosition;
    @Override
    protected View initView() {
        initTile("常规入库");
        isShowCommit(true);
        View view = View.inflate(mContext, R.layout.fragment_good_input, null);
        lvGoodInput = (ListView)view.findViewById( R.id.lv_good_input );
        etOrderCode = (EditText) view.findViewById(R.id.et_order_code);
        etPosition = (EditText) view.findViewById(R.id.et_position);
        btnAdd= (Button) view.findViewById(R.id.btn_add);
        initListener();
//        mContext.openScan();
        return view;
    }

    @Override
    protected void initData() {
        mVibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        super.initData();
        lvGoodInput.addHeaderView(View.inflate(mContext,R.layout.item_normal_input_header,null));
        views = new ArrayList<>();
        views.add(etOrderCode);
        views.add(etPosition);
    }




    private void initListener() {
        btnCommit.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        etOrderCode.setOnKeyListener(this);
        etPosition.setOnKeyListener(this);
    }

    /*private TextWatcher textWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d("onTextChanged","PO171111001"+"s:"+s+"start:"+start+"before:"+before+"count:"+count);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };*/

    private void queryGood(String orderCode,HttpUtils.OnComplish onComplish){
        HttpUtils.post(mContext, UrlConstants.GOOD_SELECT, "{\n" +
                "    \"OrderCode\": \""+orderCode+"\",\n" +
                "    \"PageSize\": 100,\n" +
                "    \"PageIndex\": 1\n" +
                "}", onComplish);
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
        System.out.println("调用");
        mContext.hideSoftInputFromWindow(mContext.getCurrentFocus());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
       //true 表隐藏 false 表显示
        if(!hidden){
            if(mContext.goodSelectUpdate ){
                List<GoodSelectBean.DataBean> selectBean= (List<GoodSelectBean.DataBean>) mContext.dataMap.get("checkData");

                List<NormalInputBean.DataBean.ProductInfoBean> addBean =
                        selectBeanToNormalBean(selectBean);
                if(goodInputAdapter!=null){
                    goodInputAdapter.addData(addBean);
                }
                mContext.goodSelectUpdate=false;
            }
            mContext.ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.finish();
                }
            });
            mContext.btnCommit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isPressAdded){
                        Toast.makeText(mContext, "点击提交了", Toast.LENGTH_SHORT).show();
                        String code = etOrderCode.getText().toString();
                        String position = etPosition.getText().toString();
                        if(TextUtils.isEmpty(code) || TextUtils.isEmpty(position)){
                            Toast.makeText(mContext, "单号或货位号不能为空", Toast.LENGTH_SHORT).show();
                        }else if(goodInputAdapter.isHasData()){
                            Map<String,Object> jsonMap=new WeakHashMap<String, Object>();
                            jsonMap.put("OrderCode",code);
                            jsonMap.put(SpContants.USER_ID,SpUtils.getString(mContext,SpContants.USER_ID,"0"));
                            jsonMap.put(SpContants.USERTRUENAME,SpUtils.getString(mContext,SpContants.USERTRUENAME,"0"));
                            jsonMap.put("TotalQuantity",goodInputAdapter.getTotalQuantity());
                            ArrayList<PurchaseEnter> PurchaseEnterList = toPurchaseEnterList(goodInputAdapter.getData(),position);
                            jsonMap.put("PurchaseEnterList",PurchaseEnterList);

                            HttpUtils.post(mContext, UrlConstants.ADDPURCHASEENTERINFO, jsonMap, new HttpUtils.OnComplish() {

                                @Override
                                public void onFailure(String errMsg) {
                                    Message msg = Message.obtain();
                                    msg.what=RESULT_ERR_TIP;
                                    Bundle bundle = new Bundle();
                                    bundle.putString("errMsg",errMsg);
                                    msg.setData(bundle);
                                    handler.sendMessage(msg);
                                }

                                @Override
                                public void onResponse(String resultStr) {
                                    if(resultStr.contains("0000")){
                                        handler.sendEmptyMessage(FINISH);
                                    }else{
                                        Message msg = Message.obtain();
                                        msg.what=RESULT_ERR_TIP;
                                        Bundle bundle = new Bundle();
                                        bundle.putString("errMsg","未知错误");
                                        msg.setData(bundle);
                                        handler.sendMessage(msg);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(mContext, "没有该单号", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(mContext, "单号没有确定，请确定", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
    }

    private ArrayList<PurchaseEnter> toPurchaseEnterList(List<NormalInputBean.DataBean
            .ProductInfoBean> data,String positionName){
        ArrayList<PurchaseEnter> PurchaseEnterList=new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            NormalInputBean.DataBean.ProductInfoBean productInfoBean = data.get(i);
            /**
             * (int orderList_Id, String product_Id, String productCode, String
             productName, String productModel, int positionName, String smallUnit, int quantity)
             */
            PurchaseEnter purchaseEnter = new PurchaseEnter(productInfoBean.getOrderList_Id(),
                    productInfoBean.getProduct_Id() + "",
                    productInfoBean.getProductCode(),
                    productInfoBean.getProductName(), productInfoBean.getProductModel(), positionName,
                    productInfoBean.getSmallUnit(), productInfoBean.getQuantity());
            PurchaseEnterList.add(purchaseEnter);
        }
        return  PurchaseEnterList;
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

    private List<NormalInputBean.DataBean.ProductInfoBean> selectBeanToNormalBean(List<GoodSelectBean.DataBean> resource){
        List<NormalInputBean.DataBean.ProductInfoBean> addData=new ArrayList<>();
        for (int i = 0; i < resource.size(); i++) {
            GoodSelectBean.DataBean dataBean = resource.get(i);
            /**
             * ProductInfoBean(int orderList_Id, int order_Id, long product_Id, String
             productCode, String productName, String productModel, int quantity, int
             enterQuantity, Object produceDate, String smallUnit, Object remark)
             */
            NormalInputBean.DataBean.ProductInfoBean bean=new NormalInputBean.DataBean
                    .ProductInfoBean(dataBean.getOrderList_Id(),dataBean.getOrder_Id(),dataBean.getProduct_Id(),dataBean.getProductCode(),
                    dataBean.getProductName(),dataBean.getProductModel(),
                    dataBean.getQuantity(),dataBean.getEnterQuantity(),
                    dataBean.getProduceDate(),dataBean.getSmallUnit(),dataBean.getRemark());
            addData.add(bean);
        }
        return  addData;
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
    public void onDestroy() {
         super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_commit){
            if(isPressAdded){
                String code = etOrderCode.getText().toString();
                String position = etPosition.getText().toString();
                if(TextUtils.isEmpty(code) || TextUtils.isEmpty(position)){
                    Toast.makeText(mContext, "单号或货位号不能为空", Toast.LENGTH_SHORT).show();
                }else if(goodInputAdapter.isHasData()){
                    Map<String,Object> jsonMap=new WeakHashMap<String, Object>();
                    jsonMap.put("OrderCode",code);
                    jsonMap.put(SpContants.USER_ID,SpUtils.getString(mContext,SpContants.USER_ID,"0"));
                    jsonMap.put(SpContants.USERTRUENAME,SpUtils.getString(mContext,SpContants.USERTRUENAME,"0"));
                    jsonMap.put("TotalQuantity",goodInputAdapter.getTotalQuantity());
                    ArrayList<PurchaseEnter> PurchaseEnterList = toPurchaseEnterList(goodInputAdapter.getData(),position);
                    jsonMap.put("PurchaseEnterList",PurchaseEnterList);

                    HttpUtils.post(mContext, UrlConstants.ADDPURCHASEENTERINFO, jsonMap, new HttpUtils.OnComplish() {

                        @Override
                        public void onFailure(String errMsg) {
                            Message msg = Message.obtain();
                            Log.d("errMsg:",errMsg);
                            msg.what=RESULT_ERR_TIP;
                            Bundle bundle = new Bundle();
                            bundle.putString("errMsg","未知错误");
                            msg.setData(bundle);
                            handler.sendMessage(msg);
                        }

                        @Override
                        public void onResponse(String resultStr) {
                            if(resultStr.contains("0000")){
                                handler.sendEmptyMessage(FINISH);
                            }else{
                                Message msg = Message.obtain();
                                Log.d("errMsg:",resultStr);
                                msg.what=RESULT_ERR_TIP;
                                Bundle bundle = new Bundle();
                                bundle.putString("errMsg","未知错误");
                                msg.setData(bundle);
                                handler.sendMessage(msg);
                            }
                        }
                    });
                }else{
                    Toast.makeText(mContext, "没有该单号", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(mContext, "单号没有确定，请确定", Toast.LENGTH_SHORT).show();
            }
        }else if( v.getId()==R.id.btn_add){
            if(isPressAdded){
                if(mContext.dataMap.containsKey("checkData")
                        && ((List<GoodSelectBean.DataBean>)mContext.dataMap.get("checkData")).size()>0){
                    mContext.goToFragment(13);
                }else{
                    queryGood(etOrderCode.getText().toString(),new HttpUtils.OnComplish() {
                        @Override
                        public void onFailure(String errMsg) {
                            System.out.println("errMsg:"+errMsg);
                        }

                        @Override
                        public void onResponse(String resultStr) {
                            System.out.println("result:"+resultStr);
                            if(resultStr.contains("0000")){
                                mContext.dataMap.put("goodJson",resultStr);
                                handler.sendEmptyMessage(GO_TO_SELECT);
                                isPressAdded=true;
                            }else{
                                handler.sendEmptyMessage(RESULT_NO_ORDER);
                            }
                        }
                    });
                }
            }else{
                Toast.makeText(mContext, "单号没有确定，请确定", Toast.LENGTH_SHORT).show();
            }



        }
    }

    private  NormalInputBean normalInputBeanResult;

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
            //处理事件
            boolean isFirst=false;
            switch (v.getId()) {
                case  R.id.et_order_code:
                    String content = etOrderCode.getText().toString();
                    if(!TextUtils.isEmpty(content)){
                        //请求数据
                        //// TODO: 2017/11/13
                        HttpUtils.post(mContext,UrlConstants.NORMAL_INPUT,"{\n" +
                                "    \"OrderCode\": \""+content+"\"\n" +
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
                                         normalInputBeanResult = ProcessJsonData.processData(resultStr, NormalInputBean.class);
                                        if(normalInputBeanResult.getResultCode().equals("0000")){
                                            handler.sendEmptyMessage(RESULT);
                                            isPressAdded=true;

                                        }else{
                                            Message msg = Message.obtain();
                                            msg.what=RESULT_ERR_TIP;
                                            Bundle bundle = new Bundle();
                                            bundle.putString("errMsg",normalInputBeanResult.getMessage());
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
                    break;
                case  R.id.et_position:
                    mContext.hideSoftInputFromWindow(etPosition);
                    break;
            }

            return true;
        }
        return false;
    }

    private class GoodInputAdapter extends BaseAdapter {


        private final List<NormalInputBean.DataBean.ProductInfoBean> data;

        public GoodInputAdapter(List<NormalInputBean.DataBean.ProductInfoBean> data) {
            this.data=data;
        }

        public List<NormalInputBean.DataBean.ProductInfoBean> getData() {
            return data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public NormalInputBean.DataBean.ProductInfoBean  getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        private boolean isHasData(){
            return data.size()>0?true:false;
        }

        private void addData(List<NormalInputBean.DataBean.ProductInfoBean> addData){
            if(addData!=null && addData.size()>0){
                data.addAll(addData);
                notifyDataSetChanged();
            }
        }

        private int getTotalQuantity(){
            int totalQuantity=0;
            for (int i = 0; i < data.size(); i++) {
                totalQuantity+=data.get(i).getQuantity();
            }
            return totalQuantity;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView==null){
                convertView=View.inflate(mContext,R.layout.item_normal_input,null);
                holder=new ViewHolder();
                holder.tvGoodNumber = (TextView)convertView.findViewById( R.id.tv_good_number );
                holder.tvGoodCode = (TextView)convertView.findViewById( R.id.tv_good_code );
                holder.tvGoodName = (TextView)convertView.findViewById( R.id.tv_good_name );
                holder.etInputNumber = (TextView)convertView.findViewById( R.id.et_input_number );
                holder.etInputNumber.setTag(position);
                //没有调用
                holder.etInputNumber.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN ){
                            mContext.hideSoftInputFromWindow(v);
                            System.out.println("执行了：OnKeyListener");
                            return true;
                        }
                        return false;
                    }
                });
                //回车不换行
                holder.etInputNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        System.out.println("执行了：OnEditorActionListener");
                        return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
                    }
                });
                holder.etInputNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        System.out.println("hasfocus:"+hasFocus);
                        if(!hasFocus){
                            EditText et= (EditText) v;
                            int tag= (int) et.getTag();
                            String content = et.getText().toString();
                            data.get(tag).setQuantity(Integer.parseInt(content.replace("\n", "")));
                            System.out.println("debug:"+content);
                        }

                    }
                });
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
                NormalInputBean.DataBean.ProductInfoBean bean=data.get(position);
                holder.tvGoodNumber.setText(bean.getProductCode()+"");
                holder.tvGoodCode.setText(bean.getProductModel()+"");
                holder.tvGoodName.setText(bean.getProductName()+"");
                holder.etInputNumber.setText(bean.getQuantity()+"");
                holder.etInputNumber.setTag(position);
            System.out.println("size:"+getCount());
            return convertView;
        }

    }



    static class ViewHolder{
         TextView tvGoodNumber;
         TextView tvGoodCode;
         TextView tvGoodName;
         TextView etInputNumber;
    }


}
