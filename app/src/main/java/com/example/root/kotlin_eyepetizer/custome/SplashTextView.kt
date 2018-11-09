package com.example.root.kotlin_eyepetizer.custome

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/06
 *  desc: 引导页的字体 kotlin 实现,
 *  tips: 如果只写只有一个参数的构造器 会失效
 *  version:1.0
 */
class SplashTextView : TextView {

   constructor(context: Context) : super(context)

   constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

   constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

   /**
    * 注意路径问题
    */
   override fun setTypeface(tf: Typeface?) {
      super.setTypeface(Typeface.createFromAsset(context.assets, "fonts/Lobster-1.4.otf"))
   }
}













