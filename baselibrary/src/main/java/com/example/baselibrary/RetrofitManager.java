package com.example.baselibrary;

import android.text.TextUtils;
import android.util.Log;
import com.example.baselibrary.baseutils.AppUtils;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

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

//    private String token = new Preference<String>("token", "");

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
        //添加一个log拦截器,打印所有的log
        HttpLoggingInterceptor loggingInterceptor
                = new HttpLoggingInterceptor(
                message -> Log.i(TAG, "Interceptor message =>" + message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //设置请求的缓存大小和位置
        return new OkHttpClient.Builder()
                .addInterceptor(addQueryParameterInterceptor()) // 参数添加
                .addInterceptor(addHeaderInterceptor()) // token 过滤
                .connectTimeout(CONN_TIME, TimeUnit.SECONDS)
                .readTimeout(READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
                .addNetworkInterceptor(loggingInterceptor)  //日志，所有的请求响应度看到
                .build();
    }

    /**
     * 设置公共参数
     */
    private static Interceptor addQueryParameterInterceptor() {
        return chain -> {
            Request originalRequest = chain.request();
            HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                    .addQueryParameter("udid", "d2807c895f0348a180148c9dfa6f2feeac0781b5")
                    .addQueryParameter("deviceModel", AppUtils.Companion.getMobileModel())
                    .build();
            Request request = originalRequest.newBuilder().url(modifiedUrl).build();
            return chain.proceed(request);
        };
    }

    /**
     * 设置头
     */
    private static Interceptor addHeaderInterceptor() {
        return chain -> {
            Request.Builder builder = chain.request().newBuilder()
                    .header("token", "")
                    .method(chain.request().method(), chain.request().body());

            Request request = builder.build();
            return chain.proceed(request);
        };
    }
}




















