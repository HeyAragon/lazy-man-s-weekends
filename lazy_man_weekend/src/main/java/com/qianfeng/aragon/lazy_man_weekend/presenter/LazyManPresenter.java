package com.qianfeng.aragon.lazy_man_weekend.presenter;

import com.qianfeng.aragon.lazy_man_weekend.bean.LazyManBean;
import com.qianfeng.aragon.lazy_man_weekend.model.ILazyMan;
import com.qianfeng.aragon.lazy_man_weekend.model.LazyMan;
import com.qianfeng.aragon.lazy_man_weekend.view.ILazyManView;

/**
 * Created by aragon on 2016/10/25.
 */
public class LazyManPresenter implements IRequestCallback ,ILazyManPresenter{

    private ILazyMan lazyMan = new LazyMan();
    private ILazyManView lazyManView ;

    public LazyManPresenter(ILazyManView lazyManView) {
        this.lazyManView = lazyManView;
    }

    @Override
    public void callback(LazyManBean lazyManBean) {
        if (lazyManBean == null) {
            return;
        }
        this.lazyManView.refreshStarView(lazyManBean);
    }

    @Override
    public void callback2(LazyManBean lazyManBean) {

    }

    @Override
    public void transportStarData() {
        lazyMan.loadStarData(this);
    }

}
