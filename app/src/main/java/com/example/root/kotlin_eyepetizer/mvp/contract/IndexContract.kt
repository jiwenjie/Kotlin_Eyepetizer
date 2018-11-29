package com.example.root.kotlin_eyepetizer.mvp.contract

import com.example.root.kotlin_eyepetizer.basic.base.IBaseView
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/28
 *  desc:首页的契约类部分
 *  version:1.0
 */
interface IndexContract {

    interface IndexModel {
        /**
         * Model 请求方法
         */
        fun requestHomeData(num: Int) : Observable<HomeBean>

        fun loadMord(url: String): Observable<HomeBean>
    }

    interface IndexView: IBaseView {
        /**
         * 设置第一次请求的数据
         */
        fun setHomeData(homeBean: HomeBean)

        /**
         * 设置加载更多的数据
         */
        fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>)

        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)
    }

    interface IndexPresenter {
        /**
         * 获取首页精选数据
         */
        fun requestHomeData(num: Int)

        /**
         * 加载更多数据
         */
        fun loadMoreData()
    }

}













