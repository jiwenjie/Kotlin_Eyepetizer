package com.example.root.kotlin_eyepetizer.mvp.presenter

import com.example.baselibrary.utils.ExceptionHandle
import com.example.baselibrary.views.BaseMvpPresenter
import com.example.root.kotlin_eyepetizer.mvp.contract.SearchContract
import com.example.root.kotlin_eyepetizer.mvp.model.SearchModel

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/03
 *  desc:搜索的 Presenter
 *  version:1.0
 */
@Suppress("NAME_SHADOWING")
class SearchPresenter(view: SearchContract.SearchView) : BaseMvpPresenter<SearchContract.SearchView>(view),
        SearchContract.SearchPresenter {

   private var nextPageUrl: String? = null

   private val mModel by lazy { SearchModel() }

   /**
    * 获取热门关键词
    */
   override fun requestHotWordData() {
      mView.apply {
         closeSoftKeyboard()
         showLoading()
      }

      addSubscription(mModel.requestHotWordData()
              .subscribe({
                 mView.apply {
                    setHotWordData(it)
                 }
              }, {
                 mView.apply {
                    showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                 }
              }))
   }

   /**
    * 查询关键字
    */
   override fun querySearchData(words: String) {
      mView.apply {
         closeSoftKeyboard()
         showLoading()
      }

      addSubscription(mModel.getSearchResult(words)
              .subscribe({
                 mView.apply {
                    dismissLoading()
                    if (it.count > 0 && it.itemList.size > 0) {
                       nextPageUrl = it.nextPageUrl
                       setSearchResult(it)
                    } else {
                       setEmptyView()
                    }
                 }
              }, {
                 mView.apply {
                    dismissLoading()
                    // 处理异常
                    showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                 }
              }))
   }

   /**
    * 加载更多数据
    */
   override fun loadMoreData() {
      nextPageUrl?.let {
         addSubscription(mModel.loadMore(it)
                 .subscribe({ it ->
                    mView.apply {
                       nextPageUrl = it.nextPageUrl
                       setSearchResult(it)
                    }
                 }, { it ->
                    mView.apply {
                       // 处理异常
                       showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                    }
                 }))
      }
   }
}


















