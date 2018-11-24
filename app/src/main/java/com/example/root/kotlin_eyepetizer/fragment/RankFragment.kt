package com.example.root.kotlin_eyepetizer.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.baselibrary.utils.ErrorStatus
import com.example.baselibrary.utils.ToastUtils
import com.example.baselibrary.views.BaseMvpFragment
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.adapter.CategoryDetailAdapter
import com.example.root.kotlin_eyepetizer.bean.HomeBean
import com.example.root.kotlin_eyepetizer.contract.RankContract
import com.example.root.kotlin_eyepetizer.presenter.RankPresenter
import kotlinx.android.synthetic.main.fragment_rank.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/21
 *  desc: 热门的列表数据显示 fragment
 *  version:1.0
 */
class RankFragment : BaseMvpFragment<RankContract.RankView, RankPresenter>(), RankContract.RankView {

//    private val itemList = ArrayList<HomeBean.Issue.Item>()
    private var apiUrl: String? = null
//    private val mPresenter by lazy { RankPresenter(this) }
    private val adapter by lazy {
        CategoryDetailAdapter(activity!!, itemList)
    }

    private var itemList = ArrayList<HomeBean.Issue.Item>()

    /** 这里之前遇到一个 bug，是因为我直接 init 了 适配器，导致 BaseHeaderAdapter null 指针
     * 所以改为 lazy 比较好，或者放在 initFragment 位置 **/

    companion object {
       fun getInstance(apiUrl: String): RankFragment {
           val fragment = RankFragment()
           val bundle = Bundle()
           fragment.arguments = bundle
           fragment.apiUrl = apiUrl
           return fragment
       }
    }

    override fun getLayoutId(): Int = R.layout.fragment_rank

    override fun initFragment(savedInstanceState: Bundle?) {
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = adapter

        mLayoutStatusView = multipleStatusView
    }

    override fun initPresenter(): RankPresenter {
        return RankPresenter(this)
    }

    override fun loadData() {
        if (!apiUrl.isNullOrEmpty()) {
            mPresenter.requestRankList(apiUrl!!)
        }
    }

    override fun setRankList(itemList: ArrayList<HomeBean.Issue.Item>) {
        multipleStatusView.showContent()
        adapter.addAllData(itemList)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ToastUtils.showToast(activity!!, errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView.showNoNetwork()
        } else {
            multipleStatusView.showError()
        }
    }

    override fun showLoading() {
        multipleStatusView.showLoading()
    }

    override fun dismissLoading() {
        multipleStatusView.showContent()
    }
}






















