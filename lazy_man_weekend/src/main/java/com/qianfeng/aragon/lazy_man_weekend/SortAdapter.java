package com.qianfeng.aragon.lazy_man_weekend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aragon on 2016/10/27.
 */
public class SortAdapter extends BaseAdapter {

    private List<String> list = null;
    private Context mContext;

    public SortAdapter(List<String> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     * @param list
     */
    public void updateListView(List<String> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.list == null ? 0: this.list.size();
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        return null;
    }

    final static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;

        public ViewHolder(View view){
            view.setTag(this);
            tvTitle = (TextView) view.findViewById(R.id.title);
            tvLetter = (TextView) view.findViewById(R.id.catalog);
        }
    }

    /**
     * 根据
     * @param position
     * @return
     */
    public int getSectionForPosition(int position) {
        String s1 = list.get(position);
        int count = 0;
        for (String s : list) {
            if (s == s1) {
                return count;
            } else {
                count++;
            }
        }
        return -1;
    }


}
