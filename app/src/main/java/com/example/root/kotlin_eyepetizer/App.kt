package com.example.root.kotlin_eyepetizer

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/07
 *  desc:
 *  version:1.0
 */
class App : Application() {
   override fun onCreate() {
      super.onCreate()
      INSTANCE = applicationContext
   }

   companion object {
      @SuppressLint("StaticFieldLeak")
      var INSTANCE: Context? = null
   }
}