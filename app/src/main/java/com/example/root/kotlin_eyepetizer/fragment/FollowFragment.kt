package com.example.root.kotlin_eyepetizer.fragment

import android.os.Bundle
import com.example.baselibrary.views.BaseMvpFragment
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.bean.HomeBean
import com.example.root.kotlin_eyepetizer.contract.FollowContract
import com.example.root.kotlin_eyepetizer.presenter.FollowPresenter

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/14
 *  desc: 关注的 Fragment
 *  version:1.0
 */
class FollowFragment : BaseMvpFragment<FollowContract.FollowView, FollowPresenter>(), FollowContract.FollowView {

   private var mTitle: String? = null
   private var itemList = ArrayList<HomeBean.Issue.Item>()


   companion object {
      fun getInstance(title: String): FollowFragment {
         val fragment = FollowFragment()
         val bundle = Bundle()
         fragment.arguments = bundle
         fragment.mTitle = title
         return fragment
      }
   }

   override fun getLayoutId(): Int = R.layout.layout_recyclerview

   override fun initFragment(savedInstanceState: Bundle?) {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override fun initPresenter(): FollowPresenter = FollowPresenter(this)

   override fun loadData() {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override fun setFollowInfo(issue: HomeBean.Issue) {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override fun showRrror(errorMsg: String, errorCode: Int) {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override fun showLoading() {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override fun dismissLoading() {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }
}