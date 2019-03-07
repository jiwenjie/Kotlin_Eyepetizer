@file:Suppress("IMPLICIT_BOXING_IN_IDENTITY_EQUALS")

package com.example.root.kotlin_eyepetizer.ui.more_function_ui.more_fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.baselibrary.baseutils.ErrorStatus
import com.example.baselibrary.baseutils.ToastUtils
import com.example.baselibrary.views.BaseMvpFragment
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_bean.ZhihuDailyItemBean
import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_contract.ZhihuContract
import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_presenter.ZhihuPresenter
import com.example.root.kotlin_eyepetizer.ui.more_function_ui.more_adapter.ZhihuAdapter
import kotlinx.android.synthetic.main.fragment_zhihu.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/11
 *  desc: 知乎日报的 Fragment
 *  version:1.0
 */
class ZhihuFragment : BaseMvpFragment<ZhihuContract.ZhihuView, ZhihuPresenter>(), ZhihuContract.ZhihuView {

   private val beanList by lazy { ArrayList<ZhihuDailyItemBean>() }
   private val zhihuAdapter by lazy { ZhihuAdapter(activity!!, beanList) }

   companion object {
      fun getInstance(): ZhihuFragment {
         val fragment = ZhihuFragment()
         val bundle = Bundle()
         fragment.arguments = bundle
         return fragment
      }
   }

   /**
    * 是否加载更多
    */
   private var loadingMore = false

   override fun initFragment(savedInstanceState: Bundle?) {
      mLayoutStatusView = zhihu_multipleStatusView
      mLayoutStatusView.showContent()

      zhihu_recyclerView.adapter = zhihuAdapter
      zhihu_recyclerView.layoutManager = LinearLayoutManager(activity)

      // 监听滑动事件加载更多
      zhihu_recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
         override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            val itemCount = zhihu_recyclerView.layoutManager?.itemCount
            val lastVisibleItem = (zhihu_recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            if (!loadingMore && lastVisibleItem == (itemCount!! - 1)) {
               loadingMore = true
               mPresenter.loadMoreList()
            }
         }
      })
   }

   override fun loadData() {
      mPresenter.loadLatestList()
   }

   override fun updateContentList(ItemList: ArrayList<ZhihuDailyItemBean>) {
      //        Logger.e(list.toString());
      loadingMore = false
      zhihuAdapter.addAllData(ItemList)
   }

   override fun showError(errorMsg: String, errorCode: Int) {
      ToastUtils.showToast(activity!!, errorMsg)
      if (errorCode == ErrorStatus.NETWORK_ERROR) {
         mLayoutStatusView.showNoNetwork()
      } else {
         mLayoutStatusView.showError()
      }
   }

   override fun showLoading() {
      mLayoutStatusView.showLoading()
   }

   override fun dismissLoading() {
      mLayoutStatusView.showContent()
   }

   override fun initPresenter(): ZhihuPresenter = ZhihuPresenter(this)

   override fun getLayoutId(): Int = R.layout.fragment_zhihu
}