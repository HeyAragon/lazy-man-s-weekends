package com.qianfeng.aragon.lazy_man_weekend;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by aragon on 2016/10/27.
 */
public class CustomListView extends ListView{
    public CustomListView(Context context) {
        this(context,null);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 重写该方法、使ListView能在所有可以滚动的视图中使用，比如ListView嵌套ListView，或者ScrollView潜逃ListView
         */
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                View.MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
