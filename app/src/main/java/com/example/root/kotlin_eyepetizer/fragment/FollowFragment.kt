package com.example.root.kotlin_eyepetizer.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.baselibrary.utils.ErrorStatus
import com.example.baselibrary.utils.ToastUtils
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.adapter.FollowAdapter
import com.example.root.kotlin_eyepetizer.base.BaseAppMvpFragment
import com.example.root.kotlin_eyepetizer.bean.HomeBean
import com.example.root.kotlin_eyepetizer.contract.FollowContract
import com.example.root.kotlin_eyepetizer.presenter.FollowPresenter
import kotlinx.android.synthetic.main.layout_recyclerview.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/14
 *  desc: 关注的 Fragment
 *  version:1.0
 */
class FollowFragment : BaseAppMvpFragment(), FollowContract.View {

   private var mTitle: String? = null

   private var itemList = ArrayList<HomeBean.Issue.Item>()

   private val mPresenter by lazy { FollowPresenter() }

   private val mFollowAdapter by lazy { activity?.let { FollowAdapter(it, itemList) } }

   /**
    * 是否加载更多
    */
   private var loadingMore = false

   init {
      mPresenter.attachView(this)
   }

   companion object {
      fun getInstance(title: String): FollowFragment {
         val fragment = FollowFragment()
         val bundle = Bundle()
         fragment.arguments = bundle
         fragment.mTitle = title
         return fragment
      }
   }

   override fun getLayoutId(): Int = R.layout.layout_recyclerview

   override fun initView() {
      mRecyclerView.layoutManager = LinearLayoutManager(activity)
      mRecyclerView.adapter = mFollowAdapter
      //实现自动加载
      mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
         override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            val itemCount = mRecyclerView.layoutManager?.itemCount
            val lastVisibleItem = (mRecyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            if (!loadingMore && lastVisibleItem == (itemCount!! - 1)) {
               loadingMore = true
               mPresenter.loadMoreData()
            }
         }
      })
      mLayoutStatusView = multipleStatusView
   }

   override fun lazyLoad() {
      mPresenter.requestFollowList()
   }

   override fun showLoading() {
      multipleStatusView.showLoading()
   }

   override fun dismissLoading() {
      multipleStatusView.showContent()
   }

   override fun setFollowInfo(issue: HomeBean.Issue) {
      loadingMore = false
      itemList = issue.itemList
      mFollowAdapter?.addAllData(itemList)
   }

   /**
    * 显示错误信息
    */
   override fun showError(errorMsg: String, errorCode: Int) {
      ToastUtils.showToast(activity!!, errorMsg)
      if (errorCode == ErrorStatus.NETWORK_ERROR) {
         multipleStatusView.showNoNetwork()
      } else {
         multipleStatusView.showError()
      }
   }

   override fun onDestroy() {
      super.onDestroy()
      mPresenter.detachView()
   }
}
