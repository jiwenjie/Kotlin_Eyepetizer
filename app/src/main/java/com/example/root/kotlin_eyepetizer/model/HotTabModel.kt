package com.example.root.kotlin_eyepetizer.model

import com.example.baselibrary.RetrofitManager
import com.example.root.kotlin_eyepetizer.api.ApiService
import com.example.root.kotlin_eyepetizer.api.UriConstant
import com.example.root.kotlin_eyepetizer.bean.TabInfoBean
import com.example.root.kotlin_eyepetizer.contract.HotContract
import com.example.root.kotlin_eyepetizer.utils.SchedulerUtils
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/20
 *  desc:热门的 Model
 *  version:1.0
 */
class HotTabModel : HotContract.HotModel {

   /**
    * 获取 TabInfo
    */
   override fun getTabInfo(): Observable<TabInfoBean> {
      return RetrofitManager.provideClient(UriConstant.BASE_URL)
              .create(ApiService::class.java)
              .getRankList()
              .compose(SchedulerUtils.ioToMain())
   }

}










