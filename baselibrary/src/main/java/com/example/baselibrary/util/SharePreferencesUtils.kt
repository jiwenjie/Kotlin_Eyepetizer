@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.example.baselibrary.util

import android.content.Context
import androidx.core.content.edit

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/04
 *  desc:
 *  version:1.0
 */
object SharePreferencesUtils {
    private const val SHARED_PREFERENCES_NAME = "share_preferences"

    fun saveString(context: Context, key: String, value: String) {
        val sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        sp.edit {
            putString(key, value)
        }
    }

    fun getString(context: Context, key: String, default: String = ""): String {
        val sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sp.getString(key, default)
    }

    fun saveInteger(context: Context, key: String, value: Int) {
        val sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        sp.edit {
            putInt(key, value)
        }
    }

    fun getInteger(context: Context, key: String, default: Int = 0): Int {
        val sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sp.getInt(key, default)
    }

    fun saveLong(context: Context, key: String, value: Long) {
        val sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        sp.edit {
            putLong(key, value)
        }
    }

    fun getLong(context: Context, key: String, default: Long = 0L): Long {
        val sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sp.getLong(key, default)
    }

    fun saveFloat(context: Context, key: String, value: Float) {
        val sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        sp.edit {
            putFloat(key, value)
        }
    }

    fun getFloat(context: Context, key: String, default: Float = 0F): Float {
        val sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sp.getFloat(key, default)
    }

    fun saveBoolean(context: Context, key: String, value: Boolean) {
        val sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        sp.edit {
            putBoolean(key, value)
        }
    }

    fun getBoolean(context: Context, key: String, default: Boolean = false): Boolean {
        val sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sp.getBoolean(key, default)
    }

    fun saveStringSet(context: Context, key: String, value: Set<String>) {
        val sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        sp.edit {
            putStringSet(key, value)
        }
    }

    fun getStringSet(context: Context, key: String): Set<String> {
        val sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sp.getStringSet(key, null)
    }
}















