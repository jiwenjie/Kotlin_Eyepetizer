package com.example.root.kotlin_eyepetizer.presenter

import com.example.baselibrary.utils.ExceptionHandle
import com.example.baselibrary.views.BaseMvpPresenter
import com.example.root.kotlin_eyepetizer.contract.RankContract
import com.example.root.kotlin_eyepetizer.model.RankModel

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/21
 *  desc: 获取排行榜数据的 Presenter
 *  version:1.0
 */
class RankPresenter(view: RankContract.RankView) : BaseMvpPresenter<RankContract.RankView>(view), RankContract.RankPresenter {

   private val rankModel by lazy { RankModel() }

   override fun requestRankList(apiUrl: String) {
      mView.showLoading()
      val disposable = rankModel.requestRankList(apiUrl)
              .subscribe({
                 mView.apply {
                    dismissLoading()
                    setRankList(it.itemList)
                 }
              },{
                 mView.apply {
                    showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                 }
              })
   }
}


















