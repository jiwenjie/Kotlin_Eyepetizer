package com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_contract

import com.example.root.kotlin_eyepetizer.basic.base.IBaseView
import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_bean.ZhihuDailyItemBean
import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_bean.ZhihuDailyListBean
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/11
 *  desc:知乎的契约类
 *  version:1.0
 */
interface ZhihuContract {

    /**
     * 知乎的 model 接口
     */
    interface ZhihuModel {
        /**
         * 获取日报的 list
         */
        fun getDailyList(): Observable<ZhihuDailyListBean>

        /**
         * 根据日期获取日报 list --> 20181211
         */
        fun getDailyList(date: String): Observable<ZhihuDailyListBean>
    }

    /**
     * 知乎的 View
     */
    interface ZhihuView: IBaseView {
        /**
         * 展示数据
         */
        fun updateContentList(ItemList: ArrayList<ZhihuDailyItemBean>)

        /**
         * 显示错误信息
         */
        fun showError(errorMsg: String, errorCode: Int)
    }

    /**
     * 知乎的 Presenter
     */
    interface ZhihuPresenter {
        /**
         * 加载最新数据
         */
        fun loadLatestList()

        /**
         * 加载更多数据
         */
        fun loadMoreList()
    }
}


