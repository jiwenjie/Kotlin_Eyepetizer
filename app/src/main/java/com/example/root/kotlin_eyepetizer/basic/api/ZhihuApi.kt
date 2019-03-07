package com.example.root.kotlin_eyepetizer.basic.api

import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_bean.ZhihuDailyDetailBean
import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_bean.ZhihuDailyListBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/11
 *  desc:知乎的 api 接口
 *  version:1.0
 */
interface ZhihuApi {

   @GET("/api/4/news/latest")
   fun getLastDailyList(): Observable<ZhihuDailyListBean>

   @GET("/api/4/news/before/{date}")
   fun getDailyListWithDate(@Path("date") date: String): Observable<ZhihuDailyListBean>

   @GET("/api/4/news/{id}")
   fun getZhihuDailyDetail(@Path("id") id: String): Observable<ZhihuDailyDetailBean>

}
























