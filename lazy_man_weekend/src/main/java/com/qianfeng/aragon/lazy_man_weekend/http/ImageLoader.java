package com.qianfeng.aragon.lazy_man_weekend.http;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by aragon on 2016/10/12.
 */
public class ImageLoader {

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

            executorService.execute(new ImageThread(imageView, path));
        } else {
            imageView.setImageBitmap(bitmap);

        }
    }
}
