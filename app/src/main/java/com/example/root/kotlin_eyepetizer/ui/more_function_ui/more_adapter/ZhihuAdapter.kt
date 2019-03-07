package com.example.root.kotlin_eyepetizer.ui.more_function_ui.more_adapter

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.baselibrary.baseadapters.BaseRecyclerAdapter
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.glide.GlideApplyOptions
import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_bean.ZhihuDailyItemBean
import kotlinx.android.synthetic.main.fragment_zhihu_item.view.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/12
 *  desc:知乎首页的适配器
 *  version:1.0
 */
@Suppress("PLUGIN_WARNING")
class ZhihuAdapter(context: Context, beanList: ArrayList<ZhihuDailyItemBean>): BaseRecyclerAdapter<ZhihuDailyItemBean>(context, beanList) {

   override fun getAdapterResId(): Int = R.layout.fragment_zhihu_item

   override fun convertView(itemView: View?, data: ZhihuDailyItemBean, position: Int) {
      itemView!!.tv_item_title.text = data.title
      Glide.with(mContext)
              .load(data.images[0])
              .apply(GlideApplyOptions.getRequestOptions())
              .transition(DrawableTransitionOptions().crossFade())
              .into(itemView.iv_item_image)
   }
}