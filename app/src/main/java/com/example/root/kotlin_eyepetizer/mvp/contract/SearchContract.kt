package com.example.root.kotlin_eyepetizer.mvp.contract

import com.example.root.kotlin_eyepetizer.basic.base.IBaseView
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/03
 *  desc: 搜索的契约类
 *  version:1.0
 */
interface SearchContract {

   interface SearchModel {
      /**
       * 请求热门关键词的数据
       */
      fun requestHotWordData(): Observable<ArrayList<String>>

      /**
       * 搜索关键词返回的结果
       */
      fun getSearchResult(words: String): Observable<HomeBean.Issue>

      /**
       * 加载更多
       */
      fun loadMore(url: String): Observable<HomeBean.Issue>
   }

   interface SearchView: IBaseView {
      /**
       * 设置热门关键词数据
       */
      fun setHotWordData(string: ArrayList<String>)

      /**
       * 设置搜索关键词返回的结果
       */
      fun setSearchResult(issue: HomeBean.Issue)

      /**
       * 关闭软键盘
       */
      fun closeSoftKeyboard()

      /**
       * 设置空 View
       */
      fun setEmptyView()

      fun showError(errorMsg: String, errorCode: Int)
   }

   interface SearchPresenter {
      /**
       * 获取热门关键字的数据
       */
      fun requestHotWordData()

      /**
       * 查询搜索
       */
      fun querySearchData(words: String)

      /**
       * 加载更多
       */
      fun loadMoreData()
   }

}















