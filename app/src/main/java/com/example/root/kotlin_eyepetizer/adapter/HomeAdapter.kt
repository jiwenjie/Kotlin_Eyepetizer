package com.example.root.kotlin_eyepetizer.adapter

import android.content.Context
import android.view.View
import com.example.baselibrary.adapter.BaseHeaderFooterRecyclerAdapter
import com.example.root.kotlin_eyepetizer.bean.HomeBean

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/09
 *  desc: 首页数据的适配器
 *  version:1.0
 */
class HomeAdapter(mContext: Context, beanList: ArrayList<HomeBean.Issue.Item>) : BaseHeaderFooterRecyclerAdapter<HomeBean.Issue.Item>(mContext, beanList) {

    // banner 作为 RecyclerView 的第一项
    var bannerItemSize = 0

    companion object {
        private const val ITEM_TYPE_BANNER = 1    //Banner 类型
        private const val ITEM_TYPE_TEXT_HEADER = 2   //textHeader
        private const val ITEM_TYPE_CONTENT = 3    //item
    }

    /**
     * 设置 Banner 大小
     */
    fun setBannerSize(count: Int) {
        bannerItemSize = count
    }

//    override fun getAdapterResId(): Int {
//
//        return R.layout.fragment_index_item
//    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
//        return super.onCreateViewHolder(parent, viewType)
//    }



    override fun convertView(itemView: View?, data: HomeBean.Issue.Item) {

    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 ->
                ITEM_TYPE_BANNER
            mData!![position + bannerItemSize - 1].type == "textHeader" ->
                ITEM_TYPE_TEXT_HEADER
            else ->
                ITEM_TYPE_CONTENT
        }
    }
}


