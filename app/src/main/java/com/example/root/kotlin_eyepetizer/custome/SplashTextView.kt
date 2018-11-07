package com.example.root.kotlin_eyepetizer.custome

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/06
 *  desc: 引导页的字体（eye 下的字）
 *  version:1.0
 */
class SplashTextView : TextView {

   constructor(context: Context?): super(context)

   constructor(context: Context?, attrs: AttributeSet) : super(context, attrs)

   constructor(context: Context?, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle)

   override fun setTypeface(tf: Typeface?) {
      super.setTypeface(Typeface.createFromAsset(context.assets, "Lobster-1.4.otf"))
   }

}













