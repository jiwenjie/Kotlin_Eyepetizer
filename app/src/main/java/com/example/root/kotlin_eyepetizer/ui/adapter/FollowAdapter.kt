package com.example.root.kotlin_eyepetizer.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.baselibrary.adapter.BaseHeaderFooterRecyclerAdapter
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import com.example.root.kotlin_eyepetizer.glide.RequestOptionSet
import kotlinx.android.synthetic.main.item_follow.view.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/16
 *  desc:关注的 adapter
 *  version:1.0
 */
class FollowAdapter(context: Context, dataList: ArrayList<HomeBean.Issue.Item>?) : BaseHeaderFooterRecyclerAdapter<HomeBean.Issue.Item>(context, dataList) {

    override fun getAdapterResId(): Int {
        return R.layout.item_follow
    }

    @SuppressLint("CheckResult")
    override fun convertView(itemView: View?, data: HomeBean.Issue.Item) {
        val headerData = data.data?.header
        /**
         * 加载作者头像
         */
        itemView!!.tv_title.text = headerData?.title
        itemView.tv_desc.text = headerData?.description

        Glide.with(mContext)
            .load(headerData?.icon)
            .apply(RequestOptionSet.getRequestOptions())
            .transition(DrawableTransitionOptions().crossFade())
            .into(itemView.iv_avatar)

        itemView.fl_recyclerView.layoutManager = LinearLayoutManager(mContext as Activity, LinearLayoutManager.HORIZONTAL, false)
        itemView.fl_recyclerView.adapter = FollowHorizontalAdapter(mContext, data.data!!.itemList)
    }
}



















