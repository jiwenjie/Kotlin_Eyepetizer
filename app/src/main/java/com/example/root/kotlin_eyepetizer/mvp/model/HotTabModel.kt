package com.example.root.kotlin_eyepetizer.mvp.model

import com.example.baselibrary.RetrofitManager
import com.example.root.kotlin_eyepetizer.basic.api.ApiService
import com.example.root.kotlin_eyepetizer.basic.api.Constant
import com.example.root.kotlin_eyepetizer.mvp.bean.TabInfoBean
import com.example.root.kotlin_eyepetizer.mvp.contract.HotContract
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
      return RetrofitManager.provideClient(Constant.BASE_URL)
              .create(ApiService::class.java)
              .getRankList()
              .compose(SchedulerUtils.ioToMain())
   }

}










