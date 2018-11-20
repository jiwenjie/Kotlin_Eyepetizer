package com.example.root.kotlin_eyepetizer.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.baselibrary.utils.ToastUtils
import com.example.baselibrary.views.BaseMvpFragment
import com.example.baselibrary.views.BaseMvpPresenter
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.activity.AboutActivity
import com.example.root.kotlin_eyepetizer.activity.ProfileHomePageActivity
import com.example.root.kotlin_eyepetizer.base.IBaseView
import com.example.root.kotlin_eyepetizer.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/07
 *  desc:
 *  version:1.0
 */
class MineFragment : BaseMvpFragment<IBaseView, BaseMvpPresenter<IBaseView>>(), View.OnClickListener {

   private var mTitle: String? = null

   companion object {
      fun getInstance(title: String): MineFragment {
         val fragment = MineFragment()
         val bundle = Bundle()
         fragment.arguments = bundle
         fragment.mTitle = title
         return fragment
      }
   }

   /**
    * 其他 fragment 中的该部分是请求数据但是 Mine 部分不需要网络请求（初始状态），
    * 所以此处不写加载数据的代码
    *
    */
   override fun loadData() {
      mine_iv_about.setOnClickListener(this)
      mine_tv_view_homepage.setOnClickListener(this)
      mine_tv_collection.setOnClickListener(this)
      mine_tv_comment.setOnClickListener(this)
      // 页面下方的操作监听
      tv_mine_message.setOnClickListener(this)
      tv_mine_attention.setOnClickListener(this)
      tv_mine_cache.setOnClickListener(this)
      tv_watch_history.setOnClickListener(this)
      tv_feedback.setOnClickListener(this)
   }

   override fun getLayoutId(): Int {
      return R.layout.fragment_mine
   }

   override fun initFragment(savedInstanceState: Bundle?) {
      //状态栏透明和间距处理
      activity?.let { StatusBarUtil.darkMode(it) }
      activity?.let { StatusBarUtil.setPaddingSmart(it, mine_toolBar) }
   }

   override fun initPresenter(): BaseMvpPresenter<IBaseView>? {
      return null
   }

   override fun onClick(v: View?) {
      when {
         v?.id == R.id.mine_iv_avatar || v?.id == R.id.mine_tv_view_homepage -> {
            val intent = Intent(activity, ProfileHomePageActivity::class.java)
            startActivity(intent)
         }
         v?.id == R.id.mine_iv_about -> {
            val intent = Intent(activity, AboutActivity::class.java)
            startActivity(intent)
         }
         v?.id == R.id.mine_tv_collection -> ToastUtils.showToast(activity!!, "收藏")
         v?.id == R.id.mine_tv_comment -> ToastUtils.showToast(activity!!, "评论")
         v?.id == R.id.tv_mine_message -> ToastUtils.showToast(activity!!, "我的消息")
         v?.id == R.id.tv_mine_attention -> ToastUtils.showToast(activity!!, "我的关注")
         v?.id == R.id.tv_mine_cache -> ToastUtils.showToast(activity!!, "我的缓存")

         v?.id == R.id.tv_watch_history -> startActivity(Intent())
         v?.id == R.id.tv_feedback -> ToastUtils.showToast(activity!!, "意见反馈")
      }
   }
}










