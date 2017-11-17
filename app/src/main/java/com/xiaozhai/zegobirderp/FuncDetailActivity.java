package com.xiaozhai.zegobirderp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.device.ScanManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaozhai.zegobirderp.base.BaseFragment;
import com.xiaozhai.zegobirderp.fragment.CheckScanFragment;
import com.xiaozhai.zegobirderp.fragment.CheckTackFragment;
import com.xiaozhai.zegobirderp.fragment.CreatShelfTaskFragment;
import com.xiaozhai.zegobirderp.fragment.EntryTaskFragment;
import com.xiaozhai.zegobirderp.fragment.GoodEntryFragment;
import com.xiaozhai.zegobirderp.fragment.GoodInputFragment;
import com.xiaozhai.zegobirderp.fragment.GoodQueryFragment;
import com.xiaozhai.zegobirderp.fragment.GoodSelectFragment;
import com.xiaozhai.zegobirderp.fragment.GoodTransScanFragment;
import com.xiaozhai.zegobirderp.fragment.GoodWeightFragment;
import com.xiaozhai.zegobirderp.fragment.NoOrderEntryFragment;
import com.xiaozhai.zegobirderp.fragment.NoOrderInputFragment;
import com.xiaozhai.zegobirderp.fragment.NoOrderScanOutFragment;
import com.xiaozhai.zegobirderp.fragment.PackCheckFragment;

import java.util.HashMap;
import java.util.WeakHashMap;

public class FuncDetailActivity extends FragmentActivity implements View.OnClickListener {

    public ImageView ivBack;
    public TextView tvFuncTitle;
    public Button btnCommit;
    public WeakHashMap<String,Object> dataMap;
    private FrameLayout flContent;
    private final String tag=FuncDetailActivity.class.getSimpleName();
    public boolean goodSelectUpdate=false;

//    public ScanManager mScanManager =null;
//    private SoundPool soundpool = null;
//    public int soundid;
//    public   boolean isScaning = false;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-10-31 11:06:57 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        ivBack = (ImageView)findViewById( R.id.iv_back );
        tvFuncTitle = (TextView)findViewById( R.id.tv_func_title );
        btnCommit = (Button)findViewById( R.id.btn_commit );
         flContent= (FrameLayout) findViewById(R.id.fl_content);

        btnCommit.setOnClickListener( this );
        ivBack.setOnClickListener(this);

        dataMap=new WeakHashMap<>();
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-10-31 11:06:57 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        Log.d(tag,"点击了");
        if ( v == btnCommit ) {
            // Handle clicks for btnCommit
        }else if(v==ivBack){
            finish();
        }
    }

    /**
     * 打开扫描
     */
   /* public void openScan(){

        mScanManager.stopDecode();
        isScaning = true;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mScanManager.startDecode();
    }*/

    private HashMap<Integer, BaseFragment> fragmentHashMap;
    public final static String SCAN_ACTION = ScanManager.ACTION_DECODE;//default action
    public IntentFilter filter;

   /* @Override
    protected void onResume() {
         filter = new IntentFilter();
        int[] idbuf = new int[]{PropertyID.WEDGE_INTENT_ACTION_NAME, PropertyID.WEDGE_INTENT_DATA_STRING_TAG};
        String[] value_buf = mScanManager.getParameterString(idbuf);
        if(value_buf != null && value_buf[0] != null && !value_buf[0].equals("")) {
            filter.addAction(value_buf[0]);
        } else {
            filter.addAction(SCAN_ACTION);
        }
    }*/

    private  InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_func_detail);
        findViews();

         imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        fragmentHashMap = new HashMap<>();
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        goToFragment(position);
//        initScan();
    }

    public void hideSoftInputFromWindow(View view) {
        boolean isOpen=imm.isActive();
        if(isOpen)
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }

    public void showSoftInput(View view) {
        boolean isOpen=imm.isActive();
        if(!isOpen) {
            imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);
        }
    }


    /*private void initScan() {
        // TODO Auto-generated method stub
        mScanManager = new ScanManager();
        mScanManager.openScanner();

        mScanManager.switchOutputMode( 0);
        soundpool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100); // MODE_RINGTONE
        soundid = soundpool.load("/etc/Scan_new.ogg", 1);
    }*/

    public void goToFragment(int position){

        BaseFragment fragment = null;

        fragment = fragmentHashMap.get(position);
        if(fragment==null){
            switch (position) {
                case 0 :
                    fragment=new GoodInputFragment();
                    break;
                case 1 :
                    fragment=new NoOrderInputFragment();
                    break;
                case 2 :
                    fragment=new EntryTaskFragment();
                    break;
                case 3 :
                    fragment=new GoodEntryFragment();
                    break;
                case 4 :
                    fragment=new NoOrderEntryFragment();
                    break;
                case 5 :
                    fragment=new GoodQueryFragment();
                    break;
                case 6 :
                    fragment=new GoodTransScanFragment();
                    break;
                case 7 :
                    fragment=new CheckTackFragment();
                    break;
                case 8 :
                    fragment=new CheckScanFragment();
                    break;
                case 9 :
                    fragment=new NoOrderScanOutFragment();
                    break;
                case 10 :
                    fragment=new PackCheckFragment();
                    break;
                case 11 :
                    fragment=new GoodWeightFragment();
                    break;
                case 12 :
                    fragment=new CreatShelfTaskFragment();
                    break;
                case 13 :
                    fragment=new GoodSelectFragment();
                    break;
                default:
                    return;
            }
            fragmentHashMap.put(position,fragment);
        }

        switchFragment(tempFragemnt,fragment);
    }


    public BaseFragment tempFragemnt;

    public void switchFragment(BaseFragment tempFragemnt, BaseFragment toFragment) {
        if(tempFragemnt!=toFragment){
            if(toFragment!=null){
                this.tempFragemnt=toFragment;
                FragmentTransaction fr = getSupportFragmentManager()
                        .beginTransaction();
                if(toFragment.isAdded()){
                    if(tempFragemnt!=null){
                        fr.hide(tempFragemnt);
                    }
                    fr.show(toFragment).commit();
                }else {
                    if(tempFragemnt!=null){
                        fr.hide(tempFragemnt);
                    }
                    fr.add(R.id.fl_content,toFragment).commit();
                }
            }
        }
    }

//    private void switchFragement( BaseFragment fragment) {
//        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
//       tr.replace(R.id.fl_content, fragment,fragment.getClass().getSimpleName()).commitAllowingStateLoss();
//    }

    public void initTitle(String titleName){
        tvFuncTitle.setText(titleName);
    }

}
