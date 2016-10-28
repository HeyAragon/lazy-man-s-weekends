package com.qianfeng.aragon.lazy_man_weekend.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.aragon.lazy_man_weekend.CityActivity;
import com.qianfeng.aragon.lazy_man_weekend.R;
import com.qianfeng.aragon.lazy_man_weekend.bean.SearchBean;
import com.qianfeng.aragon.lazy_man_weekend.http.ImageLoader;
import com.qianfeng.aragon.lazy_man_weekend.presenter.ILazyManPresenter;
import com.qianfeng.aragon.lazy_man_weekend.presenter.LazyManPresenter;
import com.qianfeng.aragon.lazy_man_weekend.view.ISearchFragmentView;
import com.qianfeng.aragon.lazy_man_weekend.welcome.SearchFragmentDetailActivity;

import java.util.List;

public class SearchFragment extends Fragment implements ISearchFragmentView{

    private ILazyManPresenter mLazyManPresenter;
    public List<SearchBean.ResultBean> mResult;
    public GridView mGridView;
    public MySearchAdapter mySearchAdapter;
    public Button cityBtn;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initView(view);
        loadData();
        setListener();
        return view;
    }

    private void loadData() {
        mLazyManPresenter = new LazyManPresenter(this);
        mLazyManPresenter.transportSearchData();
    }

    private void initView(View view) {
        mGridView = (GridView) view.findViewById(R.id.search_fragment_grid_view);
        cityBtn = (Button) view.findViewById(R.id.search_fragment_btn);
        cityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CityActivity.class);
                startActivity(intent);
            }
        });
        mySearchAdapter = new MySearchAdapter();
        mGridView.setAdapter(mySearchAdapter);
    }

    private void setListener() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = mResult.get(position).getName();
                String cn_name = mResult.get(position).getCn_name();
                Intent intent = new Intent(getContext(), SearchFragmentDetailActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("cn_name",cn_name);
                startActivity(intent);
            }
        });
    }
    @Override
    public void refreshSearchView(SearchBean searchBean) {
        mResult = searchBean.getResult();
        mySearchAdapter.notifyDataSetChanged();
    }


    class MySearchAdapter extends BaseAdapter {

        private static final String TAG = "androidhy";
        public final LayoutInflater inflater;

        public MySearchAdapter() {
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
            SearchViewHolder viewHolder = null;
            if (view == null) {
                view =  inflater.inflate(R.layout.search_item_view, parent, false);
                viewHolder = new SearchViewHolder(view);
            } else {
                viewHolder = (SearchViewHolder) view.getTag();
            }
            SearchBean.ResultBean resultBean = mResult.get(position);
            ImageLoader.load(resultBean.getIcon_view(),viewHolder.mImageView);
            viewHolder.mTxt.setText(resultBean.getCn_name());
            return view;
        }


        class SearchViewHolder{

            public final ImageView mImageView;
            public final TextView mTxt;

            public SearchViewHolder(View view) {
                view.setTag(this);
                mImageView = (ImageView) view.findViewById(R.id.search_item_iv);
                mTxt = (TextView) view.findViewById(R.id.search_item_txt);
            }

        }
    }
}

