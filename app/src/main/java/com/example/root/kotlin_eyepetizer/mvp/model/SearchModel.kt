package com.example.root.kotlin_eyepetizer.mvp.model

import com.example.baselibrary.RetrofitManager
import com.example.root.kotlin_eyepetizer.basic.api.ApiService
import com.example.root.kotlin_eyepetizer.basic.api.Constant
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import com.example.root.kotlin_eyepetizer.mvp.contract.SearchContract
import com.example.root.kotlin_eyepetizer.utils.SchedulerUtils
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/03
 *  desc:搜索的 Model
 *  version:1.0
 */
class SearchModel : SearchContract.SearchModel {

   /**
    * 请求热门关键词的数据
    */
   override fun requestHotWordData(): Observable<ArrayList<String>> {
      return RetrofitManager.provideClient(Constant.BASE_URL)
              .create(ApiService::class.java)
              .getHotWord()
              .compose(SchedulerUtils.ioToMain())
   }

   /**
    * 搜索关键词返回的结果
    */
   override fun getSearchResult(words: String): Observable<HomeBean.Issue> {
      return RetrofitManager.provideClient(Constant.BASE_URL)
              .create(ApiService::class.java)
              .getSearchData(words)
              .compose(SchedulerUtils.ioToMain())
   }

   /**
    * 加载更多
    */
   override fun loadMore(url: String): Observable<HomeBean.Issue> {
      return RetrofitManager.provideClient(Constant.BASE_URL)
              .create(ApiService::class.java)
              .getIssueData(url)
              .compose(SchedulerUtils.ioToMain())
   }
}