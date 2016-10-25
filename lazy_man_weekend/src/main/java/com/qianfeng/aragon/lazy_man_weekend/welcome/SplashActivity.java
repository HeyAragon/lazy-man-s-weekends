package com.qianfeng.aragon.lazy_man_weekend.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qianfeng.aragon.lazy_man_weekend.R;

public class SplashActivity extends AppCompatActivity {

    boolean isFirstIn = false;

    private static final int GO_HOME = 1000;

    private static final int GO_GUIDE = 1001;

    private static final int GO_CHOOSE_PERTIONNAL_INFO = 1002;

    private static final int GO_CHOOSE_INTERESTINGS = 1003;

    private static final long SPLASH_DELAY_MILLIS = 3000;

    private static final String SHAREDPREFERENCE_NAME = "first_pref";

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }


    private void init() {
        //读取SharedPreferences中需要的数据
        //使用SharedPreferences来记录程序的使用次数
        SharedPreferences preferences = getSharedPreferences(SHAREDPREFERENCE_NAME, MODE_PRIVATE);
        //取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        isFirstIn = preferences.getBoolean("isFirstIn", true);

        //判断程序第几次运行，如果是第一次运行这跳转到MainActivity
        if (!isFirstIn) {
            mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
        }else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
        }

    }


    private void goHome() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
    }

    private void goGuide() {
        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
        SplashActivity.this.startActivity(intent);
    }


}
