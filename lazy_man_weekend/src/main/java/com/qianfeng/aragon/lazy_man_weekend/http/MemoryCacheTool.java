package com.qianfeng.aragon.lazy_man_weekend.http;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

/**
 * Created by aragon on 2016/10/25.
 */
public class MemoryCacheTool {

    /**
     * 参数：最大的缓存空间
     */
    private static LruCache<String,Bitmap> lruCache = new LruCache<String ,Bitmap>(4*1024*1024){
        public static final String TAG = "androidhy";

        /**
         * 计算每一个还存进来的图片的大小
         * @param key
         * @param value
         * @return
         */
        @Override
        protected int sizeOf(String key, Bitmap value) {
            Log.i(TAG, "sizeOf: "+value.getByteCount());
            return value.getByteCount();
        }
    };

    /**
     * 缓存图片到内容中
     * @param key
     * @param bitmap
     */
    public static void save(String key, Bitmap bitmap) {
        lruCache.put(key, bitmap);
    }

    public static Bitmap read(String key) {
        Bitmap bitmap = lruCache.get(key);
        return bitmap;
    }
}
