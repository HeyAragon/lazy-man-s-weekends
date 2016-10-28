package com.qianfeng.aragon.lazy_man_weekend;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.qianfeng.aragon.lazy_man_weekend.bean.LazyManBean;
import com.qianfeng.aragon.lazy_man_weekend.fragment.StarFragment;
import com.qianfeng.aragon.lazy_man_weekend.presenter.ILazyManPresenter;
import com.qianfeng.aragon.lazy_man_weekend.view.IStarFragmentView;

public class CityActivityDetails extends AppCompatActivity{

    private String cityId;
    private FragmentManager supportFragmentManager;
    private StarFragment starFragment;
    private String path = "http://api.lanrenzhoumo.com/wh/common/leos?v=2&session_id=000040a3fb7d64ce1737c6c7bb3c7e4e157c91&lon=114.30963859310197&page=1&category=all&lat=30.575388756810078&city_id=";
    private String cityName;
    private ImageButton mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_activity_details);
        Intent intent = getIntent();
        cityId = intent.getStringExtra("cityId");
        cityName = intent.getStringExtra("cityName");
        initView();
        useFragment();
        setListener();
    }

    private void initView() {
        supportFragmentManager = getSupportFragmentManager();
        mBackBtn = (ImageButton) findViewById(R.id.city_details_image_btn);
    }

    private void setListener() {
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void useFragment() {
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
//        starFragment = new StarFragment(path+cityId);
        starFragment = StarFragment.newInstance(cityName,path+cityId);
        transaction.replace(R.id.city_details_frame_layout, starFragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }

}
