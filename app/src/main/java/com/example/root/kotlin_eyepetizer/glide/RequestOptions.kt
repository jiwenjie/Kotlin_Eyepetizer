package com.example.root.kotlin_eyepetizer.glide

import com.bumptech.glide.request.RequestOptions
import com.example.root.kotlin_eyepetizer.R

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/16
 *  desc:
 *  version:1.0
 */
object RequestOptions {

    fun getRequestOptions() : RequestOptions {
        return RequestOptions()
               .placeholder(R.drawable.placeholder_banner)
    }

}
















