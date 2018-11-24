package com.example.root.kotlin_eyepetizer.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.baselibrary.utils.ErrorStatus
import com.example.baselibrary.utils.ToastUtils
import com.example.baselibrary.views.BaseMvpFragment
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.adapter.FollowAdapter
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
class FollowFragment : BaseMvpFragment<FollowContract.FollowView, FollowPresenter>(), FollowContract.FollowView {

   private var mTitle: String? = null
   private var itemList = ArrayList<HomeBean.Issue.Item>()
   private val mFollowAdapter by lazy { activity?.let { FollowAdapter(activity!!, itemList) } }

   /**
    * 是否加载更多
    */
   private var loadingMore = false

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

   override fun initFragment(savedInstanceState: Bundle?) {
      mRecyclerView.layoutManager = LinearLayoutManager(activity)
      mRecyclerView.adapter = mFollowAdapter
      // 实现自动加载
      mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
         override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            val itemCount = mRecyclerView.layoutManager?.itemCount
            val lastVisibleItem = (mRecyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            if (itemCount != null) {
               if (!loadingMore && lastVisibleItem == (itemCount - 1)) {
                  loadingMore = true
                  mPresenter.loadMore()
               }
            }
         }
      })
      mLayoutStatusView = multipleStatusView
   }

   override fun initPresenter(): FollowPresenter = FollowPresenter(this)

   override fun loadData() {
      mPresenter.requestFollowList()
   }

   override fun setFollowInfo(issue: HomeBean.Issue) {
      loadingMore = false
      itemList = issue.itemList
      mFollowAdapter?.addAllData(itemList)
   }

   override fun showRrror(errorMsg: String, errorCode: Int) {
      ToastUtils.showToast(activity!!, errorMsg)
      if (errorCode == ErrorStatus.NETWORK_ERROR) {
         multipleStatusView.showNoNetwork()
      } else {
         multipleStatusView.showError()
      }
   }

   override fun showLoading() {
      multipleStatusView.showLoading()
   }

   override fun dismissLoading() {
      multipleStatusView.showContent()
   }
}