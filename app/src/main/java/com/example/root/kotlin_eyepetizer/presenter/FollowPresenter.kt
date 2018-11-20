package com.example.root.kotlin_eyepetizer.presenter

import android.annotation.SuppressLint
import com.example.baselibrary.utils.ExceptionHandle
import com.example.baselibrary.views.BaseMvpPresenter
import com.example.root.kotlin_eyepetizer.contract.FollowContract
import com.example.root.kotlin_eyepetizer.model.FollowModel

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/15
 *  desc: 关注页面的 Presenter
 *  version:1.0
 */
class FollowPresenter(mView: FollowContract.FollowView) : BaseMvpPresenter<FollowContract.FollowView>(mView), FollowContract.FollowPresenter {


    private var mFollowModel: FollowModel? = null
    private var nextPageUrl: String? = null

    init {
        this.mFollowModel = FollowModel()
    }

    /**
     * 构造不添加 Model 实例
     * 需要在 View 中初始化 Presenter，如果添加 Model 实例，则 View Model 耦合
     * view 当前对应的 Activity 或者 Fragment
     */

    @SuppressLint("CheckResult")
    override fun requestFollowList() {
        mView?.showLoading()
        mFollowModel?.requestFollowList()
            ?.subscribe({
                mView?.apply {
                    dismissLoading()
                    nextPageUrl = it.nextPageUrl
                    setFollowInfo(it)
                }
            }, {
                mView?.apply {
                    // 处理异常
                    showRrror(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                }
            })
    }

    override fun loadMore() {
        nextPageUrl?.let {
            mFollowModel?.loadMoreData(it)
                ?.subscribe({
                    mView?.apply {
                        nextPageUrl = it.nextPageUrl
                        setFollowInfo(it)
                    }
                }, {
                    mView?.apply {
                        showRrror(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                    }
                })
        }
    }
}















