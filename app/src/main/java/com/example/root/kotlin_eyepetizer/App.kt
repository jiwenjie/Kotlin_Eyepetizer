package com.example.root.kotlin_eyepetizer

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.squareup.leakcanary.RefWatcher

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/07
 *  desc:
 *  version:1.0
 */
class App : Application() {

   private var refWatcher: RefWatcher? = null

   override fun onCreate() {
      super.onCreate()
      INSTANCE = applicationContext
   }

   companion object {
      @SuppressLint("StaticFieldLeak")
      var INSTANCE: Context? = null

      fun getRefWatcher(context: Context): RefWatcher? {
         val myApplication = context.applicationContext as App
         return myApplication.refWatcher
      }
   }
}