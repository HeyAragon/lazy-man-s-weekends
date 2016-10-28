package com.qianfeng.aragon.lazy_man_weekend.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.aragon.lazy_man_weekend.R;
import com.qianfeng.aragon.lazy_man_weekend.bean.LazyManBean;
import com.qianfeng.aragon.lazy_man_weekend.http.ImageLoader;
import com.qianfeng.aragon.lazy_man_weekend.presenter.ILazyManPresenter;
import com.qianfeng.aragon.lazy_man_weekend.presenter.LazyManPresenter;
import com.qianfeng.aragon.lazy_man_weekend.view.IStarFragmentView;

import java.util.List;
@SuppressLint("ValidFragment")
public class StarFragment extends Fragment implements IStarFragmentView{
    private final static String ARG_PARAM1 = "hy";
    private String cityName;
    private ILazyManPresenter lazyManPresenter;
    private List<LazyManBean.ResultBean> mResult;
    private PullToRefreshListView mListView;
    private MyBaseAdapter myBaseAdapter;
    private String path = "http://api.lanrenzhoumo.com/main/recommend/index?v=3&session_id=000040a3fb7d64ce1737c6c7bb3c7e4e157c91&lon=114.30963859310197&page=1&lat=30.575388756810078";
    private TextView title;

    public static StarFragment newInstance(String s,String path) {
        StarFragment fragment = new StarFragment(path);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,s);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public StarFragment() {

    }

    public StarFragment(String path) {
        this.path = path;
    }

        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cityName = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_star, container, false);
        loadData();
        initView(view);
        return view;
    }

    private  void initView(View view) {
        mListView = (PullToRefreshListView) view.findViewById(R.id.star_fragment_view_pager);
        title = (TextView)view.findViewById(R.id.star_fragment_title_bar);
        if (cityName != null) {
            title.setText(cityName);
        }
        myBaseAdapter = new MyBaseAdapter();
        mListView.setAdapter(myBaseAdapter);
    }
    private  void loadData() {
        lazyManPresenter = new LazyManPresenter(this);
        lazyManPresenter.transportStarData(path);
    }



    @Override
    public void refreshStarView(LazyManBean lazyManBean) {
        mResult = lazyManBean.getResult();
        myBaseAdapter.notifyDataSetChanged();

    }


    class MyBaseAdapter extends BaseAdapter{

        private final LayoutInflater inflater;

        public MyBaseAdapter() {
            inflater = LayoutInflater.from(getContext());
        }

        @Override
        public int getCount() {
            return mResult == null ? 0 : mResult.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder viewHolder = null;
            if (view == null) {
                view = inflater.inflate(R.layout.star_item_view,parent,false);
                viewHolder = new ViewHolder(view);
            }else{
                viewHolder = (ViewHolder) view.getTag();
            }
            LazyManBean.ResultBean resultBean = mResult.get(position);
            String imagePath = resultBean.getFront_cover_image_list().get(0);
            ImageLoader.load(imagePath,viewHolder.imageview);

            int distance = resultBean.getDistance();
            String s = String.valueOf(distance / 1000) + "." + String.valueOf((distance % 1000) / 100)+"km";

            viewHolder.tittle.setText(resultBean.getTitle());
            viewHolder.position.setText(resultBean.getPoi());
            if (resultBean.getTime_info() == "") {
                viewHolder.endTime.setVisibility(View.INVISIBLE);

            } else {
                viewHolder.endTime.setText(resultBean.getTime_info());
            }
            int collected_num = resultBean.getCollected_num();

            if (viewHolder.collect.isClickable()) {
                ++collected_num;
                viewHolder.collect.setText("  "+collected_num+"人收藏");
            }

            viewHolder.collect.setText("  "+resultBean.getCollected_num()+"人收藏");


            viewHolder.price.setText("￥"+(int)resultBean.getPrice());
            viewHolder.style.setText(resultBean.getCategory());
            if (distance != 0) {
                viewHolder.distance.setText(" • " + s + " • ");
            } else {
                viewHolder.distance.setText(" • ");
            }
            return view;
        }
    }


    class ViewHolder {

        public final TextView tittle;
        public final TextView position;
        public final TextView endTime;
        public final TextView collect;
        public final TextView price;
        public final TextView distance;
        public final TextView style;
        public ImageView imageview;


        public ViewHolder(View view) {
            view.setTag(this);
            imageview = (ImageView) view.findViewById(R.id.star_item_image_view);
            tittle = (TextView) view.findViewById(R.id.stat_item_title);
            position = (TextView) view.findViewById(R.id.star_item_position);
            endTime = (TextView) view.findViewById(R.id.star_item_end_time);
            collect = (TextView) view.findViewById(R.id.star_item_collect);
            price = (TextView) view.findViewById(R.id.star_item_price);
            distance = (TextView) view.findViewById(R.id.star_item_distance);
            style = (TextView) view.findViewById(R.id.stat_item_style);
        }
    }
}
