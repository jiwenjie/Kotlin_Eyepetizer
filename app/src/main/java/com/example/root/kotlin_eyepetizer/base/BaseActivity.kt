package com.example.root.kotlin_eyepetizer.base

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.baselibrary.views.BaseMvpActivity
import com.example.baselibrary.views.BaseMvpPresenter
import com.example.multiple_status_view.MultipleStatusView

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/06
 *  desc: 复写 Activity 基类
 *  version:1.0
 */
abstract class BaseActivity : BaseMvpActivity<IBaseView, BaseMvpPresenter<IBaseView>>() {
    /**
     * 多种状态的 View 的切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        loadData()
    }

    override fun setListener() {
        super.setListener()
        mLayoutStatusView?.setOnRetryClickListener(mRetryClickListener)
    }

    /**
     * 请求数据
     */
    protected abstract fun loadData()

    /**
     * 打开软键盘
     */
    fun openKeyBoard(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * 关闭软键盘
     */
    fun closeKeyBoard(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }
}













