package com.qianfeng.aragon.lazy_man_weekend;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by aragon on 2016/10/27.
 */
public class SlideBar extends View{

    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;

    private Paint mPaint = new Paint();

    private int choosen = -1;//被选中状态

    private TextView mTextDialog;

    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }

    private static String[] letters={ "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#" };

    public SlideBar(Context context) {
        this(context,null);
    }

    public SlideBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();//获取对应高度
        int width = getWidth();
        int singleHeight = height / letters.length;//获取每一个字母所对应的高度
        for (int i = 0; i < letters.length; i++) {
            mPaint.setColor(Color.parseColor("#d3d3d3"));
            mPaint.setTypeface(Typeface.DEFAULT);
            mPaint.setAntiAlias(true);//抗锯齿
            mPaint.setTextSize(20);
            if (i == choosen) {
                mPaint.setColor(Color.RED);
                mPaint.setFakeBoldText(true);
            }

            float xPos = width / 2 - mPaint.measureText(letters[i]);
            float yPos = singleHeight * i + singleHeight;
            mPaint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY(); //点击的Y坐标
        final int oldChoosen = choosen;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        // 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
        final int c = (int) (y / getHeight() * letters.length);

        switch (action) {
            case MotionEvent.ACTION_UP:
                setBackgroundDrawable(new ColorDrawable(0x00000000));
                choosen = -1;
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;

            default:
                setBackgroundResource(R.drawable.sidebar_background);
                if (oldChoosen != c) {
                    if (c >= 0 && c < letters.length) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(letters[c]);
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(letters[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }
                        choosen = c;
                        invalidate();
                    }
                }
                break;
        }


        return true;
    }


    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    public interface OnTouchingLetterChangedListener{
        void onTouchingLetterChanged(String letter);
    }
}
