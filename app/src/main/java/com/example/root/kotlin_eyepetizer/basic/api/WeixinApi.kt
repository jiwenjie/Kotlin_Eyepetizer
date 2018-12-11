package com.example.root.kotlin_eyepetizer.basic.api

import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_bean.WeixinChoiceListBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/11
 *  desc:
 *  version:1.0
 */
interface WeixinApi {

   @GET("/weixin/query")
   fun getWeixinChoiceList(@Query("pno") page: Int, @Query("ps") ps: Int,
                           @Query("dtype") dttype: String, @Query("key") key: String): Observable<WeixinChoiceListBean>

}















