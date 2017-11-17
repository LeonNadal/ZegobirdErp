package com.xiaozhai.zegobirderp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.xiaozhai.zegobirderp.adapter.MainGridAdapter;
import com.xiaozhai.zegobirderp.utils.HttpUtils;

public class MainActivity extends Activity {

    private GridView gvMain;
    private TextView tvLogout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
        test();
    }

    private void test() {
        HttpUtils.login(MainActivity.this);
    }

    private void initData() {
        MainGridAdapter mainGridAdapter = new MainGridAdapter(this);
        gvMain.setAdapter(mainGridAdapter);
    }

    private void initViews() {
        gvMain= (GridView) findViewById(R.id.gv_main);
         tvLogout = (TextView) findViewById(R.id.tv_logout);
        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, FuncDetailActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }
}
