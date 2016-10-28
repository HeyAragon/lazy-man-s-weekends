package com.qianfeng.aragon.lazy_man_weekend.presenter;

import com.qianfeng.aragon.lazy_man_weekend.bean.CityBean;
import com.qianfeng.aragon.lazy_man_weekend.bean.LazyManBean;
import com.qianfeng.aragon.lazy_man_weekend.bean.SearchBean;

/**
 * Created by aragon on 2016/10/25.
 */
public interface IRequestCallback {
    void starCallback(LazyManBean lazyManBean);

    void searchCallback(SearchBean searchBean);

    void cityListCallback(CityBean cityBean);

}
