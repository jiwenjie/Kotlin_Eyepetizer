package com.example.root.kotlin_eyepetizer.model

import com.example.baselibrary.RetrofitManager
import com.example.root.kotlin_eyepetizer.api.ApiService
import com.example.root.kotlin_eyepetizer.api.UriConstant
import com.example.root.kotlin_eyepetizer.bean.HomeBean
import com.example.root.kotlin_eyepetizer.utils.SchedulerUtils
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/21
 *  desc: 排行榜的 Model
 *  version:1.0
 */
class RankModel {

   /**
    * 获取排行榜的数据
    */
   fun requestRankList(apiUrl: String): Observable<HomeBean.Issue> {
      return RetrofitManager
              .provideClient(UriConstant.BASE_URL)
              .create(ApiService::class.java)
              .getIssueData(apiUrl)
              .compose(SchedulerUtils.ioToMain())
   }
}



















