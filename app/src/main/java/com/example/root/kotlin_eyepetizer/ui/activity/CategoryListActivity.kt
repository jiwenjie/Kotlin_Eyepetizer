@file:Suppress("PLUGIN_WARNING")

package com.example.root.kotlin_eyepetizer.ui.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baselibrary.utils.ToastUtils
import com.example.baselibrary.views.BaseMvpActivity
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.ui.adapter.CategoryDetailAdapter
import com.example.root.kotlin_eyepetizer.basic.api.Constant
import com.example.root.kotlin_eyepetizer.mvp.bean.CategoryBean
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import com.example.root.kotlin_eyepetizer.mvp.contract.CategoryDetailContract
import com.example.root.kotlin_eyepetizer.glide.GlideApplyOptions
import com.example.root.kotlin_eyepetizer.mvp.presenter.CategoryDetailListPresenter
import com.example.root.kotlin_eyepetizer.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_category_list.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/26
 *  desc:具体到某一个分类的列表活动界面
 *  version:1.0
 */
class CategoryListActivity : BaseMvpActivity<CategoryDetailContract.CategoryDetailView,
        CategoryDetailListPresenter>(), CategoryDetailContract.CategoryDetailView {

   /**
    * 注意尝试下这里使用 val 可不可以，还是必须使用 var 才行
    */
   private val itemList by lazy { ArrayList<HomeBean.Issue.Item>() }
   private val adapter by lazy { CategoryDetailAdapter(this, itemList) }

   private var categoryData: CategoryBean? = null

   /**
    * 是否加载更多
    */
   private var loadingMore = false

   override fun getLayoutId(): Int = R.layout.activity_category_list

   @SuppressLint("SetTextI18n")
   override fun initActivity(savedInstanceState: Bundle?) {
      categoryData = intent.getSerializableExtra(Constant.BUNDLE_CATEGORY_DATA) as CategoryBean?

      setSupportActionBar(toolbar)
      supportActionBar?.setDisplayHomeAsUpEnabled(true)
      toolbar.setNavigationOnClickListener { finish() }

      //加载 headerImage
      Glide.with(this)
              .load(categoryData?.headerImage)
              .apply(GlideApplyOptions.getRequestOptions())
              .into(activity_category_image)

      tv_category_desc.text = "#${categoryData?.description}#"

      collapsing_toolbar_layout.title = categoryData?.name
      collapsing_toolbar_layout.setExpandedTitleColor(Color.WHITE)   //设置还没收缩时状态下的字体颜色
      collapsing_toolbar_layout.setCollapsedTitleTextColor(Color.BLACK)    //设置收缩后 Toolbar 上字体的颜色

      mRecyclerView.layoutManager = LinearLayoutManager(this)
      mRecyclerView.adapter = adapter
      //实现下拉自动加载更多
      mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
         override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            val itemCount = mRecyclerView.layoutManager!!.itemCount
            val lastVisibleItem = (mRecyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            if (!loadingMore && lastVisibleItem == (itemCount - 1)) {
               loadingMore = true
               mPresenter.loadMoreData()
            }
         }
      })

      // 状态栏的透明和间距处理
      StatusBarUtil.darkMode(this)
      StatusBarUtil.setPaddingSmart(this, toolbar)

      mLayoutStatusView = activity_category_multipleStatusView
   }

   override fun initPresenter(): CategoryDetailListPresenter = CategoryDetailListPresenter(this)

   override fun loadData() {
      //获取当前的分类列表
//      categoryData?.id?.let{
//         mPresenter.getCategoryDetailList(it)
//      }
      mPresenter.getCategoryDetailList(categoryData!!.id)
   }

   override fun setCateDetailList(itemList: ArrayList<HomeBean.Issue.Item>) {
      loadingMore = false
      adapter.addAllData(itemList)
   }

   override fun showError(errorMsg: String) {
      ToastUtils.showToast(this, errorMsg)
      activity_category_multipleStatusView.showError()
   }

   override fun showLoading() {
//      activity_category_multipleStatusView.showLoading()
   }

   override fun dismissLoading() {
//      activity_category_multipleStatusView.showContent()
   }
}