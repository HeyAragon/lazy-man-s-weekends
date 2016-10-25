package com.qianfeng.aragon.lazy_man_weekend.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.aragon.lazy_man_weekend.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private MyAdapter mPagerAdapter;
    private List<Integer> images = new ArrayList<>();
    private static final String SHAREDPREFERENCES_NAME = "first_pref";
    private TextView title1;
    private TextView title2;
    private Button unLogin;
    private int currentItem = 0;
    private Handler mHandler = new Handler(){
        public static final String TAG = "androidhy";

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            int what  = msg.what;
//            Log.i(TAG, "handleMessage: "+what);
            currentItem =  currentItem < Integer.MAX_VALUE/2 ? (currentItem+1) : 0;
            mViewPager.setCurrentItem(currentItem);
            mHandler.sendEmptyMessageDelayed(0, 3000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initDatas();
        mHandler.sendEmptyMessageDelayed(0, 3000);

    }


    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.guide_view_pager);
        title1 = (TextView) findViewById(R.id.guide_title1);
        title2 = (TextView) findViewById(R.id.guide_title2);
        unLogin = (Button) findViewById(R.id.guide_unlogin);

        mPagerAdapter = new MyAdapter();
        mViewPager.setAdapter(mPagerAdapter);
        listener();
        mViewPager.setCurrentItem(currentItem);
    }

    private void listener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position%4 == 0) {
                    title1.setText("闭目");
                    title2.setText("难掩喜悦与期待");
                }
                if (position%4 == 1) {
                    title1.setText("睁眼");
                    title2.setText("因为你心随所动");
                }
                if (position%4 == 2) {
                    title1.setText("启程");
                    title2.setText("只因追寻你所爱");
                }
                if (position%4 == 3) {
                    title1.setText("我们");
                    title2.setText("做你最了解的人");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initDatas() {
        images.add(R.drawable.pic1);
        images.add(R.drawable.pic2);
        images.add(R.drawable.pic3);
        images.add(R.drawable.pic4);
        mPagerAdapter.notifyDataSetChanged();
    }


    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setImageResource(images.get(position%4));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(imageView);
            unLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setGuide();
                    goUserInfo();
                }
            });
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        private void setGuide() {
            SharedPreferences preferences = getSharedPreferences(SHAREDPREFERENCES_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            //存入数据
            editor.putBoolean("isFirstIn", true);
            //提交修改
            editor.commit();
        }

        private void goUserInfo() {
            // 跳转
            Intent intent = new Intent(GuideActivity.this, UserInfoActivity.class);
            GuideActivity.this.startActivity(intent);
        }
    }
}
