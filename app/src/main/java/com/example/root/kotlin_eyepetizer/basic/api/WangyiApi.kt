package com.example.root.kotlin_eyepetizer.basic.api

import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_bean.WangyiNewsListBean
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/11
 *  desc:网易新闻的 api
 *  version:1.0
 */
interface WangyiApi {

   @GET("/nc/article/headline/T1348647909107/{id}-20.html")
   fun getNewsList(@Path("id") id: Int): Observable<WangyiNewsListBean>

   @GET("/nc/article/{id}/full.html")
   fun getNewsDetail(@Path("id") id: String): Observable<ResponseBody>

}