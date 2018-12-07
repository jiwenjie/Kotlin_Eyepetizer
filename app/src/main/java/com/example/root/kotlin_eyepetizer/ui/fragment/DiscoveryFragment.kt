package com.example.root.kotlin_eyepetizer.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.baselibrary.adapter.BaseFragmentPagerAdapter
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.basic.base.BaseAppMvpFragment
import com.example.root.kotlin_eyepetizer.utils.StatusBarUtil
import com.example.root.kotlin_eyepetizer.utils.TabLayoutHelper
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/07
 *  desc: 发现，和热门首页是同样的布局
 *  version:1.0
 */
class DiscoveryFragment : BaseAppMvpFragment() {

   private val tabList = ArrayList<String>()
   private val fragments = ArrayList<Fragment>()

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

   override fun getLayoutId(): Int = R.layout.fragment_hot

   override fun initView() {
      //状态栏透明和间距处理
      activity?.let { StatusBarUtil.darkMode(it) }
      activity?.let { StatusBarUtil.setPaddingSmart(it, mToolbar) }

      tv_header_title.text = mTitle

      tabList.add("关注")
      tabList.add("分类")
      fragments.add(FollowFragment.getInstance("关注"))
      fragments.add(CategoryFragment.getInstance("分类"))

      /**
       * getSupportFragmentManager() 替换为getChildFragmentManager()
       */
      mHotViewPager.adapter = BaseFragmentPagerAdapter(childFragmentManager, fragments, tabList)
      mHotTabLayout.setupWithViewPager(mHotViewPager)
      TabLayoutHelper.setUpIndicatorWidth(mHotTabLayout)
   }

   override fun lazyLoad() {

   }
}
