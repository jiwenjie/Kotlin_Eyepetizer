package com.example.root.kotlin_eyepetizer.mvp.presenter

import com.example.baselibrary.utils.ExceptionHandle
import com.example.baselibrary.views.BaseMvpPresenter
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import com.example.root.kotlin_eyepetizer.mvp.contract.IndexContract
import com.example.root.kotlin_eyepetizer.mvp.model.IndexModel

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/28
 *  desc:首页的 Presenter
 *  tips: (数据市 Banner 数据和 一页（15 or 20 个数据）组合而成的 HomeBean, 需要查看接口分析)
 *  version:1.0
 */
class IndexPresenter(view: IndexContract.IndexView) : BaseMvpPresenter<IndexContract.IndexView>(view),
    IndexContract.IndexPresenter {

    private var bannerHomeBean: HomeBean? = null
    private val mModel by lazy { IndexModel() }
    private var nextPageUrl: String? = null     //加载首页的 Banner 数据 + 一页的网络请求数据合并后，nextPageUrl 没 add

    /**
     * 获取首页精选数据 banner + 一次请求的数据列表
     */
    override fun requestHomeData(num: Int) {
        mView.showLoading()
        addSubscription(
            mModel.requestHomeData(num)
                .flatMap { homeBean ->
                    //过滤掉 Banner2 (包含广告等不需要的 Type 类型)，具体需要查看接口数据分析
                    val bannerItemList = homeBean.issueList[0].itemList
                    bannerItemList.filter {
                        it.type == "banner2" || it.type == "horizontalScrollCard"
                    }.forEach {
                        // 移除
                        bannerItemList.remove(it)
                    }

                    bannerHomeBean = homeBean     //记录第一页市当作 banner 数据

                    //根据 nextPageUrl 请求下一页数据
                    mModel.loadMord(homeBean.nextPageUrl)
                }
                .subscribe({
                    mView.apply {
                        dismissLoading()
                        nextPageUrl = it.nextPageUrl
                        //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                        val newBannerItemList = it.issueList[0].itemList

                        newBannerItemList.filter { item ->
                            item.type == "banner2" || item.type == "horizontalScrollCard"
                        }.forEach { item ->
                            //移除 item
                            newBannerItemList.remove(item)
                        }
                        // 重新赋值 Banner 长度
                        bannerHomeBean!!.issueList[0].count = bannerHomeBean!!.issueList[0].itemList.size

                        // 赋值过滤后的数据 + banner 数据
                        bannerHomeBean?.issueList!![0].itemList.addAll(newBannerItemList)

                        setHomeData(bannerHomeBean!!)
                    }
                }, {
                    mView.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                    }
                })
        )
    }

    /**
     * 加载更多
     */
    override fun loadMoreData() {
        addSubscription(nextPageUrl.let {
            mModel.loadMord(it!!)
                .subscribe({1
                    
                }, {

                })
        })
    }
}























