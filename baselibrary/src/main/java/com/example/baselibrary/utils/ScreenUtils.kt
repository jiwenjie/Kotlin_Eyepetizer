@file:Suppress("DEPRECATION")

package com.example.baselibrary.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.support.design.widget.TabLayout
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import java.lang.Exception

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/04
 *  desc:有关屏幕设置的 Utils
 *  version:1.0
 */
object ScreenUtils {

    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    fun getScreenDensity(context: Context): Float {
        return context.resources.displayMetrics.density
    }

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = getScreenDensity(context)
        return (dpValue * scale + 0.5f).toInt()
    }

    fun sp2px(context: Context, spValue: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.resources.displayMetrics).toInt()
    }

    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = getScreenDensity(context)
        return (pxValue / scale + 0.5f).toInt()
    }

    fun px2sp(context: Context, pxValue: Float): Float {
        return (pxValue / context.resources.displayMetrics.scaledDensity)
    }

    fun getScreenSize(context: Context): IntArray {
        val dm = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(dm)
        return intArrayOf(dm.widthPixels, dm.heightPixels)
    }

    fun getDpixel(context: Context, value: Int): Int {
        return (getScreenDensity(context) * value).toInt()
    }

    fun getStatusBarHeight(context: Context): Int {
        val resourceRes = context.resources
            .getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceRes > 0) context.resources.getDimensionPixelOffset(resourceRes) else 0
    }

    fun getStatusBarColor(context: Context, color: String) {
        val statusBarView = View(context)
        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(context))
        statusBarView.setBackgroundColor(Color.parseColor(color))
        val contentView = (context as Activity).findViewById<ViewGroup>(android.R.id.content)
        contentView.addView(statusBarView, lp)
    }

    fun reflexTablayout(tabLayout: TabLayout, padGap: Float) {
        tabLayout.post {
            try {
                val mTabStrip = tabLayout.getChildAt(0) as LinearLayout
                val dpGap = ScreenUtils.dip2px(tabLayout.context, padGap)
                for (i in 0 until mTabStrip.childCount) {
                    val tabView = mTabStrip.getChildAt(i)
                    val mTextViewField = tabView.javaClass.getDeclaredField("mTextView")
                    mTextViewField.isAccessible = true
                    val mTextView = mTextViewField.get(tabView) as TextView
                    tabView.setPadding(0, 0, 0, 0)
                    var width = mTextView.width
                    if (width == 0) {
                        mTextView.measure(0, 0)
                        width = mTextView.measuredWidth
                    }
                    val params = tabView.layoutParams as LinearLayout.LayoutParams
                    params.width = width
                    params.leftMargin = dpGap
                    params.rightMargin = dpGap
                    tabView.layoutParams = params
                    tabView.invalidate()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun snapShot(context: Context, withStatusBar: Boolean): Bitmap {
        val view = (context as Activity).window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bitmap = view.drawingCache
        val frame = Rect()
        context.window.decorView.getWindowVisibleDisplayFrame(frame)

        val bp = if (withStatusBar) Bitmap.createBitmap(bitmap, 0, 0, getScreenWidth(context), getScreenHeight(context))
        else Bitmap.createBitmap(bitmap, 0, frame.top, getScreenWidth(context), getScreenHeight(context))

        view.destroyDrawingCache()
        return bp
    }

    fun getActionBarSize(context: Context): Int {
        val tv = TypedValue()
        return if (context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true))
            TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
        else 0
    }
}
























