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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.ningwenqiang.glory.mygitdemoapp.R;
import com.ningwenqiang.glory.mygitdemoapp.UiTools;

/**
 * create by: 86136
 * create time: 2021/3/31 16:25
 * Function description:  右边是checkBox的
 */

public class InputItem extends FrameLayout {

    public InputItem(@NonNull Context context) {
        super(context);
        init(context);
    }

    private EditText mEditText;
    private CheckBox mCheckBox;
    private View mSeparatorView;

    public InputItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAttr(context, attrs);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray _TypedArray = context.obtainStyledAttributes(attrs, R.styleable.input_item);
        int TextColor = _TypedArray.getColor(R.styleable.input_item_text_color, ContextCompat.getColor(context, R.color.black));
        float TextSize = _TypedArray.getDimension(R.styleable.input_item_text_size, UiTools.sp2px(context, 16));
        Drawable CheckDrawable = _TypedArray.getDrawable(R.styleable.input_item_img_drawable);
        int SeparatorColor = _TypedArray.getColor(R.styleable.input_item_separator_color, ContextCompat.getColor(context, R.color.black));
        String hint = _TypedArray.getString(R.styleable.input_item_text_hint);
        mEditText.setTextColor(TextColor);
        mEditText.setTextSize(TextSize);
        if (!TextUtils.isEmpty(hint)) mEditText.setHint(hint);
        if (CheckDrawable != null) mCheckBox.setButtonDrawable(CheckDrawable);
        mSeparatorView.setBackgroundColor(SeparatorColor);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (inflater != null) {
            inflater.inflate(R.layout.component_input_item, this, true);
            mEditText = findViewById(R.id.edit);
            mCheckBox = findViewById(R.id.check);
            mSeparatorView = findViewById(R.id.separator_view);
        }
    }

    public CheckBox getCheckBox() {
        return mCheckBox;
    }

    public EditText getEditText() {
        return mEditText;
    }

    public String getTextStr() {
        return mEditText.getText().toString().trim();
    }

}
