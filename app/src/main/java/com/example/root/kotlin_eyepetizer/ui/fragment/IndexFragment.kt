package com.example.root.kotlin_eyepetizer.ui.fragment

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.baselibrary.utils.ErrorStatus
import com.example.baselibrary.utils.ToastUtils
import com.example.baselibrary.views.BaseMvpFragment
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import com.example.root.kotlin_eyepetizer.mvp.contract.IndexContract
import com.example.root.kotlin_eyepetizer.mvp.presenter.IndexPresenter
import com.example.root.kotlin_eyepetizer.ui.adapter.IndexAdapter
import com.example.root.kotlin_eyepetizer.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_index.*
import java.text.SimpleDateFormat
import java.util.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/07
 *  desc:首页 fragment，列表 和 banner 页面的展示
 *  version:1.0
 */

@Suppress("DEPRECATION")
class IndexFragment : BaseMvpFragment<IndexContract.IndexView, IndexPresenter>(),
            IndexContract.IndexView {

   private var mTitle: String? = null
   private var num: Int = 1   // 表示请求数据的页数

   private var mIndexAdapter: IndexAdapter? = null
   private var loadingMore = false
   private var isRefresh = false

   companion object {
      fun getInstance(title: String): IndexFragment {
         val fragment = IndexFragment()
         val bundle = Bundle()
         fragment.arguments = bundle
         fragment.mTitle = title
         return fragment
      }
   }

   override fun getLayoutId(): Int = R.layout.fragment_index

   override fun initPresenter(): IndexPresenter = IndexPresenter(this)


   override fun initFragment(savedInstanceState: Bundle?) {
      // 内容跟随偏移
      mRefreshLayout.setEnableHeaderTranslationContent(true)
      mRefreshLayout.setOnRefreshListener {
         isRefresh = true
         mPresenter.requestHomeData(num)
      }

      mRefreshLayout.setPrimaryColorsId(R.color.color_light_black, R.color.color_title_bg)

      mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
         override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
               val childCount = mRecyclerView.childCount
               val itemCount = mRecyclerView.layoutManager?.itemCount
               val firstVisibleItem = (mRecyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
               if (firstVisibleItem + childCount == itemCount) {
                  if (!loadingMore) {
                     loadingMore = true
                     mPresenter.loadMoreData()
                  }
               }
            }
         }

         // RecyclerView 滾動的時候調用
         override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val currentVisibleItemPosition = (mRecyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            if (currentVisibleItemPosition == 0) {
               // 背景設置為透明
               toolbar.setBackgroundColor(getColor(R.color.color_translucent))
               iv_search.setImageResource(R.mipmap.ic_action_search_white)
               tv_header_title.text = ""
            } else {
               if (mIndexAdapter?.beanList!!.size > 1) {
                  toolbar.setBackgroundColor(getColor(R.color.color_title_bg))
                  iv_search.setImageResource(R.mipmap.ic_action_search_black)
                  val itemList = mIndexAdapter!!.beanList
                  val item = itemList[currentVisibleItemPosition + mIndexAdapter!!.bannerItemSize - 1]
                  if (item.type == "textHeader") {
                     tv_header_title.text = item.data?.text
                  } else {
                     tv_header_title.text = simpleDateFormat.format(item.data?.date)
                  }
               }
            }
         }
      })

      iv_search.setOnClickListener {
         openSearchActivity()
      }

      mLayoutStatusView = multipleStatusView
      //狀態欄的處理
      activity?.let { StatusBarUtil.darkMode(it) }
      activity?.let { StatusBarUtil.setPaddingSmart(it, toolbar) }
   }

   override fun loadData() {
      mPresenter.requestHomeData(num)
   }

   /**
    * 设置首页数据
    */
   override fun setHomeData(homeBean: HomeBean) {
      mLayoutStatusView?.showContent()

      //Adapter
      mIndexAdapter = activity?.let { IndexAdapter(it) }
      mIndexAdapter!!.addAllData(homeBean.issueList[0].itemList)
      // 設置 banner 大小
      mIndexAdapter?.setBannerSize(homeBean.issueList[0].count)

      mRecyclerView.adapter = mIndexAdapter
      mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
      mRecyclerView.itemAnimator = DefaultItemAnimator()
   }

   override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {
      loadingMore = false
      mIndexAdapter?.addAllData(itemList)
   }

   override fun showError(msg: String, errorCode: Int) {
      if (errorCode == ErrorStatus.NETWORK_ERROR) {
         mLayoutStatusView?.showNoNetwork()
      } else {
         mLayoutStatusView?.showError()
      }
   }


   /**
    * 显示 Loading （下拉刷新的时候不需要显示 Loading）
    */
   override fun showLoading() {
      if (!isRefresh) {
         isRefresh = false
         mLayoutStatusView?.showLoading()
      }
   }

   override fun dismissLoading() {
      mRefreshLayout.finishRefresh()
   }

   private fun openSearchActivity() {
      ToastUtils.showToast(activity!!, "打開搜索頁面")
   }

   private val simpleDateFormat by lazy {
      SimpleDateFormat("- MMM. dd, 'Brunch' -", Locale.ENGLISH)
   }

   fun getColor(colorId: Int): Int {
      return resources.getColor(colorId)
   }

}