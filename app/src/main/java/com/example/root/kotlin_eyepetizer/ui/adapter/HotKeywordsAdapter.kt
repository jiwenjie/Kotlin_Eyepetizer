package com.example.root.kotlin_eyepetizer.ui.adapter

import android.content.Context
import android.view.View
import com.example.baselibrary.baseadapters.BaseRecyclerAdapter
import com.example.root.kotlin_eyepetizer.R
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.item_flow_text.view.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/03
 *  desc:Tag 标签布局的 Adapter
 *  version:1.0
 */
class HotKeywordsAdapter(mContext: Context, mList: ArrayList<String>):
        BaseRecyclerAdapter<String>(mContext, mList) {

   /**
    * Kotlin 的函数可以作为参数，这样写 callback 的时候就可以不用 interface 了
    * 而且这样的写法可以更好的使用 lambda 方式
    */
   private var mOnTagItemClickListener: ((tag: String) -> Unit)? = null

   fun setOnTagItemClickListener(onTagItemClickListener: (tag: String) -> Unit) {
      this.mOnTagItemClickListener = onTagItemClickListener
   }

   override fun convertView(itemView: View?, data: String, position: Int) {
      itemView!!.tv_title.text = data

      val params = itemView.tv_title.layoutParams
      if (params is FlexboxLayoutManager.LayoutParams) {
         params.flexGrow = 1.0f
      }

      itemView.setOnClickListener {
         mOnTagItemClickListener?.invoke(data)
      }
   }

   override fun getAdapterResId(): Int = R.layout.item_flow_text
}



















