package com.example.root.kotlin_eyepetizer.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.baselibrary.adapter.BaseFragmentPagerAdapter
import com.example.baselibrary.views.BaseMvpFragment
import com.example.baselibrary.views.BaseMvpPresenter
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.base.IBaseView
import com.example.root.kotlin_eyepetizer.utils.StatusBarUtil
import com.example.root.kotlin_eyepetizer.utils.TabLayoutHelper
import kotlinx.android.synthetic.main.fragment_discovery.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/07
 *  desc: 发现，和热门首页是同样的布局
 *  version:1.0
 */
class DiscoveryFragment : BaseMvpFragment<IBaseView, BaseMvpPresenter<IBaseView>>() {

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

   override fun getLayoutId(): Int {
      return R.layout.fragment_discovery
   }

   override fun loadData() {

   }

   override fun initFragment(savedInstanceState: Bundle?) {
      // 状态栏的部分设置
      activity?.let { StatusBarUtil.darkMode(it) }
      activity?.let { StatusBarUtil.setPaddingSmart(it, fragment_discovery_toolbar) }

      mHeaderTitle.text = mTitle

      tabList.add("关注")
      tabList.add("分类")
      fragments.add(FollowFragment.getInstance("关注"))
      fragments.add(CategoryFragment.getInstance("分类"))

      /**
       * getSupportFragmentManager() 替换成 getChildFragmentManager()
       */
      mViewPager.adapter = BaseFragmentPagerAdapter(childFragmentManager, fragments, tabList)
      mTabLayout.setupWithViewPager(mViewPager)
      TabLayoutHelper.setUpIndicatorWidth(mTabLayout)
   }

   override fun initPresenter(): BaseMvpPresenter<IBaseView>? {
      return null
   }
}
















