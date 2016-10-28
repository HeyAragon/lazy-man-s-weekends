package com.qianfeng.aragon.lazy_man_weekend.welcome;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qianfeng.aragon.lazy_man_weekend.R;
import com.qianfeng.aragon.lazy_man_weekend.fragment.CatFragment;
import com.qianfeng.aragon.lazy_man_weekend.fragment.SearchFragment;
import com.qianfeng.aragon.lazy_man_weekend.fragment.StarFragment;
import com.qianfeng.aragon.lazy_man_weekend.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private MyPagerAdapter myPagerAdapter;
    private RadioButton mStarRadioBtn;
    private RadioButton mSearchRadioBtn;
    private RadioButton mCatRadioBtn;
    private RadioButton mUserRadioBtn;
    private RadioGroup mRadiogroup;
    private StarFragment starFragment;
    private SearchFragment searchFragment;
    private CatFragment catFragment;
    private UserFragment userFragment;
    private FragmentManager supportFragmentManager;
    private String path = "http://api.lanrenzhoumo.com/main/recommend/index?v=3&session_id=000040a3fb7d64ce1737c6c7bb3c7e4e157c91&lon=114.30963859310197&page=1&lat=30.575388756810078";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.main_view_pager);
        mStarRadioBtn = (RadioButton) findViewById(R.id.main_star_rb);
        mSearchRadioBtn = (RadioButton) findViewById(R.id.main_search_rb);
        mCatRadioBtn = (RadioButton) findViewById(R.id.main_cat_rb);
        mUserRadioBtn = (RadioButton) findViewById(R.id.main_user_rb);
        mRadiogroup = (RadioGroup) findViewById(R.id.main_rg);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myPagerAdapter);
        supportFragmentManager = MainActivity.this.getSupportFragmentManager();

    }

    private void loadFragment() {
        starFragment = new StarFragment(path);
        searchFragment = new SearchFragment();
        catFragment = new CatFragment();
        userFragment = new UserFragment();
        fragments.add(starFragment);
        fragments.add(searchFragment);
        fragments.add(catFragment);
        fragments.add(userFragment);
    }


    /**
     * 此适配器不会销毁item
     */
    class MyPagerAdapter2 extends FragmentStatePagerAdapter{

        public MyPagerAdapter2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }


    class MyPagerAdapter extends FragmentPagerAdapter {

        private static final String TAG = "androidhy";

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            loadFragment();
        }

        @Override
        public Fragment getItem(int position) {

            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }
    }

    private void setListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == 3) {
                    position = 4;
                }
                RadioButton radioButton = (RadioButton) mRadiogroup.getChildAt(position);
                radioButton.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_star_rb:
                        mViewPager.setCurrentItem(0,true);
                        break;
                    case R.id.main_search_rb:
                        mViewPager.setCurrentItem(1,true);
                        break;
                    case R.id.main_cat_rb:
                        mViewPager.setCurrentItem(2,true);
                        break;
                    case R.id.main_user_rb:
                        mViewPager.setCurrentItem(3,true);
                        break;
                }
            }
        });
    }
//
//    private void checkedFragment(Fragment fragment){
//        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//        if(mCurrentShowFragment !=null){
//            fragmentTransaction.hide(mCurrentShowFragment);
//        }
//        if(!fragment.isAdded()){
//            fragmentTransaction.add(R.id.main_content_layout,fragment);
//        }else{
//            fragmentTransaction.show(fragment);
//        }
//        fragmentTransaction.commit();
//        mCurrentShowFragment=fragment;
//    }
}
