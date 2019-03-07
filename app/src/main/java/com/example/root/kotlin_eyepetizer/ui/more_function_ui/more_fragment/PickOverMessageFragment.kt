package com.example.root.kotlin_eyepetizer.ui.more_function_ui.more_fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.baseadapters.BaseFragmentPagerAdapter
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_pick_over_message.*
import java.util.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/10
 *  desc: 各种文章精选的 Fragment
 *  version:1.0
 */
class PickOverMessageFragment : Fragment() {

   private val mList by lazy { ArrayList<Fragment>() }

//   private val zhihuFragment by lazy { ZhihuFragment() }
//   private val weixinFragment by lazy { WeixinFragment() }
//   private val wangyiFragment by lazy { WangyiFragment() }

   companion object {
      fun getInstance(): PickOverMessageFragment {
         val fragment = PickOverMessageFragment()
         val bundle = Bundle()
         fragment.arguments = bundle
         return fragment
      }
   }

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      return LayoutInflater.from(activity!!).inflate(R.layout.fragment_pick_over_message, container, false)
   }

   override fun onActivityCreated(savedInstanceState: Bundle?) {
      super.onActivityCreated(savedInstanceState)
      initView()
   }

   private fun initView() {
      activity?.let { StatusBarUtil.darkMode(it) }
      activity?.let { StatusBarUtil.setPaddingSmart(it, pick_over_toolbar) }

      mList.add(ZhihuFragment.getInstance())
      mList.add(WangyiFragment.getInstance())
      mList.add(WeixinFragment.getInstance())

      pick_over_viewPager.adapter = BaseFragmentPagerAdapter(childFragmentManager, mList, arrayOf("知乎日报", "网易新闻", "微信精选"))
      pick_over_tabLayout.setupWithViewPager(pick_over_viewPager)
   }
}





