package com.ningwenqiang.glory.okhttpdemo.bean

/**
 * 对网络消息进行统一处理的
 * @param <T> 需要进行统一处理的
</T> */
class NetRootBean<T : JsonNetBean?> : JsonNetBean {

    var code //返回码
            = 0

    var info //数据信息
            : String? = null

    var data: T? = null

}
