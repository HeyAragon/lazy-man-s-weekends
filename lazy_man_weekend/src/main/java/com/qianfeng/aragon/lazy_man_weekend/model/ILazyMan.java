package com.qianfeng.aragon.lazy_man_weekend.model;

import com.qianfeng.aragon.lazy_man_weekend.presenter.IRequestCallback;

/**
 * Created by aragon on 2016/10/25.
 */
public interface ILazyMan {
    void loadStarData(IRequestCallback starCallback,String path);

    void loadSearchData(IRequestCallback searchCallback);

    void loadCityListData(IRequestCallback cityListCallback);

}
