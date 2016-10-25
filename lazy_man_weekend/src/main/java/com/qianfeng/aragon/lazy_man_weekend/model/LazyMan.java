package com.qianfeng.aragon.lazy_man_weekend.model;

import android.util.Log;

import com.google.gson.Gson;
import com.qianfeng.aragon.lazy_man_weekend.bean.LazyManBean;
import com.qianfeng.aragon.lazy_man_weekend.http.HttpUrl;
import com.qianfeng.aragon.lazy_man_weekend.http.HttpUtils;
import com.qianfeng.aragon.lazy_man_weekend.presenter.IRequestCallback;

/**
 * Created by aragon on 2016/10/25.
 */
public class LazyMan implements ILazyMan{

    @Override
    public void loadStarData(final IRequestCallback callback) {
        HttpUtils.request(HttpUrl.STAR_PATH, false, null, new HttpUtils.ICallBack() {
            public static final String TAG = "androidhy";

            @Override
            public void success(String result) {
                Gson gson = new Gson();
                LazyManBean lazyManBean = gson.fromJson(result, LazyManBean.class);
                Log.i(TAG, "success: "+lazyManBean.getResult().size());
                callback.callback(lazyManBean);
            }
        });
    }
}
