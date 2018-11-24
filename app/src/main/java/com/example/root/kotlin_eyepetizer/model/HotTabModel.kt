package com.example.root.kotlin_eyepetizer.model

import com.example.baselibrary.RetrofitManager
import com.example.root.kotlin_eyepetizer.api.ApiService
import com.example.root.kotlin_eyepetizer.api.UriConstant
import com.example.root.kotlin_eyepetizer.bean.TabInfoBean
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/20
 *  desc:热门的 Model
 *  version:1.0
 */
class HotTabModel {

   /**
    * 获取 TabInfo
    */
   fun getTabInfo(): Observable<TabInfoBean> {
      return RetrofitManager.provideClient(UriConstant.BASE_URL)
              .create(ApiService::class.java)
              .getRankList()
              .subscribeOn(Schedulers.io())
              .unsubscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              //.compose(SchedulerUtils.ioToMain())
   }

}










