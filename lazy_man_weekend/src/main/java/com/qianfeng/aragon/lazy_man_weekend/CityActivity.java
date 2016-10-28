package com.qianfeng.aragon.lazy_man_weekend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.aragon.lazy_man_weekend.bean.CityBean;
import com.qianfeng.aragon.lazy_man_weekend.presenter.ILazyManPresenter;
import com.qianfeng.aragon.lazy_man_weekend.presenter.LazyManPresenter;
import com.qianfeng.aragon.lazy_man_weekend.view.ICityListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityActivity extends AppCompatActivity implements ICityListView, SlideBar.OnTouchingLetterChangedListener {
    private static final String TAG = "androidgc";
    private ILazyManPresenter presenter;
    private Map<String, List<String>> maps = new HashMap<>();
    private Map<String, String> idMap = new HashMap<>();
    private List<CityBean.ResultBean> result;
    private ListView mListView;
    private CityAdapter cityAdapter;
    private List<String> totalData = new ArrayList<>();
    private List<CityBean.ResultBean.CityListBean> hotCity;
    private GridView mGridView;
    private View hotCityHeadView;
    private GridViewAdapter gridViewAdapter;
    private List<String> letters = new ArrayList<>();
    private SlideBar slideBar;
    private TextView dialog;
    private ImageButton imageBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        loadData();
        initView();

    }

    private void initView() {
//        slideBar = (SlideBar) findViewById(R.id.slide_bar);
//        dialog = (TextView) findViewById(R.id.dialog);
//        slideBar.setTextView(dialog);
//        slideBar.setOnTouchingLetterChangedListener(new SlideBar.OnTouchingLetterChangedListener() {
//            @Override
//            public void onTouchingLetterChanged(String letter) {
//
//            }
//        });

        mListView = (ListView) findViewById(R.id.city_list_view);
        hotCityHeadView = LayoutInflater.from(this).inflate(R.layout.city_hot_cities, null);
        mGridView = (GridView) hotCityHeadView.findViewById(R.id.city_grid_view);
        View allCityViewTxt = LayoutInflater.from(this).inflate(R.layout.all_city_item, null);
        View localCityView = LayoutInflater.from(this).inflate(R.layout.local_city_item, null);
        gridViewAdapter = new GridViewAdapter();
        mGridView.setAdapter(gridViewAdapter);
        mListView.addHeaderView(localCityView);
        mListView.addHeaderView(hotCityHeadView);
        mListView.addHeaderView(allCityViewTxt);
        cityAdapter = new CityAdapter();
        mListView.setAdapter(cityAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public String cityId;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CityActivity.this, CityActivityDetails.class);
                String str = totalData.get(position - 3);
                Log.i(TAG, "onItemClick:str= " + str);
                for (String key : idMap.keySet()) {
                    if (str.equals(key)) {
                        Log.i(TAG, "onItemClick: key=" + key);
                        cityId = idMap.get(key);
//                        cityIdCallback.sendCityID(cityId);
                        intent.putExtra("cityId", cityId);
                        intent.putExtra("cityName", key);
                        Log.i(TAG, "onItemClick: " + cityId);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CityActivity.this, CityActivityDetails.class);
                int city_id = hotCity.get(position).getCity_id();
                intent.putExtra("cityId", city_id + "");
                startActivity(intent);

            }
        });

        imageBtn = (ImageButton) findViewById(R.id.city_back_btn);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


