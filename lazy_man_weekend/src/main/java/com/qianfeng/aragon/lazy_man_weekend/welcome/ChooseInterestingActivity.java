package com.qianfeng.aragon.lazy_man_weekend.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.qianfeng.aragon.lazy_man_weekend.R;

public class ChooseInterestingActivity extends AppCompatActivity {

    private static final String SHAREDPREFERENCES_NAME = "first_pref";
    private ImageButton rightBtb;
    private ImageButton leftBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_interesting);
        initView();
        setListener();
    }


    private void initView() {
        rightBtb = (ImageButton) findViewById(R.id.user_interesting_right_bt);
        leftBtn = (ImageButton)findViewById(R.id.user_interesting_left_bt);
    }



    private void setListener() {
        rightBtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGuide();
                goUserInteresting();
            }
        });

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void setGuide() {
        SharedPreferences preferences = getSharedPreferences(SHAREDPREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //存入数据
        editor.putBoolean("isFirstIn", false);
        //提交修改
        editor.commit();
    }

    private void goUserInteresting() {
        // 跳转
        Intent intent = new Intent(ChooseInterestingActivity.this, MainActivity.class);
        ChooseInterestingActivity.this.startActivity(intent);

    }
}
