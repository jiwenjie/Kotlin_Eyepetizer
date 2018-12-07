package com.example.root.kotlin_eyepetizer.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.baselibrary.utils.ErrorStatus
import com.example.baselibrary.utils.ToastUtils
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.basic.base.BaseAppMvpFragment
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import com.example.root.kotlin_eyepetizer.mvp.contract.RankContract
import com.example.root.kotlin_eyepetizer.mvp.presenter.RankPresenter
import com.example.root.kotlin_eyepetizer.ui.adapter.CategoryDetailAdapter
import kotlinx.android.synthetic.main.fragment_rank.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/21
 *  desc: 热门的列表数据显示 fragment
 *  version:1.0
 */

class RankFragment : BaseAppMvpFragment(), RankContract.View {

    private val mPresenter by lazy { RankPresenter() }
    private val mAdapter by lazy { activity?.let { CategoryDetailAdapter(it, itemList) } }

    private var itemList = ArrayList<HomeBean.Issue.Item>()
    private var apiUrl: String? = null

    companion object {
        fun getInstance(apiUrl: String): RankFragment {
            val fragment = RankFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.apiUrl = apiUrl
            return fragment
        }
    }

    init {
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_rank

    override fun initView() {
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mAdapter

        mLayoutStatusView = multipleStatusView
    }

    override fun lazyLoad() {
        if (!apiUrl.isNullOrEmpty()) {
            mPresenter.requestRankList(apiUrl!!)
        }
    }

    override fun showLoading() {
        mLayoutStatusView?.showLoading()
    }

    override fun dismissLoading() {
        mLayoutStatusView?.showContent()
    }

    override fun setRankList(itemList: ArrayList<HomeBean.Issue.Item>) {
        mLayoutStatusView?.showContent()
        mAdapter?.addAllData(itemList)
    }

    override fun showError(errorMsg: String,errorCode:Int) {
        ToastUtils.showToast(activity!!, errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}



