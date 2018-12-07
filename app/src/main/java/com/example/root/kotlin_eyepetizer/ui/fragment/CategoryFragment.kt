package com.example.root.kotlin_eyepetizer.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.baselibrary.utils.ErrorStatus
import com.example.baselibrary.utils.ToastUtils
import com.example.baselibrary.views.BaseMvpFragment
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.mvp.bean.CategoryBean
import com.example.root.kotlin_eyepetizer.mvp.contract.CategoryContract
import com.example.root.kotlin_eyepetizer.mvp.presenter.CategoryPresenter
import com.example.root.kotlin_eyepetizer.ui.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/14
 *  desc: 分类的 Activity
 *  version:1.0
 */
class CategoryFragment : BaseMvpFragment<CategoryContract.CategoryView, CategoryPresenter>(),
        CategoryContract.CategoryView {

   private var mTitle: String? = null
   private val itemList = ArrayList<CategoryBean>()
   private val adapter by lazy { CategoryAdapter(activity!!, itemList) }

   companion object {
      fun getInstance(title: String): CategoryFragment {
         val fragment = CategoryFragment()
         val bundle = Bundle()
         fragment.arguments = bundle
         fragment.mTitle = title
         return fragment
      }
   }

   override fun getLayoutId(): Int = R.layout.fragment_category

   override fun initFragment(savedInstanceState: Bundle?) {
      mLayoutStatusView = category_MultipleStatusView
      mLayoutStatusView.showContent()

      mRecyclerView.layoutManager = GridLayoutManager(activity, 2)
      mRecyclerView.adapter = adapter
   }

   override fun loadData() {
      mPresenter.getCategoryData()
   }

   override fun initPresenter(): CategoryPresenter = CategoryPresenter(this)

   override fun showCategory(categoryList: ArrayList<CategoryBean>) {
      adapter.addAllData(categoryList)
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
}