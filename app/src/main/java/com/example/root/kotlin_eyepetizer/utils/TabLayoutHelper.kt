package com.example.root.kotlin_eyepetizer.utils

import android.annotation.SuppressLint
import android.os.Build
import android.support.design.widget.TabLayout
import android.widget.LinearLayout
import com.example.baselibrary.utils.ScreenUtils
import com.example.root.kotlin_eyepetizer.App
import java.lang.reflect.Field

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/14
 *  desc: TabLayout 的帮助类
 *  version:1.0
 */
object TabLayoutHelper {

   @SuppressLint("ObsoleteSdkInt")
   fun setUpIndicatorWidth(tabLayout: TabLayout) {
      val tabLayoutClass = tabLayout.javaClass
      var tabStrip: Field? = null
      try {
         tabStrip = tabLayoutClass.getDeclaredField("mTabStrip")
         tabStrip!!.isAccessible = true
      } catch (e: Exception) {
         e.printStackTrace()
      }

      var layout: LinearLayout? = null
      try {
         if (tabStrip != null) {
            layout = tabStrip.get(tabLayout) as LinearLayout
         }
         for (i in 0 until layout!!.childCount) {
            val child = layout.getChildAt(i)
            child.setPadding(0, 0, 0, 0)
            val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
               params.marginStart = ScreenUtils.dip2px(App.INSTANCE!!.applicationContext, 50f)
               params.marginEnd = ScreenUtils.dip2px(App.INSTANCE!!.applicationContext, 50f)
            }
            child.layoutParams = params
            child.invalidate()
         }
      } catch (e: IllegalAccessException) {
         e.printStackTrace()
      }
   }

}












