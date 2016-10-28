package com.qianfeng.aragon.lazy_man_weekend.model;

import android.util.Log;

import com.google.gson.Gson;
import com.qianfeng.aragon.lazy_man_weekend.CityActivity;
import com.qianfeng.aragon.lazy_man_weekend.bean.CityBean;
import com.qianfeng.aragon.lazy_man_weekend.bean.LazyManBean;
import com.qianfeng.aragon.lazy_man_weekend.bean.SearchBean;
import com.qianfeng.aragon.lazy_man_weekend.http.HttpUrl;
import com.qianfeng.aragon.lazy_man_weekend.http.HttpUtils;
import com.qianfeng.aragon.lazy_man_weekend.presenter.IRequestCallback;

/**
 * Created by aragon on 2016/10/25.
 */
public class LazyMan implements ILazyMan{

    private static final String TAG = "androidxx";
    public String path = "http://api.lanrenzhoumo.com/main/recommend/index?v=3&session_id=000040a3fb7d64ce1737c6c7bb3c7e4e157c91&lon=114.30963859310197&page=1&lat=30.575388756810078";

    @Override
    public void loadStarData(final IRequestCallback starCallback,String path) {
        HttpUtils.request(path, false, null, new HttpUtils.ICallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                LazyManBean lazyManBean = gson.fromJson(result, LazyManBean.class);
//                Log.i(TAG, "star-success: "+lazyManBean.getResult().size()+path);
                starCallback.starCallback(lazyManBean);
//                path = HttpUrl.STAR_PATH;
            }
        });
    }

    @Override
    public void loadSearchData(final IRequestCallback searchCallback) {
        HttpUtils.request(HttpUrl.SEARCH_PATH, false, null, new HttpUtils.ICallBack() {
            public static final String TAG = "androidhy";

            @Override
            public void success(String result) {
                Gson gson = new Gson();
                SearchBean searchBean = gson.fromJson(result, SearchBean.class);
                Log.i(TAG, "search-success: "+searchBean.getResult().size());
                searchCallback.searchCallback(searchBean);
            }
        });
    }

    @Override
    public void loadCityListData(final IRequestCallback cityListCallback) {
        HttpUtils.request(HttpUrl.CITY_PATH, false, null, new HttpUtils.ICallBack() {
            public static final String TAG = "androidhy";

            @Override
            public void success(String result) {
                Gson gson = new Gson();
                CityBean cityBean = gson.fromJson(result, CityBean.class);
                Log.i(TAG, "city-success: "+cityBean.getResult().size());
                cityListCallback.cityListCallback(cityBean);
            }
        });
    }

//    @Override
//    public void sendCityID(String cityId) {
//        Log.i(TAG, "sendCityID: "+cityId);
//        path = "http://api.lanrenzhoumo.com/wh/common/leos?v=2&session_id=000040a3fb7d64ce1737c6c7bb3c7e4e157c91&lon=114.30963859310197&page=1&category=all&lat=30.575388756810078&city_id="+cityId;
//    }
}
