package com.example.root.kotlin_eyepetizer.utils

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.TypedValue
import android.view.View

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/14
 *  desc: 有关头部状态栏等设置的工具类
 *  version:1.0
 */
class StatusBarUtil {

   companion object {
      private var DEFAULT_COLOR = 0
      private var DEFAULT_ALPHA = 0f//Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 0.2f : 0.3f;
      private val MIN_API = 19

      /* 增加 View 的 paddingTop，增加的值是状态栏的高度 (智能判断，并设置高度) */
      fun setPaddingSmart(context: Context, view: View) {
         if (Build.VERSION.SDK_INT >= MIN_API) {
            val lp = view.layoutParams
            if (lp != null && lp.height > 0) {
               lp.height += getStatusBarHeight(context)  // 增高
            }
            view.setPadding(view.paddingStart, view.paddingTop + getStatusBarHeight(context),
                    view.paddingEnd, view.paddingBottom)
         }
      }

      /* 获取状态栏高度 */
      fun getStatusBarHeight(context: Context): Int {
         var result = 24
         val resId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
         result = if (resId > 0) {
            context.resources.getDimensionPixelSize(resId)
         } else {
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    result.toFloat(), Resources.getSystem().displayMetrics).toInt()
         }
         return result
      }
   }
}


















