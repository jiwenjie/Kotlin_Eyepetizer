package com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_model

import com.example.baselibrary.RetrofitManager
import com.example.root.kotlin_eyepetizer.basic.api.Constant
import com.example.root.kotlin_eyepetizer.basic.api.ZhihuApi
import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_bean.ZhihuDailyListBean
import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_contract.ZhihuContract
import com.example.root.kotlin_eyepetizer.utils.SchedulerUtils
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/11
 *  desc:知乎请求数据的 model
 *  version:1.0
 */
class ZhihuModel : ZhihuContract.ZhihuModel {

    override fun getDailyList(): Observable<ZhihuDailyListBean> {
        return RetrofitManager.provideClient(Constant.ZHIHU_BASE_URL)
            .create(ZhihuApi::class.java)
            .getLastDailyList()
            .compose(SchedulerUtils.ioToMain())
    }

    override fun getDailyList(date: String): Observable<ZhihuDailyListBean> {
        return RetrofitManager.provideClient(Constant.ZHIHU_BASE_URL)
            .create(ZhihuApi::class.java)
            .getDailyListWithDate(date)
            .compose(SchedulerUtils.ioToMain())
    }
}








