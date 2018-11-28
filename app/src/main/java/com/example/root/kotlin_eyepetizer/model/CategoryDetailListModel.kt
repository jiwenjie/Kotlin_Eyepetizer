package com.example.root.kotlin_eyepetizer.model

import com.example.baselibrary.RetrofitManager
import com.example.root.kotlin_eyepetizer.api.ApiService
import com.example.root.kotlin_eyepetizer.api.Constant
import com.example.root.kotlin_eyepetizer.bean.HomeBean
import com.example.root.kotlin_eyepetizer.contract.CategoryDetailContract
import com.example.root.kotlin_eyepetizer.utils.SchedulerUtils
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/26
 *  desc:分类的详情界面（该界面也是一个列表）
 *  version:1.0
 */
class CategoryDetailListModel : CategoryDetailContract.CategoryDetailModel {

    /**
     * 获取分类下的 List 数据
     */
    override fun getCategoryDetailList(id: Long): Observable<HomeBean.Issue> {
        return RetrofitManager.provideClient(Constant.BASE_URL)
            .create(ApiService::class.java)
            .getCategoryDetailList(id)
            .compose(SchedulerUtils.ioToMain())
    }

    override fun loadMoreData(url: String): Observable<HomeBean.Issue> {
        return RetrofitManager.provideClient(Constant.BASE_URL)
            .create(ApiService::class.java)
            .getIssueData(url)
            .compose(SchedulerUtils.ioToMain())
    }
}