package com.example.baselibrary;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2018/11/04
 * desc: 网络数据的请求回掉
 * version:1.0
 */
public class ResultCallBack {
    public interface OnHttpRequestCallBack<T> {
        void onSuccess(T t);

        void onFail(String msg);

        void onError(Throwable t);
    }
}










