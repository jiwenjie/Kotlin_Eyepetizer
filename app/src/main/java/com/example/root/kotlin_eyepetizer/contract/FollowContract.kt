package com.example.root.kotlin_eyepetizer.contract

import com.example.root.kotlin_eyepetizer.base.IBaseView
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

      fun requestFollowList() : Observable<HomeBean.Issue>  // 获取列表

      fun loadMoreData(url: String) : Observable<HomeBean.Issue>
   }

   interface FollowView : IBaseView {
      /**
       * 设置关注信息数据
       */
      fun setFollowInfo(issue: HomeBean.Issue)

      fun showRrror(errorMsg: String, errorCode: Int)
   }

    interface FollowPresenter {
        /**
         * 获取 List
         */
        fun requestFollowList()

        /**
         * 加载更多
         */
        fun loadMore()
    }

//   interface FollowPresenter : IBasePresenter<FollowView> {
//      /**
//       * 获取 List
//       */
//      fun requestFollowList()
//
//      /**
//       * 加载更多
//       */
//      fun loadMore()
//   }

}


















