package com.example.root.kotlin_eyepetizer.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.baselibrary.adapter.BaseFragmentPagerAdapter
import com.example.baselibrary.utils.ErrorStatus
import com.example.baselibrary.utils.ToastUtils
import com.example.baselibrary.views.BaseMvpFragment
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.bean.TabInfoBean
import com.example.root.kotlin_eyepetizer.contract.HotContract
import com.example.root.kotlin_eyepetizer.presenter.HotTabPresenter
import com.example.root.kotlin_eyepetizer.utils.StatusBarUtil
import com.example.root.kotlin_eyepetizer.utils.TabLayoutHelper
import kotlinx.android.synthetic.main.fragment_discovery.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/07
 *  desc:
 *  version:1.0
 */
class HotFragment : BaseMvpFragment<HotContract.View, HotTabPresenter>(), HotContract.View {

   private var mTitle: String? = null

   private val mTabTitleList = ArrayList<String>()
   private val mFragmentList = ArrayList<Fragment>()

   companion object {
      fun getInstance(title: String): HotFragment {
         val fragment = HotFragment()
         val bundle = Bundle()
         fragment.arguments = bundle
         fragment.mTitle = title
         return fragment
      }
   }

   override fun loadData() {
      mPresenter.getTabInfo()
   }

   override fun getLayoutId(): Int {
      return R.layout.fragment_discovery  // 发现和热门是同一个布局，只是内容显示不同
   }

   override fun initFragment(savedInstanceState: Bundle?) {
      mLayoutStatusView = fragment_discovery_multipleStatusView
      //状态栏透明和间距处理
      activity?.let { StatusBarUtil.darkMode(it) }
      activity?.let { StatusBarUtil.setPaddingSmart(it, fragment_discovery_toolbar) }
   }

   override fun initPresenter(): HotTabPresenter {
      return HotTabPresenter(this)
   }

   override fun setTabInfo(tabInfoBean: TabInfoBean) {
      mLayoutStatusView.showContent()

      tabInfoBean.tabInfo.tabList.mapTo(mTabTitleList) { it.name }
      tabInfoBean.tabInfo.tabList.mapTo(mFragmentList) { RankFragment.getInstance(it.apiUrl) }

      mViewPager.adapter = BaseFragmentPagerAdapter(fragmentManager, mFragmentList, mTabTitleList)
      mTabLayout.setupWithViewPager(mViewPager)
      TabLayoutHelper.setUpIndicatorWidth(mTabLayout)
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