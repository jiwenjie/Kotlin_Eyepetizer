package com.example.root.kotlin_eyepetizer.adapter

import android.content.Context
import android.view.View
import com.example.baselibrary.adapter.BaseHeaderFooterRecyclerAdapter
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.bean.HomeBean
import kotlinx.android.synthetic.main.item_follow.view.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/16
 *  desc:关注的 adapter
 *  version:1.0
 */
class FollowAdapter(context: Context) : BaseHeaderFooterRecyclerAdapter<HomeBean.Issue.Item>(context) {

    override fun getAdapterResId(): Int {
        return R.layout.item_follow
    }

    override fun convertView(itemView: View?, data: HomeBean.Issue.Item) {
        val headerData = data.data?.header
        /**
         * 加载作者头像
         */
        itemView!!.tv_title.text = headerData?.title
        itemView.tv_desc.text = headerData?.description
//        Glide.with(mContext)
//                .load(headerData?.icon)
//                .transition(DrawableTransformation().crossFade)
    }
}



















