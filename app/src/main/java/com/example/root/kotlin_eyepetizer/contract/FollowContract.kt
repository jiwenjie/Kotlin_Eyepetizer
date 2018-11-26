package com.example.root.kotlin_eyepetizer.contract

import com.example.root.kotlin_eyepetizer.base.IBaseView
import com.example.root.kotlin_eyepetizer.base.IPresenter
import com.example.root.kotlin_eyepetizer.bean.HomeBean
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/14
 *  desc: 关注页面的契约类
 *  version:1.0
 */
interface FollowContract {

   interface FollowModel {
      fun requestFollowList(): Observable<HomeBean.Issue>  // 获取列表

      fun loadMoreData(url: String): Observable<HomeBean.Issue>
   }

   interface View : IBaseView {
      /**
       * 设置关注信息数据
       */
      fun setFollowInfo(issue: HomeBean.Issue)

      fun showError(errorMsg: String, errorCode: Int)
   }

   interface Presenter : IPresenter<View> {
      /**
       * 获取List
       */
      fun requestFollowList()

      /**
       * 加载更多
       */
      fun loadMoreData()
   }
}


















