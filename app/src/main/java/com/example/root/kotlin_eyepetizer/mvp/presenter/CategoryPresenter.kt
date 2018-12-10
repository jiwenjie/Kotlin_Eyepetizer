package com.example.root.kotlin_eyepetizer.mvp.presenter

import android.annotation.SuppressLint
import com.example.baselibrary.baseutils.ExceptionHandle
import com.example.baselibrary.views.BaseMvpPresenter
import com.example.root.kotlin_eyepetizer.mvp.contract.CategoryContract
import com.example.root.kotlin_eyepetizer.mvp.model.CategoryModel

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/26
 *  desc:分类本身的 Presenter (分类的详情点击也是一个列表部分)
 *  version:1.0
 */

class CategoryPresenter(view: CategoryContract.CategoryView)
        : BaseMvpPresenter<CategoryContract.CategoryView>(view), CategoryContract.CategoryPresenter {

   private val model by lazy { CategoryModel() }

   @SuppressLint("CheckResult")
   override fun getCategoryData() {
      mView.showLoading()
       // 把订阅添加进池子中
      addSubscription(model.getCategoryData()
              .subscribe({
                 mView.dismissLoading()
                 mView.showCategory(it)
              },{
                 mView?.showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
              }))
   }
}
