package com.example.root.kotlin_eyepetizer.base

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/15
 *  desc: Presenter 基类
 *  version:1.0
 */
interface IBasePresenter<V: IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}












