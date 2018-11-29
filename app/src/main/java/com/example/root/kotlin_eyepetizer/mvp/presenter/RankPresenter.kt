package com.example.root.kotlin_eyepetizer.mvp.presenter

import com.example.baselibrary.utils.ExceptionHandle
import com.example.root.kotlin_eyepetizer.basic.base.BasePresenter
import com.example.root.kotlin_eyepetizer.mvp.contract.RankContract
import com.example.root.kotlin_eyepetizer.mvp.model.RankModel

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/21
 *  desc: 获取排行榜数据的 Presenter
 *  version:1.0
 */

class RankPresenter : BasePresenter<RankContract.View>(), RankContract.Presenter {

   private val rankModel by lazy { RankModel() }

   /**
    *  请求排行榜数据
    */
   override fun requestRankList(apiUrl: String) {
      checkViewAttached()
      mRootView?.showLoading()
      val disposable = rankModel.requestRankList(apiUrl)
              .subscribe({ issue ->
                 mRootView?.apply {
                    dismissLoading()
                    setRankList(issue.itemList)
                 }
              }, { throwable ->
                 mRootView?.apply {
                    //处理异常
                    showError(ExceptionHandle.handleException(throwable),ExceptionHandle.errorCode)
                 }
              })
      addSubscription(disposable)
   }
}

