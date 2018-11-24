package com.example.root.kotlin_eyepetizer.presenter

import com.example.baselibrary.utils.ExceptionHandle
import com.example.baselibrary.views.BaseMvpPresenter
import com.example.root.kotlin_eyepetizer.contract.HotContract
import com.example.root.kotlin_eyepetizer.model.HotTabModel

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/20
 *  desc: 热门的 Presenter
 *  version:1.0
 */
class HotTabPresenter(mView: HotContract.View) : BaseMvpPresenter<HotContract.View>(mView), HotContract.Preserent {

   private val model by lazy {
       HotTabModel()
   }

   override fun getTabInfo() {
      mView.showLoading()
      val disposable = model.getTabInfo()
              .subscribe({
                 mView.dismissLoading()
                 mView.setTabInfo(it)
              }, {
                 mView.showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
              })
   }

}