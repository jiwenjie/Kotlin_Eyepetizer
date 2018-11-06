package com.example.root.kotlin_eyepetizer.base

import com.example.baselibrary.views.BaseMvpViewImpl

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/06
 *  desc:
 *  version:1.0
 */
interface IBaseView : BaseMvpViewImpl {
    fun showLoading()

    fun dismissLoading()
}






