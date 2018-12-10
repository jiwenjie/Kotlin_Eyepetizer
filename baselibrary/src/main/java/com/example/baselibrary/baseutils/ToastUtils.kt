package com.example.baselibrary.baseutils

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.widget.Toast


object ToastUtils {
    private var mToast: Toast? = null

    @SuppressLint("ShowToast") // 加这一行目的是为了屏蔽可能会报的 lint 错误
    fun showToast(context: Context, msg: String, length: Int = Toast.LENGTH_SHORT) {    // 第三个参数设置了默认值
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, length)
        } else {
            mToast!!.setText(msg)
        }
        mToast!!.show()
    }

    @SuppressLint("ShowToast")  // 不加注释的话， Toast.makeText(context, msg, length) 会有警告
    fun showCenterToast(context: Context, msg: String, length: Int = Toast.LENGTH_SHORT) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, length)
        } else {
            mToast!!.setText(msg)
        }
        mToast!!.setGravity(Gravity.CENTER, 0, 0)
        mToast!!.show()
    }

    fun cancelToast() {
        if (mToast != null) {
            mToast!!.cancel()
            mToast = null
        }
    }
}

















