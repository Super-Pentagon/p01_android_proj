package com.pentagon.p01_android_proj.util.http;

/**
 * @author: Create by huangyuanhao on 2020-07-04
 * @email: 814484626@qq.com
 */
public interface BaseCallBack<T> {

    /**
     * 成功回调
     * @param t
     */
    void onSucceed(T t);

    /**
     * 失败回调
     * @param errorMsg
     */
    void onFailed(String errorMsg);

}
