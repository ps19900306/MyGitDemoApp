package com.ningwenqiang.glory.mygitdemoapp.ui_function.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.ningwenqiang.glory.mygitdemoapp.R;
import com.ningwenqiang.glory.mygitdemoapp.UiTools;

/**
 * create by: 86136
 * create time: 2021/3/31 16:25
 * Function description:
 */
public class InputItem1 extends FrameLayout {



    private EditText mEditText;
    private ImageView mImageView;
    private View mSeparatorView;

    public InputItem1(@NonNull Context context) {
        super(context);
        init(context);
    }

    public InputItem1(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAttr(context, attrs);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (inflater != null) {
            inflater.inflate(R.layout.component_input_item1, this, true);
            mEditText = findViewById(R.id.edit);
            mImageView = findViewById(R.id.image);
            mSeparatorView = findViewById(R.id.separator_view);
        }
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray _TypedArray = context.obtainStyledAttributes(attrs, R.styleable.input_item);
        int TextColor = _TypedArray.getColor(R.styleable.input_item_text_color, ContextCompat.getColor(context, R.color.black));
        float TextSize = _TypedArray.getDimension(R.styleable.input_item_text_size, UiTools.sp2px(context, 16));
        Drawable drawable = _TypedArray.getDrawable(R.styleable.input_item_img_drawable);
        int SeparatorColor = _TypedArray.getColor(R.styleable.input_item_separator_color, ContextCompat.getColor(context, R.color.black));
        String hint = _TypedArray.getString(R.styleable.input_item_text_hint);
        mEditText.setTextColor(TextColor);
        mEditText.setTextSize(TextSize);
        if (!TextUtils.isEmpty(hint)) mEditText.setHint(hint);
        if (drawable != null) mImageView.setImageDrawable(drawable);
        mSeparatorView.setBackgroundColor(SeparatorColor);
    }


    public String getTextStr() {
        return mEditText.getText().toString().trim();
    }

}
