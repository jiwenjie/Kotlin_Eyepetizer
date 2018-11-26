package com.example.root.kotlin_eyepetizer.presenter

import com.example.baselibrary.utils.ExceptionHandle
import com.example.root.kotlin_eyepetizer.base.BasePresenter
import com.example.root.kotlin_eyepetizer.contract.HotContract
import com.example.root.kotlin_eyepetizer.model.HotTabModel

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/20
 *  desc: 热门的 Presenter
 *  version:1.0
 */

class HotTabPresenter : BasePresenter<HotContract.View>(), HotContract.Presenter {

   private val hotTabModel by lazy { HotTabModel() }

   override fun getTabInfo() {
      checkViewAttached()
      mRootView?.showLoading()
      val disposable = hotTabModel.getTabInfo()
              .subscribe({ tabInfo ->
                 mRootView?.setTabInfo(tabInfo)
              }, { throwable ->
                 //处理异常
                 mRootView?.showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
              })
      addSubscription(disposable)
   }
}
