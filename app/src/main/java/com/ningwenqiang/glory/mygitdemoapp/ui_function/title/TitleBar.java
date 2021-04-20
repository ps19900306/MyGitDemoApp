package com.ningwenqiang.glory.mygitdemoapp.ui_function.title;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.ningwenqiang.glory.mygitdemoapp.R;
import com.ningwenqiang.glory.mygitdemoapp.UiTools;

/**
 * create by: 86136
 * create time: 2021/4/1 10:54
 * Function description:
 * 这个只接受左边图标增加
 */
public class TitleBar extends LinearLayout {


    private ImageView mBackImg;
    private TextView mTitleTv;

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (inflater != null) {
            inflater.inflate(R.layout.component_title_bar, this, true);
            mBackImg = findViewById(R.id.back_img);
            mTitleTv = findViewById(R.id.title_tv);
        }
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray _TypedArray = context.obtainStyledAttributes(attrs, R.styleable.title_bar);
        int TextColor = _TypedArray.getColor(R.styleable.title_bar_text_color, ContextCompat.getColor(context, R.color.black));
        float TextSize = _TypedArray.getDimension(R.styleable.title_bar_text_size, UiTools.sp2px(context, 16));
        Drawable drawable = _TypedArray.getDrawable(R.styleable.title_bar_img_drawable);
        boolean showBack = _TypedArray.getBoolean(R.styleable.title_bar_show_back, false);
        mTitleTv.setTextColor(TextColor);
        mTitleTv.setTextSize(TextSize);
        if (showBack) {
            mBackImg.setVisibility(VISIBLE);
            if (drawable != null) mBackImg.setImageDrawable(drawable);
        }
    }


    //添加右边的按钮事件
    public ImageView addRightButton(int resId, OnClickListener onClickListener) {
        LinearLayout.LayoutParams layoutParams = new LayoutParams(getHeight(), getHeight());
        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(layoutParams);
        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), resId));
        imageView.setOnClickListener(onClickListener);
        addView(imageView);
        return imageView;
    }

    public void setBackListener(OnClickListener onClickListener) {
        mBackImg.setOnClickListener(onClickListener);
    }


    public void setBackViewVisibility(boolean visibility) {
        if (visibility) {
            mBackImg.setVisibility(VISIBLE);
        } else {
            mBackImg.setVisibility(GONE);
        }
    }

    public void setBackViewResId(int resId) {
        mBackImg.setImageDrawable(ContextCompat.getDrawable(getContext(), resId));
    }

    public void setTitleText(String str) {
        mTitleTv.setText(str);
    }

    public void setTitleText(int resId) {
        mTitleTv.setText(resId);
    }

}
