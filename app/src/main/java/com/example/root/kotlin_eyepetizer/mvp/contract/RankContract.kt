package com.example.root.kotlin_eyepetizer.mvp.contract

import com.example.root.kotlin_eyepetizer.basic.base.IBaseView
import com.example.root.kotlin_eyepetizer.basic.base.IPresenter
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/21
 *  desc: 排行榜数据的契约类
 *  version:1.0
 */
interface RankContract {

   interface RankModel {
      fun requestRankList(apiUrl: String): Observable<HomeBean.Issue>
   }

   interface View : IBaseView {
      /**
       * 设置排行榜的数据
       */
      fun setRankList(itemList: ArrayList<HomeBean.Issue.Item>)

      fun showError(errorMsg: String, errorCode: Int)
   }

   interface Presenter : IPresenter<View> {
      /**
       * 获取 TabInfo
       */
      fun requestRankList(apiUrl: String)
   }
}



















