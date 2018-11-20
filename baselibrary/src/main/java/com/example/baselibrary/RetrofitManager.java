package com.example.baselibrary;

import android.text.TextUtils;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2018/11/04
 * desc: Retrofit 管理类
 * version:1.0
 */
public class RetrofitManager {

    private static final String TAG = RetrofitManager.class.getSimpleName();
    private static final int CONN_TIME = 5;
    private static final int READ_TIME = 10;
    private static final int WRITE_TIME = 30;
    private static Retrofit mRetrofit;
    /* 用于动态修改 url */
    private static String url;

    private RetrofitManager() {

    }

    public static Retrofit provideClient(String baseUrl) {
        if (!TextUtils.equals(url, baseUrl) && mRetrofit != null) {
            mRetrofit = null;
            url = baseUrl;
        }

        if (mRetrofit == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofit == null) {
                    mRetrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .client(genericOkClient())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return mRetrofit;
    }

    /**
     * 创建 OkHttpClient 实例
     * <p>
     * 头部信息通过 Retrofit Header 注解添加
     */
    private static OkHttpClient genericOkClient() {
        HttpLoggingInterceptor loggingInterceptor
                = new HttpLoggingInterceptor(
                message -> Log.i(TAG, "Interceptor message =>" + message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(CONN_TIME, TimeUnit.SECONDS)
                .readTimeout(READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
                .addNetworkInterceptor(loggingInterceptor)
                .build();
    }
}




















