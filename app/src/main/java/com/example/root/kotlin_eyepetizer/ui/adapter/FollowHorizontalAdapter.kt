package com.example.root.kotlin_eyepetizer.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.baselibrary.adapter.BaseHeaderFooterRecyclerAdapter
import com.example.baselibrary.utils.LogUtils
import com.example.baselibrary.utils.ToastUtils
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import com.example.root.kotlin_eyepetizer.basic.durationFormat
import com.example.root.kotlin_eyepetizer.glide.GlideApplyOptions
import kotlinx.android.synthetic.main.item_follow_horizontal.view.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/16
 *  desc: 关注部分， 水平的 RecyclerViewAdapter
 *  version:1.0
 */
class FollowHorizontalAdapter(context: Context, categoryList: ArrayList<HomeBean.Issue.Item>) : BaseHeaderFooterRecyclerAdapter<HomeBean.Issue.Item>(context, categoryList) {

    override fun getAdapterResId(): Int = R.layout.item_follow_horizontal

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun convertView(itemView: View?, data: HomeBean.Issue.Item) {
        val horezontalItemData = data.data
        Glide.with(mContext)
                .load(data.data?.cover?.feed)
                .apply(GlideApplyOptions.getRequestOptions())
                .transition(DrawableTransitionOptions().crossFade())
                .into(itemView!!.iv_cover_feed)

        // 横向 RecyclerView 封页图下的标题
        itemView.tv_title.text = if (!TextUtils.isEmpty(horezontalItemData?.title)) horezontalItemData?.title else ""

        // 格式化时间
        val timeFormat = durationFormat(horezontalItemData?.duration)
        // 标签
        LogUtils.d("horizontalItemData==title:${horezontalItemData?.title}tag:${horezontalItemData?.tags?.size}")

        if (horezontalItemData?.tags != null && horezontalItemData.tags.size > 0) {
            itemView.tv_tag.text = "#${horezontalItemData.tags[0].name} / $timeFormat"
        } else {
            itemView.tv_tag.text = "#$timeFormat"
        }

        itemView.setOnClickListener {
            goToVideoPlayer()
        }
    }

    private fun goToVideoPlayer() {
        ToastUtils.showToast(mContext, "测试一下")
    }
}
























