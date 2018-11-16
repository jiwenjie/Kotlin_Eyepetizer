package com.example.root.kotlin_eyepetizer.utils

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/15
 *  desc:
 *  version:1.0
 */
object SchedulerUtils {

   fun <T> ioToMain(): IoMainScheduler<T> {
      return IoMainScheduler()
   }

}


















