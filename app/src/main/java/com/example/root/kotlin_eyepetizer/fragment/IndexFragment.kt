package com.example.root.kotlin_eyepetizer.fragment

import android.os.Bundle
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.base.BaseAppMvpFragment

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/07
 *  desc:
 *  version:1.0
 */

class IndexFragment : BaseAppMvpFragment() {

   private var mTitle: String? = null

   companion object {
      fun getInstance(title: String): IndexFragment {
         val fragment = IndexFragment()
         val bundle = Bundle()
         fragment.arguments = bundle
         fragment.mTitle = title
         return fragment
      }
   }

   override fun getLayoutId(): Int {
      return R.layout.fragment_index
   }

   override fun initView() {

   }

   override fun lazyLoad() {

   }
}