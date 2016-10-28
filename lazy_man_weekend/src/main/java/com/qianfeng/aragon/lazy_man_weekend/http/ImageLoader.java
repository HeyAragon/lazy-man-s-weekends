package com.qianfeng.aragon.lazy_man_weekend.http;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.qianfeng.aragon.lazy_man_weekend.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by aragon on 2016/10/12.
 */
public class ImageLoader {

    private static final String TAG = "androidhy";
    private static ExecutorService executorService;

    private static void init() {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(4);
        }
    }

    public static void load(String path, ImageView imageView) {
        init();
        //从内存中取数据
        Bitmap bitmap = MemoryCacheTool.read(path);
        if (bitmap == null) {
            Log.i(TAG, "load: "+"网络加载数据"+path);
            imageView.setImageResource(R.drawable.abcde);
            executorService.execute(new ImageThread(imageView, path));
        } else {
            imageView.setImageBitmap(bitmap);
            Log.i(TAG, "load: "+"从内存中取数据");

        }
    }
}
