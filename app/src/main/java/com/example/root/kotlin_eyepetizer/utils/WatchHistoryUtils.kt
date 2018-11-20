package com.example.root.kotlin_eyepetizer.utils

import android.content.Context
import android.content.SharedPreferences
import java.lang.reflect.Method


/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/20
 *  desc:kotlin 委托属性 + SharedPreference 实例(用来记录视频观看记录)
 *  version:1.0
 */

class WatchHistoryUtils {

    companion object {
        /**
         * 保存在手机里面的文件名
         */
        private val FILE_NAME = "kotlinMvp_file"

        /**
         * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
         */
        fun put(context: Context, key: String, `object`: Any) {
            val sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE)

            val editor = sp.edit()

            when (`object`) {
                is String -> editor.putString(key, `object`)
                is Int -> editor.putInt(key, `object`)
                is Boolean -> editor.putBoolean(key, `object`)
                is Float -> editor.putFloat(key, `object`)
                is Long -> editor.putLong(key, `object`)
                else -> editor.putString(key, `object`.toString())
            }
            SharedPreferencesCompat.apply(editor)
        }

        /**
         * 创建一个解决 SharedPreferencesCompat.apply 方法的一个兼容类
         */
        private object SharedPreferencesCompat {
            private val sApplyMethod = findApplyMethod()

            /**
             * 反射查找 apply 的方法
             */
            private fun findApplyMethod(): Method? {
                try {
                    val clz = SharedPreferences.Editor::class.java
                    return clz.getMethod("apply")
                } catch (e: NoSuchMethodException) {

                }
                return null
            }

            /**
             * 如果找到则使用 apply 执行，否则使用 commit
             */
            fun apply(editor: SharedPreferences.Editor) {
                try {
                    if (sApplyMethod != null) {
                        sApplyMethod.invoke(editor)
                        return
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                editor.commit()
            }
        }

        /***************文件名可变，用于存储各种历史记录**************/


        /**
         * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
         */
        fun put(fileName: String, context: Context, key: String, `object`: Any) {
            val sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE)
            val editor = sp.edit()

            when (`object`) {
                is String -> editor.putString(key, `object`)
                is Int -> editor.putInt(key, `object`)
                is Boolean -> editor.putBoolean(key, `object`)
                is Float -> editor.putFloat(key, `object`)
                is Long -> editor.putLong(key, `object`)
                else -> editor.putString(key, `object`.toString())
            }
            SharedPreferencesCompat.apply(editor)
        }

        /**
         * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
         */

    }

}





















