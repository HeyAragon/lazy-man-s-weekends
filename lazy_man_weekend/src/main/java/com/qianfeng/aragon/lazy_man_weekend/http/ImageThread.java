package com.qianfeng.aragon.lazy_man_weekend.http;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by aragon on 2016/10/12.
 */
public class ImageThread implements Runnable {
    /**
     * 规定图片的宽高像素最大是100个像素
     */
//    public static final int MAX_WIDTH = 100;
//    public static final int MAX_HEIGHT = 100;
    /**
     * 图片地址
     */
    private String imagePath;

    private ImageView mImageView;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //防止因为ListView的Item复用导致的图片出现跳动Bug
            if (mImageView.getTag().equals(imagePath)) {
                Bitmap bitmap = (Bitmap) msg.obj;
                //将图片缓存到内存中
                MemoryCacheTool.save(imagePath,bitmap);
                mImageView.setImageBitmap(bitmap);
            }

        }
    };

    public ImageThread(ImageView imageView, String imagePath) {
        this.mImageView = imageView;
        this.imagePath = imagePath;
        mImageView.setTag(imagePath);
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            URL url = new URL(imagePath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                int length = 0;
                byte[] buffer = new byte[1024];
                while ((length = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }
                outputStream.flush();
            }

            //将图片流转换成Bitmap对象
            byte[] bytes = outputStream.toByteArray();
//            //二次采样
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            //仅仅获取图片的大小属性
//            options.inJustDecodeBounds = true;
//            //获取图片的大小 不加载图片的内容
//            BitmapFactory.decodeByteArray(bytes, 0, bytes.length,options);
//            int outWidth = options.outWidth;
//            int outHeight = options.outHeight;
//            //计算压缩比
//            int widthRadio = outWidth/MAX_WIDTH;
//            int heightRadio = outHeight/MAX_HEIGHT;
//            int radio = widthRadio > heightRadio ? widthRadio : heightRadio;
//            //加载图片的内容，并且对图片按照比率进行压缩
//            options.inJustDecodeBounds = false;
//            //配置图片压缩比
//            options.inSampleSize = radio;
//            //转化图片
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            //将Bitmap对象传递到主线程中
            Message message = mHandler.obtainMessage();
            message.obj = bitmap;
            mHandler.sendMessage(message);

        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
