package com.ningwenqiang.glory.mygitdemoapp.data_processing.gson.bean;

/**
 * create by: 86136
 * create time: 2020/9/8 10:33
 * Function description:  泛型基础类测试
 */
public class  BaseGenericParadigm<T> {

    private int code;

    private String text;

    private T  data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
