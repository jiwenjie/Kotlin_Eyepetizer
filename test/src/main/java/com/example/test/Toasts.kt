package com.example.test

import android.content.Context
import android.widget.Toast

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/09
 *  desc:
 *  version:1.0
 */
object Toasts {

    private var mToast: Toast? = null

    fun showToast(context: Context, msg: String, length: Int = Toast.LENGTH_SHORT) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, length)
        } else {
            mToast!!.setText(msg)
        }

        mToast!!.show()
    }

}