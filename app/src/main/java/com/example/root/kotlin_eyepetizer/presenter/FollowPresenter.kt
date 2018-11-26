package com.example.root.kotlin_eyepetizer.presenter

import com.example.baselibrary.utils.ExceptionHandle
import com.example.root.kotlin_eyepetizer.base.BasePresenter
import com.example.root.kotlin_eyepetizer.contract.FollowContract
import com.example.root.kotlin_eyepetizer.model.FollowModel

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/15
 *  desc: 关注页面的 Presenter
 *  version:1.0
 */

class FollowPresenter : BasePresenter<FollowContract.View>(), FollowContract.Presenter {

   private val followModel by lazy { FollowModel() }
   private var nextPageUrl: String? = null

   /**
    *  请求关注数据
    */
   override fun requestFollowList() {
      checkViewAttached()
      mRootView?.showLoading()
      val disposable = followModel.requestFollowList()
              .subscribe({ issue ->
                 mRootView?.apply {
                    dismissLoading()
                    nextPageUrl = issue.nextPageUrl
                    setFollowInfo(issue)
                 }
              }, { throwable ->
                 mRootView?.apply {
                    //处理异常
                    showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                 }
              })
      addSubscription(disposable)
   }

   /**
    * 加载更多
    */
   override fun loadMoreData() {
      val disposable = nextPageUrl?.let {
         followModel.loadMoreData(it)
                 .subscribe({ issue ->
                    mRootView?.apply {
                       nextPageUrl = issue.nextPageUrl
                       setFollowInfo(issue)
                    }

                 }, { t ->
                    mRootView?.apply {
                       showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }
                 })

      }
      if (disposable != null) {
         addSubscription(disposable)
      }
   }
}


