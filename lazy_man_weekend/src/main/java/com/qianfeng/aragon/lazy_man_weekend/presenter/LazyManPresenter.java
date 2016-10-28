package com.qianfeng.aragon.lazy_man_weekend.presenter;

import com.qianfeng.aragon.lazy_man_weekend.bean.CityBean;
import com.qianfeng.aragon.lazy_man_weekend.bean.LazyManBean;
import com.qianfeng.aragon.lazy_man_weekend.bean.SearchBean;
import com.qianfeng.aragon.lazy_man_weekend.model.ILazyMan;
import com.qianfeng.aragon.lazy_man_weekend.model.LazyMan;
import com.qianfeng.aragon.lazy_man_weekend.view.ICityListView;
import com.qianfeng.aragon.lazy_man_weekend.view.ISearchFragmentView;
import com.qianfeng.aragon.lazy_man_weekend.view.IStarFragmentView;

/**
 * Created by aragon on 2016/10/25.
 */
public class LazyManPresenter implements IRequestCallback ,ILazyManPresenter{

    private ILazyMan lazyMan = new LazyMan();
    private IStarFragmentView starFragmentView ;
    private ISearchFragmentView searchFragmentView;
    private ICityListView cityListView;

    public LazyManPresenter(IStarFragmentView lazyManView) {
        this.starFragmentView = lazyManView;
    }

    public LazyManPresenter(ISearchFragmentView searchFragmentView) {
        this.searchFragmentView = searchFragmentView;
    }

    public LazyManPresenter(ICityListView cityListView) {
        this.cityListView = cityListView;
    }



    /**
     * starFragment数据
     * @param lazyManBean
     */
    @Override
    public void starCallback(LazyManBean lazyManBean) {
        if (lazyManBean == null) {
            return;
        }
        this.starFragmentView.refreshStarView(lazyManBean);
    }

    @Override
    public void transportStarData(String path) {
        lazyMan.loadStarData(this,path);
    }

    //---------------------------------------------------------------------
    /**
     * searchFragment数据
     * @param searchBean
     */
    @Override
    public void searchCallback(SearchBean searchBean) {
        if (searchBean == null) {
            return;
        }
        this.searchFragmentView.refreshSearchView(searchBean);
    }

    @Override
    public void transportSearchData() {
        lazyMan.loadSearchData(this);
    }



    //---------------------------------------------------------------------

    @Override
    public void cityListCallback(CityBean cityBean) {

        if (cityBean == null) {
            return;
        }
        this.cityListView.refreshCityListView(cityBean);
    }

    @Override
    public void transportCityListData() {
        lazyMan.loadCityListData(this);
    }

}
