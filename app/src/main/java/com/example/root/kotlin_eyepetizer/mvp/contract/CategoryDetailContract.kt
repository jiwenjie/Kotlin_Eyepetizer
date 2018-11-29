package com.example.root.kotlin_eyepetizer.mvp.contract

import com.example.root.kotlin_eyepetizer.basic.base.IBaseView
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/26
 *  desc:分类的详情 contract，是一个列表页面
 *  version:1.0
 */
interface CategoryDetailContract {

    /**
     * model
     */
    interface CategoryDetailModel {

        fun getCategoryDetailList(id: Long): Observable<HomeBean.Issue>

        fun loadMoreData(url: String): Observable<HomeBean.Issue>
    }

    interface CategoryDetailView: IBaseView {
        /**
         * view, 设置列表数据
         */
        fun setCateDetailList(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg: String)
    }

    interface CategoryDetailPresenter {
        /**
         * presenter，请求数据
         */
        fun getCategoryDetailList(id: Long)

        fun loadMoreData()
    }
}