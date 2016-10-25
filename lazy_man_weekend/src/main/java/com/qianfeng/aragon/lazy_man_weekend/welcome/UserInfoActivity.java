package com.qianfeng.aragon.lazy_man_weekend.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.qianfeng.aragon.lazy_man_weekend.R;

public class UserInfoActivity extends AppCompatActivity {

    private static final String SHAREDPREFERENCES_NAME = "first_pref";
    private ImageButton leftBt;
    private ImageButton rightBt;
    private RadioGroup userStateRg;
    private RadioGroup userSexRg;
    private int checkedSexId;
    private int checkedStateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
        setListener();
    }

    private void initView() {
        leftBt = (ImageButton) findViewById(R.id.user_info_left_bt);
        rightBt = (ImageButton) findViewById(R.id.user_info_right_bt);
        userStateRg = (RadioGroup) findViewById(R.id.user_info_state_rg);
        userSexRg = (RadioGroup) findViewById(R.id.user_info_sex_rg);

    }

    private void setListener() {
        leftBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rightBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRadioGroupChecked()) {
                    setGuide();
                    goUserInteresting();
                } else {
                    if (checkedStateId == -1) {
                        Toast.makeText(UserInfoActivity.this,"为能给您推荐更合适的产品，请选择个人状态哦~",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (checkedSexId == -1) {
                        Toast.makeText(UserInfoActivity.this,"为能给您推荐更适合的产品，请选择性别哦~",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private void setGuide() {
        SharedPreferences preferences = getSharedPreferences(SHAREDPREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //存入数据
        editor.putBoolean("isFirstIn", true);
        //提交修改
        editor.commit();
    }

    private void goUserInteresting() {
        // 跳转
        Intent intent = new Intent(UserInfoActivity.this, ChooseInterestingActivity.class);
        UserInfoActivity.this.startActivity(intent);

    }

    /**
     * 判断xml文件中 性别和当前状态 两个radioGroup是否已选择
     * @return
     */
    private boolean isRadioGroupChecked() {
        checkedSexId = userSexRg.getCheckedRadioButtonId();
        checkedStateId = userStateRg.getCheckedRadioButtonId();
        //若radioButton没被选中，则 getCheckedRadioButtonId() 返回 -1
        if (checkedSexId != -1 && checkedStateId != -1) {
            return true;
        } else {
            return false;
        }
    }

}
