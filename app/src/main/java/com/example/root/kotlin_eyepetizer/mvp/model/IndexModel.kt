package com.example.root.kotlin_eyepetizer.mvp.model

import com.example.baselibrary.RetrofitManager
import com.example.root.kotlin_eyepetizer.basic.api.ApiService
import com.example.root.kotlin_eyepetizer.basic.api.Constant
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import com.example.root.kotlin_eyepetizer.mvp.contract.IndexContract
import com.example.root.kotlin_eyepetizer.utils.SchedulerUtils
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/28
 *  desc:首页的 model
 *  version:1.0
 */
class IndexModel : IndexContract.IndexModel {

    override fun requestHomeData(num: Int): Observable<HomeBean> {
        return RetrofitManager.provideClient(Constant.EYE_BASE_URL)
            .create(ApiService::class.java)
            .getFirstIndexData(num)
            .compose(SchedulerUtils.ioToMain())
    }

    override fun loadMord(url: String): Observable<HomeBean> {
        return RetrofitManager.provideClient(Constant.EYE_BASE_URL)
            .create(ApiService::class.java)
            .getMoreIndexData(url)
            .compose(SchedulerUtils.ioToMain())
    }
}