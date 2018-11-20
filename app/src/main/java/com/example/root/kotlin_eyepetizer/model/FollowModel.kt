package com.example.root.kotlin_eyepetizer.model

import com.example.baselibrary.RetrofitManager
import com.example.root.kotlin_eyepetizer.api.ApiService
import com.example.root.kotlin_eyepetizer.api.UriConstant
import com.example.root.kotlin_eyepetizer.bean.HomeBean
import com.example.root.kotlin_eyepetizer.contract.FollowContract
import com.example.root.kotlin_eyepetizer.utils.SchedulerUtils
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/15
 *  desc: 关注的 Model
 *  version:1.0
 */
class FollowModel : FollowContract.FollowModel {

   /* 为了能在 presenter 中引用，执行打断等操作 */
   var mListDisposable: Disposable? = null
   var mLordMoreDisposable: Disposable? = null

   override fun requestFollowList(): Observable<HomeBean.Issue> {
       return mListDisposable.let {
          RetrofitManager.provideClient(UriConstant.BASE_URL)
              .create(ApiService::class.java)
              .getFollowInfo()
              .compose(SchedulerUtils.ioToMain())
      }
   }

   override fun loadMoreData(url: String): Observable<HomeBean.Issue> {
        return mLordMoreDisposable.let {
            RetrofitManager.provideClient(UriConstant.BASE_URL)
                .create(ApiService::class.java)
                .getIssueData(url)
                .compose(SchedulerUtils.ioToMain())
        }
   }
}


