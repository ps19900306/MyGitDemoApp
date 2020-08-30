package com.ningwenqiang.glory.okhttpdemo.bean;

/**
 * 对网络消息进行统一处理的
 * @param <T> 需要进行统一处理的
 */
public class ABasicBeanA<T> implements AJsonNetBean {


    int code ;//返回码

    String info  ;//数据信息

    T   data;
}
