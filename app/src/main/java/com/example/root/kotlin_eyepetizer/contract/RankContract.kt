package com.example.root.kotlin_eyepetizer.contract

import com.example.root.kotlin_eyepetizer.base.IBaseView
import com.example.root.kotlin_eyepetizer.bean.HomeBean

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/21
 *  desc: 排行榜数据的契约类
 *  version:1.0
 */
interface RankContract {

   interface RankModel {

   }

   interface RankView : IBaseView {
      /**
       * 设置排行榜的数据
       */
      fun setRankList(itemList: ArrayList<HomeBean.Issue.Item>)

      fun showError(errorMsg: String, errorCode: Int)
   }

   interface RankPresenter {
      /**
       * 获取 TabInfo
       */
      fun requestRankList(apiUrl: String)
   }

}



















