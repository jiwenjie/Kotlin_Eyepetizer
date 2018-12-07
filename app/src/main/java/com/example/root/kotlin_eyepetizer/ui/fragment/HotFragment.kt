package com.example.root.kotlin_eyepetizer.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.baselibrary.adapter.BaseFragmentPagerAdapter
import com.example.baselibrary.utils.ErrorStatus
import com.example.baselibrary.utils.ToastUtils
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.basic.base.BaseAppMvpFragment
import com.example.root.kotlin_eyepetizer.mvp.bean.TabInfoBean
import com.example.root.kotlin_eyepetizer.mvp.contract.HotContract
import com.example.root.kotlin_eyepetizer.mvp.presenter.HotTabPresenter
import com.example.root.kotlin_eyepetizer.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/07
 *  desc:
 *  version:1.0
 */

class HotFragment : BaseAppMvpFragment(), HotContract.View {

   private val mPresenter by lazy { HotTabPresenter() }
   private var mTitle: String? = null

   /**
    * 存放 tab 标题
    */
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

   init {
      mPresenter.attachView(this)
   }

   override fun getLayoutId(): Int = R.layout.fragment_hot

   override fun lazyLoad() {
      mPresenter.getTabInfo()
   }

   override fun initView() {
      mLayoutStatusView = multipleStatusView
      //状态栏透明和间距处理
      activity?.let { StatusBarUtil.darkMode(it) }
      activity?.let { StatusBarUtil.setPaddingSmart(it, mToolbar) }
   }

   override fun showLoading() {
      mLayoutStatusView?.showLoading()
   }

   override fun dismissLoading() {
      mLayoutStatusView?.showContent()
   }

   /**
    * 设置 TabInfo
    */
   override fun setTabInfo(tabInfoBean: TabInfoBean) {
      mLayoutStatusView?.showContent()

      tabInfoBean.tabInfo.tabList.mapTo(mTabTitleList) { it.name }
      tabInfoBean.tabInfo.tabList.mapTo(mFragmentList) { RankFragment.getInstance(it.apiUrl) }
      mHotViewPager.adapter = BaseFragmentPagerAdapter(childFragmentManager, mFragmentList, mTabTitleList)
      mHotTabLayout.setupWithViewPager(mHotViewPager)
   }

   override fun showError(errorMsg: String, errorCode: Int) {
      ToastUtils.showToast(activity!!, errorMsg)
      if (errorCode == ErrorStatus.NETWORK_ERROR) {
         mLayoutStatusView?.showNoNetwork()
      } else {
         mLayoutStatusView?.showError()
      }
   }

   override fun onDestroy() {
      super.onDestroy()
      mPresenter.detachView()
   }
}
