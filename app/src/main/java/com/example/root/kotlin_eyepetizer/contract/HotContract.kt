package com.example.root.kotlin_eyepetizer.contract

import com.example.root.kotlin_eyepetizer.base.IBaseView
import com.example.root.kotlin_eyepetizer.bean.TabInfoBean
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/20
 *  desc: 热门部分的契约类
 *  version:1.0
 */
interface HotContract {

   interface modelView {
      /**
       * 获取 TabInfo
       */
      fun getTabInfo(): Observable<TabInfoBean>
   }

   interface View: IBaseView {
      /**
       * 设置 TabInfo
       */
      fun setTabInfo(tabInfoBean: TabInfoBean)

      fun showError(errorMsg: String, errorCode: Int)
   }

   interface Preserent {
      /**
       * 获取 TabInfo
       */
      fun getTabInfo()
   }

}













