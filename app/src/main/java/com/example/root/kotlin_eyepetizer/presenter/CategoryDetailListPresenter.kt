package com.example.root.kotlin_eyepetizer.presenter

import com.example.baselibrary.views.BaseMvpPresenter
import com.example.root.kotlin_eyepetizer.contract.CategoryDetailContract
import com.example.root.kotlin_eyepetizer.model.CategoryDetailListModel

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/26
 *  desc:分类详情的 Presenter，该界面是列表
 *  version:1.0
 */
class CategoryDetailListPresenter(view: CategoryDetailContract.CategoryDetailView)
   : BaseMvpPresenter<CategoryDetailContract.CategoryDetailView>(view),
        CategoryDetailContract.CategoryDetailPresenter {

   private val mModel by lazy { CategoryDetailListModel() }

   private var nextPageUrl: String? = null

   override fun getCategoryDetailList(id: Long) {
      mView.showLoading()
      // 添加进池子里，防止出现内存泄漏
      addSubscription(mModel.getCategoryDetailList(id)
              .subscribe({
                 mView.dismissLoading()
                 nextPageUrl = it.nextPageUrl
                 mView.setCateDetailList(it.itemList)
              }, {
                  mView.showError(it.toString())
              }))
   }

   override fun loadMoreData() {
      // 添加进池子里，防止出现内存泄漏
      addSubscription(mModel.loadMoreData(nextPageUrl!!)
              .subscribe({
                 nextPageUrl = it.nextPageUrl
                 mView.setCateDetailList(it.itemList)
              },{
                  mView.showError(it.toString())
              }))
   }
}



















