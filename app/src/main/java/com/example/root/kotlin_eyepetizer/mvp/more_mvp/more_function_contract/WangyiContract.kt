package com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_contract

import com.example.root.kotlin_eyepetizer.basic.base.IBaseView

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/12
 *  desc:网易相关的契约类
 *  version:1.0
 */
interface WangyiContract {

   /**
    * model 的接口
    */
   interface WangyiModel {
//      fun getNewsList(index: Int): Observable<>
   }

   /**
    * View 的接口
    */
   interface WangyiView : IBaseView {

   }

   /**
    * Presenter 的接口
    */
   interface ZhihuPresenter {
      /**
       * 获取最新数据
       */
      fun loadLatestList()

      /**
       * 加载更多
       */
      fun loadMore()
   }
}












