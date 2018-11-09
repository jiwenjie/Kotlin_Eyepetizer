package com.example.root.kotlin_eyepetizer.base

import android.view.View
import com.example.baselibrary.views.BaseMvpFragment
import com.example.baselibrary.views.BaseMvpPresenter
import com.example.multiple_status_view.MultipleStatusView

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/06
 *  desc:复写 Fragment 基类
 *  version:1.0
 */
abstract class BaseFragment<V, P> : BaseMvpFragment<IBaseView, BaseMvpPresenter<IBaseView>>() {

    /**
     * 多种状态的 View 切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        loadData()
    }

    /**
     * 加载数据
     */
    protected abstract fun loadData()

    override fun setListener() {
        super.setListener()
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

}






















