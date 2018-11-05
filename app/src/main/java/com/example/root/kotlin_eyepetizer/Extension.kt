@file:Suppress("DEPRECATION")

package com.example.root.kotlin_eyepetizer

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/05
 *  desc: 扩展函数的位置
 *  version:1.0
 */
const val TAG = "Kotlin_Eyepetizer"


fun <T> Observable<T>.ioMain(): Observable<T> {
   return subscribeOn(Schedulers.io())
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
}

fun View.durationFormat(duration: Long?): String {
   val minute = duration!! / 60
   val second = duration % 60
   if (minute <= 9) {
      if (second <= 9) {
         return "0${minute}' 0${second}''"
      } else {
         return "0${minute}' ${second}''"
      }
   } else {
      if (second <= 9) {
         return "${minute}' 0${second}''"
      } else {
         return "${minute}' ${second}''"
      }
   }
}

fun View.timeFormat(time: Long): String {
   val date = Date()
   val timeCalendar = Calendar.getInstance()
   timeCalendar.time = date

   val today = Calendar.getInstance()
   val todayDate = Date(System.currentTimeMillis())
   today.time = todayDate

   if (timeCalendar.get(Calendar.YEAR) === today.get(Calendar.YEAR)) {
      val diffyDay = timeCalendar.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR)

      if (diffyDay == 0) {
         // 表示是 今天
         val hours = timeCalendar.get(Calendar.HOUR_OF_DAY)
         val minutes = timeCalendar.get(Calendar.MINUTE)
         return "${if (hours < 10) "0" + hours else hours}:${if (minutes < 10) "0" + minutes else minutes}"
      }
   }
   val year = timeCalendar.get(Calendar.YEAR)
   val month = timeCalendar.get(Calendar.MONTH)
   val day = timeCalendar.get(Calendar.DAY_OF_MONTH)
   return "${year} / ${if (month < 10) "0" + month else month} / ${if (day < 10) "0" + day else day}"
}

/**
 * 几天前，几小时前
 */
fun View.timePreFormat(time: Long): String {

   val now = System.currentTimeMillis()
   val pre = now - time // 多久前

   val min = pre / 1000 / 60
   if (min < 1) {
      return "刚刚"
   } else if (min < 60) {
      return "" + min + "分钟前"
   } else if (min < 60 * 24) {
      return "" + min / 60 + "小时前"
   } else {
      return "" + min / 60 / 24 + "天前"
   }

}

fun Context.dataFormat(total: Long): String {
   var result = ""
   var speedReal = 0
   speedReal = (total / (1024)).toInt()
   if (speedReal < 512) {
      result = speedReal.toString() + " KB"
   } else {
      val mSpeed = speedReal / 1024.0
      result = (Math.round(mSpeed * 100) / 100.0).toString() + " MB"
   }
   return result
}

/**
 * 1 表示 wifi
 */
fun Context.getNetType(): Int {
   val connectService = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
   val activityNetworkInfo = connectService.activeNetworkInfo

   if (activityNetworkInfo == null || !activityNetworkInfo.isAvailable) {
      return 0
   } else {
      // NetworkInfo 不为 null 开始判断是网络类型
      val netType = activityNetworkInfo.type
      if (netType == ConnectivityManager.TYPE_WIFI) {
         // wifi net 处理
         return 1
      }
   }
   return 0
}





















