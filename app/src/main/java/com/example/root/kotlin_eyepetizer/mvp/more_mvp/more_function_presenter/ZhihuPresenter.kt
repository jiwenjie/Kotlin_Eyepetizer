package com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_presenter

import android.text.TextUtils
import com.example.baselibrary.baseutils.ExceptionHandle
import com.example.baselibrary.views.BaseMvpPresenter
import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_contract.ZhihuContract
import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_model.ZhihuModel

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/11
 *  desc:知乎的 presenter
 *  version:1.0
 */
class ZhihuPresenter(view: ZhihuContract.ZhihuView)
    : BaseMvpPresenter<ZhihuContract.ZhihuView>(view), ZhihuContract.ZhihuPresenter {

    private val mModel by lazy { ZhihuModel() }

    /**
     * 日报日期
     */
    private var mDate: String? = null

    override fun loadLatestList() {
        mView.showLoading()
        addSubscription(mModel.getDailyList()
            .subscribe({
                mView.dismissLoading()
                mDate = it?.date
                mView.updateContentList(it.stories)
            }, {
                mView?.showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
            }))
    }

    override fun loadMoreList() {

        addSubscription(
            mDate.let {
                mModel.getDailyList(mDate!!)
                    .subscribe({ bean ->
                        mView.apply {
                            if (TextUtils.equals(mDate, bean.date))
                                return@subscribe
                            mDate = bean.date
                            mView.updateContentList(bean.stories)
                        }
                    }, { e ->
                        mView?.showError(ExceptionHandle.handleException(e), ExceptionHandle.errorCode)
                    })
            })


//        addSubscription(mModel.getDailyList(mDate!!)
//            .subscribe({
//                if (TextUtils.equals(mDate, it.date))
//                    return@subscribe
//                mDate = it.date
//                mView.updateContentList(it.stories)
//            }, {
//                mView?.showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
//            }))
    }
}



















