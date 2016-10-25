package com.qianfeng.aragon.lazy_man_weekend.presenter;

import com.qianfeng.aragon.lazy_man_weekend.bean.LazyManBean;

/**
 * Created by aragon on 2016/10/25.
 */
public interface IRequestCallback {
    void callback(LazyManBean lazyManBean);

    void callback2(LazyManBean lazyManBean);
}
