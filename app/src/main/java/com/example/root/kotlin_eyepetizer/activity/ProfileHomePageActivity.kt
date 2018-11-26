package com.example.root.kotlin_eyepetizer.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.api.UriConstant
import com.example.root.kotlin_eyepetizer.utils.StatusBarUtil
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener
import com.scwang.smartrefresh.layout.util.DensityUtil
import kotlinx.android.synthetic.main.activity_profile_homepage.*
import java.util.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/18
 *  desc:
 *  version:1.0
 */
@Suppress("OverridingDeprecatedMember")
class ProfileHomePageActivity : AppCompatActivity() {

   private var mOffset = 0
   private var mScrollY = 0

   @SuppressLint("SetJavaScriptEnabled")
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_profile_homepage)

      //状态栏透明和间距处理
      StatusBarUtil.darkMode(this)
      StatusBarUtil.setPaddingSmart(this, toolbar)

      refreshLayout.setOnMultiPurposeListener(object : SimpleMultiPurposeListener() {
         override fun onHeaderPulling(header: RefreshHeader?, percent: Float, offset: Int, headerHeight: Int, extendHeight: Int) {
            mOffset = offset / 2
            parallax.translationY = (mOffset - mScrollY).toFloat()
            toolbar.alpha = 1 - Math.min(percent, 1f)
         }

         override fun onHeaderReleasing(header: RefreshHeader?, percent: Float, offset: Int, footerHeight: Int, extendHeight: Int) {
            mOffset = offset / 2
            parallax.translationY = (mOffset - mScrollY).toFloat()
            toolbar.alpha = 1 - Math.max(percent,  1f)
         }

         /** 刷新完成后 ToolBar 颜色没有显示透明，未解决 **/
      })

      homePage_ScrollView.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener {
         private var lastScrollY = 0
         private val h = DensityUtil.dp2px(170f)
         private val color = ContextCompat.getColor(applicationContext, R.color.colorPrimary)
         override fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
            var tScrollY = scrollY
            if (lastScrollY < h) {
               tScrollY = Math.min(h, tScrollY)
               mScrollY = if (tScrollY > h) h else tScrollY
               toolbar.setBackgroundColor(255 * mScrollY / h shl 24 or color)
               parallax.translationY = (mOffset - mScrollY).toFloat()
               /** 这里的 ToolBar 颜色没有显示透明，未解决 **/
            }
            lastScrollY = tScrollY
         }
      })

      toolbar.setNavigationOnClickListener {
         finish()
      }

      /** 刷新设置 **/
      refreshLayout.setOnRefreshListener {
         mWebView.loadUrl(UriConstant.GitHub_URL)
      }
      refreshLayout.autoRefresh()

      mWebView.settings.javaScriptEnabled = true
      mWebView.webViewClient = object : WebViewClient() {
         override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view!!.loadUrl(url)
            return true
         }

         override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            refreshLayout.finishRefresh()
            view!!.loadUrl(String.format(Locale.CHINA, "javascript:document.body.style.paddingTop='%fpx'; void 0", DensityUtil.px2dp(mWebView.paddingTop)))
         }
      }
   }
}