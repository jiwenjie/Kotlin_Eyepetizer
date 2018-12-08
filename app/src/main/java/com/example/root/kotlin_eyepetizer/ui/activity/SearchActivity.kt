package com.example.root.kotlin_eyepetizer.ui.activity

import android.annotation.TargetApi
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import com.example.baselibrary.utils.ErrorStatus
import com.example.baselibrary.utils.GeneralUtils
import com.example.baselibrary.utils.ToastUtils
import com.example.baselibrary.views.BaseMvpActivity
import com.example.root.kotlin_eyepetizer.App
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import com.example.root.kotlin_eyepetizer.mvp.contract.SearchContract
import com.example.root.kotlin_eyepetizer.mvp.presenter.SearchPresenter
import com.example.root.kotlin_eyepetizer.ui.adapter.CategoryDetailAdapter
import com.example.root.kotlin_eyepetizer.ui.adapter.HotKeywordsAdapter
import com.example.root.kotlin_eyepetizer.utils.StatusBarUtil
import com.example.root.kotlin_eyepetizer.utils.ViewAnimUtils
import com.google.android.flexbox.*
import kotlinx.android.synthetic.main.activity_search.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/02
 *  desc:搜索活動
 *  version:1.0
 */

class SearchActivity : BaseMvpActivity<SearchContract.SearchView, SearchPresenter>(),
        SearchContract.SearchView {

   private var itemList =  ArrayList<HomeBean.Issue.Item>()
   private val adapter by lazy { CategoryDetailAdapter(this, itemList) }

   private var mHotWordAdapter: HotKeywordsAdapter? = null

   private var mTextTypeface: Typeface? = null
   private var keyWords: String? = null

   /**
    * 是否加载更多
    */
   private var loadingMore = false

   init {
      // 细黑简体字体
      mTextTypeface = Typeface.createFromAsset(App.INSTANCE?.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
   }

   override fun initActivity(savedInstanceState: Bundle?) {
      enterAndExitAnim()   // 设置进出场的动画
      // 状态栏的透明和间距处理
      StatusBarUtil.darkMode(this)
      StatusBarUtil.setPaddingSmart(this, toolbar)
      tv_title_tip.typeface = mTextTypeface
      tv_hot_search_words.typeface = mTextTypeface
      // 初始化查询结果的 RecyclerView
      mRecyclerView_result.layoutManager = LinearLayoutManager(this)
      mRecyclerView_result.adapter = adapter

      // 实现滑动页面的自动加载
      mRecyclerView_result.addOnScrollListener(object : RecyclerView.OnScrollListener() {
         override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            val itemCount = mRecyclerView_result.layoutManager?.itemCount
            val lastVisibleItem = (mRecyclerView_result.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            if (!loadingMore && lastVisibleItem == (itemCount!! - 1)) {
               loadingMore = true
               mPresenter.loadMoreData()
            }
         }
      })

      // 取消
      tv_cancel.setOnClickListener {
         onBackPressed()
      }
      // 键盘的搜索按钮 (EditText 的 几种 imeAction 设置使用，此处判断是否是搜索)
      et_search_view.setOnEditorActionListener { v, actionId, event ->
         if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            closeSoftKeyboard()
            keyWords = et_search_view.text.toString()
            if (keyWords.isNullOrEmpty()) {
               ToastUtils.showToast(applicationContext!!, "请输入您感兴趣的关键词")
            } else {
               mPresenter.querySearchData(keyWords!!)
            }
         }
         false
      }
      mLayoutStatusView = multipleStatusView
   }

   /**
    * 进出场的动画切换
    */
   private fun enterAndExitAnim() {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         setUpEnterAnimation() // 入场动画
         setUpExitAnimation() // 退场动画
      } else {
         setUpView()
      }
   }

   /**
    * 进场动画
    */
   @TargetApi(Build.VERSION_CODES.LOLLIPOP)
   private fun setUpEnterAnimation() {
      val transition = TransitionInflater.from(this)
              .inflateTransition(R.transition.arc_motion)
      window.sharedElementEnterTransition = transition
      transition.addListener(object : Transition.TransitionListener {
         override fun onTransitionEnd(transition: Transition?) {
            transition?.removeListener(this)
            animateRevealShow()
         }

         override fun onTransitionResume(transition: Transition?) {

         }

         override fun onTransitionPause(transition: Transition?) {

         }

         override fun onTransitionCancel(transition: Transition?) {

         }

         override fun onTransitionStart(transition: Transition?) {

         }
      })
   }

   /**
    * 展示动画
    */
   @TargetApi(Build.VERSION_CODES.LOLLIPOP)
   private fun animateRevealShow() {
      ViewAnimUtils.animateRevealShow(
              this, rel_frame,
              fab_circle.width / 2, R.color.backgroundColor,
              object : ViewAnimUtils.OnRevealAnimationListener {
                 override fun onRevealHide() {

                 }

                 override fun onRevealShow() {
                    setUpView()
                 }
              }
      )
   }

   /**
    * 退场动画
    */
   @TargetApi(Build.VERSION_CODES.LOLLIPOP)
   private fun setUpExitAnimation() {
      val fade = Fade()
      window.returnTransition = fade
      fade.duration = 300
   }

   /**
    * 5.0 以下的界面切换动画
    */
   private fun setUpView() {
      val animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
      animation.duration = 300
      rel_container.startAnimation(animation)
      rel_container.visibility = View.VISIBLE
      // 打开软键盘
      GeneralUtils.showSoftInput(applicationContext, et_search_view)
   }

   override fun closeSoftKeyboard() = GeneralUtils.hideKeyboard(applicationContext, et_search_view)

   override fun loadData() {
      //请求热门关键词
      mPresenter.requestHotWordData()
   }

   /**
    * 设置热门关键词
    */
   override fun setHotWordData(string: ArrayList<String>) {
      showHotWordView()
      mLayoutStatusView.showContent()

      mHotWordAdapter = HotKeywordsAdapter(this, string)

      val flexBoxLayoutManager = FlexboxLayoutManager(this)
      flexBoxLayoutManager.flexWrap = FlexWrap.WRAP      // 按正常方向换行
      flexBoxLayoutManager.flexDirection = FlexDirection.ROW   // 主轴为水平方向，起点在左端
      flexBoxLayoutManager.alignItems = AlignItems.CENTER      // 定义项目在副轴轴上如何对齐
      flexBoxLayoutManager.justifyContent = JustifyContent.FLEX_START   // 多个轴对其方式

      mRecyclerView_hot.layoutManager = flexBoxLayoutManager
      mRecyclerView_hot.adapter = mHotWordAdapter
      // 设置 Tag 的点击事件, (注意该点击事件在适配器中的写法，该种写法可以更方便的使用 lambda)
      mHotWordAdapter?.setOnTagItemClickListener { tag ->
         closeSoftKeyboard()
         keyWords = tag
         mPresenter.querySearchData(tag)
      }
   }

   /**
    * 搜索结果设置
    */
   override fun setSearchResult(issue: HomeBean.Issue) {
      loadingMore = false
      hideHotWordView()

      tv_search_count.visibility = View.VISIBLE
      tv_search_count.text = String.format(resources.getString(R.string.search_result_count), keyWords, issue.total)

      adapter.addAllData(issue.itemList)
   }

   /**
    * 没有找到相匹配的内容
    */
   override fun setEmptyView() {
      ToastUtils.showToast(applicationContext!!, "抱歉，没有找到相匹配的内容")
      hideHotWordView()
      tv_search_count.visibility = View.GONE
      multipleStatusView?.showEmpty()
   }

   /**
    * 隐藏热门关键字的 View
    */
   private fun hideHotWordView() {
      layout_hot_words.visibility = View.GONE
      layout_content_result.visibility = View.VISIBLE
   }

   /**
    * 显示热门关键字的 流式布局
    */
   private fun showHotWordView() {
      layout_hot_words.visibility = View.VISIBLE
      layout_content_result.visibility = View.GONE
   }

   override fun showError(errorMsg: String, errorCode: Int) {
      ToastUtils.showToast(applicationContext!!, errorMsg)
      if (errorCode == ErrorStatus.NETWORK_ERROR) {
         multipleStatusView?.showNoNetwork()
      } else {
         multipleStatusView?.showContent()
      }
   }

   override fun showLoading() {
      multipleStatusView?.showLoading()
   }

   override fun dismissLoading() {
      multipleStatusView?.showContent()
   }

   override fun getLayoutId(): Int = R.layout.activity_search

   override fun initPresenter(): SearchPresenter = SearchPresenter(this)

   override fun onBackPressed() {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         ViewAnimUtils.animateRevealHide(
                 this, rel_frame,
                 fab_circle.width / 2, R.color.backgroundColor,
                 object : ViewAnimUtils.OnRevealAnimationListener {
                    override fun onRevealHide() {
                       defaultBackPressed()
                    }

                    override fun onRevealShow() {

                    }
                 })
      } else {
         defaultBackPressed()
      }
   }

   /**
    * 默认回退
    */
   private fun defaultBackPressed() {
      closeSoftKeyboard()
      super.onBackPressed()
   }

   override fun onDestroy() {
      super.onDestroy()
      mTextTypeface = null
   }
}