//    public void quit() {
//        if (isFinishing()) {
//            Toast.makeText("")
//        }
//    }
    private void loadData() {
        presenter = new LazyManPresenter(this);
        presenter.transportCityListData();
    }


    @Override
    public void onTouchingLetterChanged(String letter) {

    }

    @Override
    public void refreshCityListView(CityBean cityBean) {
        result = cityBean.getResult();
        hotCity = result.get(0).getCity_list();
        Log.i(TAG, "refreshCityListView: " + "hotCity:size=" + hotCity.size());
        for (int i = 1; i < result.size(); i++) {
            String begin_key = result.get(i).getBegin_key();
            totalData.add(begin_key);
            letters.add(begin_key);
            List<String> cities = new ArrayList<>();
            List<CityBean.ResultBean.CityListBean> city_list = result.get(i).getCity_list();
            for (int j = 0; j < city_list.size(); j++) {
                String city_name = city_list.get(j).getCity_name();
                int city_id = city_list.get(j).getCity_id();
                totalData.add(city_name);
//                Log.i(TAG, "refreshCityListView: "+city_name+city_id);
                idMap.put(city_name, city_id + "");
                cities.add(city_list.get(j).getCity_name());
                maps.put(begin_key, cities);
            }
        }
        for (String s : totalData) {
//            Log.i(TAG, "refreshCityListView: "+s+"/t");
        }
        cityAdapter.notifyDataSetChanged();
        gridViewAdapter.notifyDataSetChanged();
    }


    class GridViewAdapter extends BaseAdapter {

        public final LayoutInflater inflater2;

        public GridViewAdapter() {
            inflater2 = LayoutInflater.from(CityActivity.this);
        }

        @Override
        public int getCount() {
            return hotCity == null ? 0 : hotCity.size();
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
            View view = null;
            ViewHolderHotCity viewHolderHotCity = null;
            if (view == null) {
                view = inflater2.inflate(R.layout.city_hot_city_item, parent, false);
                viewHolderHotCity = new ViewHolderHotCity(view);
            } else {
                viewHolderHotCity = (ViewHolderHotCity) view.getTag();
            }
            String city_name = hotCity.get(position).getCity_name();
            if (city_name != null) {
                viewHolderHotCity.cityName.setText(city_name);
            }

            return view;
        }

        class ViewHolderHotCity {

            public TextView cityName;

            public ViewHolderHotCity(View view) {
                view.setTag(this);
                cityName = (TextView) view.findViewById(R.id.hot_city_name_txt);
            }

        }
    }


    class CityAdapter extends BaseAdapter {
        private static final int LETTER_TYPE = 1;
        private static final int CITY_TYPE = 2;
        public final LayoutInflater inflater;

        public CityAdapter() {
            inflater = LayoutInflater.from(CityActivity.this);
        }


        @Override
        public int getCount() {
            return totalData == null ? 0 : totalData.size();
        }

        @Override
        public Object getItem(int position) {
            return totalData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {

            return 3;
        }

        @Override
        public int getItemViewType(int position) {

            if (maps.containsKey(totalData.get(position))) {
                return LETTER_TYPE;
            }
            return CITY_TYPE;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolderLetter viewHolderLetter = null;
            ViewHolderCity viewHolderCity = null;
            int itemViewType = getItemViewType(position);
//            if()
            if (view == null) {
                switch (itemViewType) {
                    case LETTER_TYPE:
                        view = inflater.inflate(R.layout.city_letter_item, parent, false);
                        viewHolderLetter = new ViewHolderLetter(view);
                        break;
                    case CITY_TYPE:
                        view = inflater.inflate(R.layout.city_cities_item, parent, false);
                        viewHolderCity = new ViewHolderCity(view);
//                        viewHolderCity.city.setTag(idMap.get(totalData.get(position)));
                        break;
                }
            } else {
                switch (itemViewType) {
                    case LETTER_TYPE:
                        viewHolderLetter = (ViewHolderLetter) view.getTag();
                        break;
                    case CITY_TYPE:
                        viewHolderCity = (ViewHolderCity) view.getTag();
                        break;
                }
            }
            switch (itemViewType) {
                case LETTER_TYPE:
                    viewHolderLetter.letter.setText(totalData.get(position));
                    break;
                case CITY_TYPE:
                    viewHolderCity.city.setText(totalData.get(position));
//                    viewHolderCity.city.setTag(idMap.get(totalData.get(position)));
                    break;
            }

            return view;
        }

    }

    class ViewHolderLetter {
        public TextView letter;

        public ViewHolderLetter(View view) {
            view.setTag(this);
            letter = (TextView) view.findViewById(R.id.city_item_letter_txt);
        }
    }

    class ViewHolderCity {
        public TextView city;

        public ViewHolderCity(View view) {
            view.setTag(this);
            city = (TextView) view.findViewById(R.id.city_item_cities_txt);
        }
    }
}
