package com.example.root.kotlin_eyepetizer.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.baselibrary.adapter.BaseHeaderFooterRecyclerAdapter
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.activity.VideoDetailActivity
import com.example.root.kotlin_eyepetizer.bean.HomeBean
import com.example.root.kotlin_eyepetizer.durationFormat
import com.example.root.kotlin_eyepetizer.glide.RequestOptionSet
import kotlinx.android.synthetic.main.item_category_detail.view.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/21
 *  desc: 分类详情的 adapter
 *  version:1.0
 */

/** 这里遇到一个 bug，就是在获取数据的时候 RankFragment 里初始化的时候如果不加 dataList 的话会获取不到数据，未 null **/
class CategoryDetailAdapter(context: Context, dataList: ArrayList<HomeBean.Issue.Item>) : BaseHeaderFooterRecyclerAdapter<HomeBean.Issue.Item>(context, dataList) {

   override fun getAdapterResId(): Int = R.layout.item_category_detail

   @SuppressLint("SetTextI18n")
   override fun convertView(itemView: View?, data: HomeBean.Issue.Item) {
      val itemData = data.data
      val cover = itemData?.cover?.feed?:""
      // 加载封页图
      Glide.with(mContext)
              .load(cover)
              .apply(RequestOptionSet.getRequestOptions())
              .transition(DrawableTransitionOptions().crossFade())
              .into(itemView!!.iv_image)
      itemView.tv_title.text = itemData?.title?:""

      // 格式化时间
      val timeFormat = durationFormat(itemData?.duration)
      itemView.tv_tag.text = "#${itemData?.category}/$timeFormat"

      itemView.setOnClickListener {
         goToVideoPlayer(mContext as Activity, itemView.iv_image, data)
      }
   }

   /**
    * 跳转到视频详情页面播放
    *
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
























