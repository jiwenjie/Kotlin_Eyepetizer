package com.example.root.kotlin_eyepetizer.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.baselibrary.adapter.BaseHeaderFooterRecyclerAdapter
import com.example.root.kotlin_eyepetizer.App
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.ui.activity.CategoryListActivity
import com.example.root.kotlin_eyepetizer.basic.api.Constant
import com.example.root.kotlin_eyepetizer.mvp.bean.CategoryBean
import com.example.root.kotlin_eyepetizer.glide.GlideApplyOptions
import kotlinx.android.synthetic.main.item_category.view.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/26
 *  desc:分类的适配器 (是外部的各种大的分类，不是每个具体分类的列表适配器)
 *  version:1.0
 */
class CategoryAdapter(context: Context, dataList: ArrayList<CategoryBean>) : BaseHeaderFooterRecyclerAdapter<CategoryBean>(context, dataList) {

   private var textTypeface: Typeface? = null

   init {
      textTypeface = Typeface.createFromAsset(App.INSTANCE?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
   }

   override fun getAdapterResId(): Int {
      return R.layout.item_category
   }

   @SuppressLint("SetTextI18n")
   override fun convertView(itemView: View?, data: CategoryBean) {
      itemView!!.tv_category_name.text = "#${data.name}"
      // 设置方正兰亭细黑简体
      itemView.tv_category_name.typeface = textTypeface

      Glide.with(mContext)
              .load(data.bgPicture)
              .apply(GlideApplyOptions.getRequestOptions())
              .transition(DrawableTransitionOptions().crossFade())
              .thumbnail(0.5f)
              .into(itemView.iv_category)

      itemView.setOnClickListener {
         val intent = Intent(mContext as Activity, CategoryListActivity::class.java)
         intent.putExtra(Constant.BUNDLE_CATEGORY_DATA, data)
         mContext.startActivity(intent)
      }
   }
}
















