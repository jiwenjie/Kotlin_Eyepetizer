package com.example.root.kotlin_eyepetizer.mvp.model

import com.example.baselibrary.RetrofitManager
import com.example.root.kotlin_eyepetizer.basic.api.ApiService
import com.example.root.kotlin_eyepetizer.basic.api.Constant
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import com.example.root.kotlin_eyepetizer.mvp.contract.RankContract
import com.example.root.kotlin_eyepetizer.utils.SchedulerUtils
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/21
 *  desc: 排行榜的 Model
 *  version:1.0
 */
class RankModel : RankContract.RankModel {

   /**
    * 获取排行榜的数据
    */
   override fun requestRankList(apiUrl: String): Observable<HomeBean.Issue> {
      return RetrofitManager
              .provideClient(Constant.EYE_BASE_URL)
              .create(ApiService::class.java)
              .getIssueData(apiUrl)
              .compose(SchedulerUtils.ioToMain())
   }
}



















