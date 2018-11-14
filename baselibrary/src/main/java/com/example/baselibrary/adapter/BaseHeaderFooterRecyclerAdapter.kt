package com.example.baselibrary.adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/04
 *  desc:
 *  version:1.0
 */
abstract class BaseHeaderFooterRecyclerAdapter<T: Any>(context: Context,
                                                       data: ArrayList<T>? = null) :
        RecyclerView.Adapter<BaseHeaderFooterRecyclerAdapter.BaseHolder>() {

    protected val mContext = context
    var mData = data
    private val mInflater: LayoutInflater
    private val footers = SparseArray<View>()
    private val headers = SparseArray<View>()
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mOnItemLongClickListener: OnItemLongClickListener? = null

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, view: View)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int, view: View)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mOnItemClickListener = listener
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        this.mOnItemLongClickListener = listener
    }

    fun updateAdapterData(data: ArrayList<T>?) {
        this.mData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return if (hasHeader() && headers.get(viewType) != null)
            BaseHolder(headers.get(viewType))
        else if (hasFooter() && footers.get(viewType) != null)
            BaseHolder(footers.get(viewType))
        else BaseHolder(mInflater.inflate(getAdapterResId(), parent, false))
    }

    protected open fun getAdapterResId(): Int {
        return -1
    }

    override fun getItemCount(): Int = getHeaderSize() + getDataSize() + getFooterSize()

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        if (!isHeader(position) && !isFooter(position)) {
            val pos = position - getHeaderSize()
            convertView(holder.itemView, mData!![pos])

            if (mOnItemClickListener != null)
                holder.itemView.setOnClickListener { v ->
                    mOnItemClickListener!!.onItemClick(pos, v)
                }

            if (mOnItemLongClickListener != null) {
                holder.itemView.setOnLongClickListener { v ->
                    mOnItemLongClickListener!!.onItemLongClick(pos, v)
                    false
                }
            }
        }
    }

    abstract fun convertView(itemView: View?, data: T)

    fun addHeader(header: View) {
        headers.put(HEADER + getHeaderSize(), header)
        notifyItemInserted(getHeaderSize())
    }

    fun addFooter(footer: View) {
        footers.put(FOOTER + getFooterSize(), footer)
        var pos = if (mData == null) getFooterSize() else getFooterSize() + mData!!.size - 1
        if (hasHeader()) pos += getHeaderSize()
        notifyItemInserted(pos)
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            isHeader(position) -> headers.keyAt(position)
            isFooter(position) -> footers.keyAt(position - getDataSize() - getHeaderSize())
            else -> getConvertType(position)
        }
    }

    protected open fun getConvertType(position: Int): Int = 0

    private fun hasHeader(): Boolean {
        return headers.size() > 0
    }

    private fun hasFooter(): Boolean {
        return footers.size() > 0
    }

    private fun isHeader(position: Int): Boolean {
        return hasHeader() && position < getHeaderSize()
    }

    private fun isFooter(position: Int): Boolean {
        return hasFooter() && position >= getDataSize() + getHeaderSize()
    }

    private fun getHeaderSize(): Int {
        return headers.size()
    }

    private fun getFooterSize(): Int {
        return footers.size()
    }

    private fun getDataSize(): Int {
        return mData?.size ?: 0
    }

    private fun getRealPosition(viewHolder: RecyclerView.ViewHolder): Int {
        return viewHolder.layoutPosition
    }

    fun getAdapterData(): MutableList<T>? {
        return if (mData != null) mData!!
        else null
    }

    fun addData(data: T) {
        if (mData != null) {
            this.mData!!.add(data)
            notifyDataSetChanged()
        } else {
            throw IllegalStateException("mData is null and init first")
        }
    }

    fun addData(position: Int, data: T) {
        if (mData != null) {
            this.mData!!.add(position, data)
            notifyDataSetChanged()
        } else {
            throw IllegalStateException("mData is null and init first")
        }
    }

    fun addAllData(data: MutableList<T>) {
        if (mData != null) {
            this.mData!!.addAll(data)
            notifyDataSetChanged()
        } else {
            throw IllegalStateException("mData is null and init first")
        }
    }

    fun removeData(position: Int) {
        if (mData != null) {
            this.mData!!.removeAt(position)
            notifyDataSetChanged()
        } else {
            throw IllegalStateException("mData is null and init first")
        }
    }

    fun removeData(data: T) {
        if (mData != null && data in mData!!) {
            mData!!.remove(data)
            notifyDataSetChanged()
        } else {
            throw IllegalStateException("data not in mData and check it")
        }
    }

    fun clearData() {
        if (mData != null) {
            mData!!.clear()
            notifyDataSetChanged()
        } else {
            throw IllegalStateException("mData is null and init first")
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val lm = recyclerView.layoutManager
        if (lm is GridLayoutManager) {
            lm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (isHeader(position) || isFooter(position)) lm.spanCount
                    else 1
                }
            }
        }
    }

    override fun onViewAttachedToWindow(holder: BaseHolder) {
        super.onViewAttachedToWindow(holder)
        val lp = holder.itemView.layoutParams
        if (lp is StaggeredGridLayoutManager.LayoutParams)
            lp.isFullSpan = isHeader(getRealPosition(holder)) || isFooter(getRealPosition(holder))
    }

    class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private const val HEADER = 0x10000000
        private const val FOOTER = 0x20000000
    }
}























