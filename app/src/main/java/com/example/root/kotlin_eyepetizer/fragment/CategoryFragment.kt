package com.example.root.kotlin_eyepetizer.fragment

import android.os.Bundle
import com.example.baselibrary.views.BaseMvpPresenter
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.base.BaseFragment
import com.example.root.kotlin_eyepetizer.base.IBaseView

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/14
 *  desc: 分类的 Activity
 *  version:1.0
 */
class CategoryFragment : BaseFragment<IBaseView, BaseMvpPresenter<IBaseView>>() {

   private var mTitle: String? = null

   companion object {
      fun getInstance(title: String): CategoryFragment {
         val fragment = CategoryFragment()
         val bundle = Bundle()
         fragment.arguments = bundle
         fragment.mTitle = title
         return fragment
      }
   }

   override fun initPresenter(): BaseMvpPresenter<IBaseView>? {
      return null
   }

   override fun getLayoutId(): Int = R.layout.fragment_category

   override fun initFragment(savedInstanceState: Bundle?) {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override fun loadData() {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

}