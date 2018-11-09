package com.example.root.kotlin_eyepetizer.fragment

import android.os.Bundle
import com.example.baselibrary.views.BaseMvpPresenter
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.base.BaseFragment
import com.example.root.kotlin_eyepetizer.base.IBaseView

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/07
 *  desc:
 *  version:1.0
 */
class DiscoveryFragment : BaseFragment<IBaseView, BaseMvpPresenter<IBaseView>>() {

   private var mTitle: String? = null

   companion object {
      fun getInstance(title: String): DiscoveryFragment {
         val fragment = DiscoveryFragment()
         val bundle = Bundle()
         fragment.arguments = bundle
         fragment.mTitle = title
         return fragment
      }
   }

   override fun loadData() {

   }

   override fun getLayoutId(): Int {
      return R.layout.fragment_discovery
   }

   override fun initFragment(savedInstanceState: Bundle?) {

   }

   override fun initPresenter(): BaseMvpPresenter<IBaseView>? {
      return null
   }
}