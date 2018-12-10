package com.example.baselibrary.baseadapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/08
 *  desc:
 *  version:1.0
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseListAdapter<T: Any, VH : BaseListAdapter.BaseListHolder>(context: Context, dataList: ArrayList<T>? = null) : BaseAdapter() {

    private val mContext = context
    private var mDataList = dataList
    private val mInflater = LayoutInflater.from(mContext)

    fun updateDataList(dataList: ArrayList<T>?) {
        this.mDataList = dataList
        notifyDataSetChanged()
    }

    fun getAdapterDataList(): ArrayList<T> = mDataList ?: ArrayList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var copyConvert = convertView
        val holder: VH

        if (copyConvert == null) {
            copyConvert = mInflater.inflate(getAdapterLayoutId(), parent, false)
            holder = createViewHolder(itemView = copyConvert)
            copyConvert!!.tag = holder
        } else
            holder = copyConvert.tag as VH


        convertItemView(holder, mDataList!![position])

        return copyConvert
    }

    abstract fun createViewHolder(itemView: View): VH

    abstract fun convertItemView(holder: VH, data: T)

    abstract fun getAdapterLayoutId(): Int

    override fun getItem(position: Int): Any = mDataList!![position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = mDataList?.size ?: 0

    open class BaseListHolder
}




























