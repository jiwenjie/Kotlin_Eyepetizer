package com.example.root.kotlin_eyepetizer.base

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/26
 *  desc:
 *  version:1.0
 */
interface IPresenter<in V: IBaseView> {

   fun attachView(mRootView: V)

   fun detachView()

}
