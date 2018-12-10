package com.example.root.kotlin_eyepetizer.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.baselibrary.baseadapters.BaseRecyclerAdapter
import com.example.baselibrary.baseutils.ScreenUtils
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.basic.durationFormat
import com.example.root.kotlin_eyepetizer.glide.GlideApplyOptions
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import com.example.root.kotlin_eyepetizer.ui.activity.VideoDetailActivity
import kotlinx.android.synthetic.main.item_category_detail.view.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/21
 *  desc: 分类详情的 adapter
 *  version:1.0
 */

/** 这里遇到一个 bug，就是在获取数据的时候 RankFragment 里初始化的时候如果不加 dataList 的话会获取不到数据，未 null **/
class CategoryDetailAdapter(context: Context, dataList: ArrayList<HomeBean.Issue.Item>)
   : BaseRecyclerAdapter<HomeBean.Issue.Item>(context, dataList) {

   override fun getAdapterResId(): Int = R.layout.item_category_detail

   @SuppressLint("SetTextI18n")
   override fun convertView(itemView: View?, data: HomeBean.Issue.Item, position: Int) {
      /**
       * 设置 item 的间隔（列表第一个和最后一个的 marginTop 和 marginBottom 设置间距（中间的 item 合并在一起））
       */
      if (position == mData!!.size - 1) {
         (itemView?.layoutParams as RecyclerView.LayoutParams).topMargin = ScreenUtils.dip2px(mContext, 1f)
         (itemView.layoutParams as RecyclerView.LayoutParams).bottomMargin = ScreenUtils.dip2px(mContext, 8f)
      } else {
         (itemView?.layoutParams as RecyclerView.LayoutParams).topMargin = ScreenUtils.dip2px(mContext, 1f)
      }

      val itemData = data.data
      val cover = itemData?.cover?.feed ?: ""
      // 加载封页图
      Glide.with(mContext)
              .load(cover)
              .apply(GlideApplyOptions.getRequestOptions())
              .transition(DrawableTransitionOptions().crossFade())
              .into(itemView.iv_image)
      itemView.tv_title.text = itemData?.title ?: ""

      // 格式化时间
      val timeFormat = durationFormat(itemData?.duration)
      itemView.tv_tag.text = "#${itemData?.category}/$timeFormat"

      itemView.setOnClickListener {
         goToVideoPlayer(mContext as Activity, itemView.iv_image, data)
      }
   }

   /**
    * 跳转到视频详情页面播放
    * @param activity
    * @param view
    */
   private fun goToVideoPlayer(activity: Activity, imageView: ImageView, itemData: HomeBean.Issue.Item) {
      val intent = Intent(activity, VideoDetailActivity::class.java)
      /**
       * 未做
       */
   }
}
























