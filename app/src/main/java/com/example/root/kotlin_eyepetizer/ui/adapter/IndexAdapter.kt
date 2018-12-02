package com.example.root.kotlin_eyepetizer.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.basic.api.Constant
import com.example.root.kotlin_eyepetizer.basic.durationFormat
import com.example.root.kotlin_eyepetizer.glide.GlideApplyOptions
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import com.example.root.kotlin_eyepetizer.ui.activity.VideoDetailActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_index_banner_item.view.*
import kotlinx.android.synthetic.main.fragment_index_content_item.view.*
import kotlinx.android.synthetic.main.fragment_index_header_item.view.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/09
 *  desc: 首页精选的 Adpter
 *  version:1.0
 */
@Suppress("NAME_SHADOWING")
class IndexAdapter(context: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mContext: Context? = context
    var beanList = ArrayList<HomeBean.Issue.Item>()

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

    /**
     * 添加更多数据
     */
    fun addAllData(itemList: ArrayList<HomeBean.Issue.Item>) {
        this.beanList.addAll(itemList)
        notifyDataSetChanged()
    }

    /**
     * 得到 item 的类型
     */
    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 ->
                ITEM_TYPE_BANNER
            beanList[position + bannerItemSize - 1].type == "textHeader" ->
                ITEM_TYPE_TEXT_HEADER
            else ->
                ITEM_TYPE_CONTENT
        }
    }

    /**
     * 得到 RecyclerView Item 的数量，（Banner 作为一个 item）
     */
    override fun getItemCount(): Int {
        return when {
            beanList.size > bannerItemSize -> beanList.size - bannerItemSize + 1
            beanList.isEmpty() -> 0
            else -> 1
        }
    }

    /**
     * 绑定布局
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_BANNER -> ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fragment_index_banner_item, parent, false))
            ITEM_TYPE_TEXT_HEADER -> ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fragment_index_header_item, parent, false))
            else -> {
                ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fragment_index_content_item, parent, false))
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            //Banner
            ITEM_TYPE_BANNER -> {
                val bannerItemData: ArrayList<HomeBean.Issue.Item> = beanList.take(bannerItemSize).toCollection(ArrayList())
                val bannerFeedList = ArrayList<String>()
                val bannerTitleList = ArrayList<String>()
                //取出 banner 顯示的 img 和 title
                Observable.fromIterable(bannerItemData)
                    .subscribe { list ->
                        bannerFeedList.add(list.data?.cover?.feed ?: "")
                        bannerTitleList.add(list.data?.title ?: "")
                    }
                // 設置 banner
                with(viewHolder) {
                    itemView.banner.run {
                        setAutoPlayAble(bannerFeedList.size > 1)
                        setData(bannerFeedList, bannerTitleList)
                        setAdapter { banner, itemView, feedImageUrl, position ->
                            Glide.with(mContext!!)
                                .load(feedImageUrl)
                                .apply(GlideApplyOptions.getRequestOptions())
                                .transition(DrawableTransitionOptions().crossFade())
                                .into(banner.getItemImageView(position))
                        }
                    }
                }
                // 沒有使用到的參數在 kotlin 中用 "_" 代替
                viewHolder.itemView.banner.setDelegate { _, imageView, _, position ->
                    goToVideoPlayer(mContext as Activity, imageView, bannerItemData[position])
                }
            }

            // TextHeader
            ITEM_TYPE_TEXT_HEADER -> {
                viewHolder.itemView.tvHeader.text = beanList[position + bannerItemSize - 1].data?.text ?: ""
            }

            // content
            ITEM_TYPE_CONTENT -> {
                setVideoItem(viewHolder, beanList[position + bannerItemSize - 1])
            }
        }


    }

    @SuppressLint("SetTextI18n")
    private fun setVideoItem(holder: RecyclerView.ViewHolder, item: HomeBean.Issue.Item) {
        val itemData = item.data

        val defAvatar = R.mipmap.default_avatar
        val cover = itemData?.cover?.feed
        var avatar = itemData?.author?.icon
        var tagText: String? = "#"

        //如果作者的出处为空，就显示提供者信息
        if (avatar.isNullOrEmpty()) {
            avatar = itemData?.provider?.icon
        }
        //加载封页图
        Glide.with(mContext!!)
            .load(cover)
            .apply(GlideApplyOptions.getRequestOptions())
            .transition(DrawableTransitionOptions().crossFade())
            .into(holder.itemView.iv_cover_feed)

        // 如果提供者信息为空，就显示默认
        if (avatar.isNullOrEmpty()) {
            Glide.with(mContext!!)
                .load(defAvatar)
                .apply(GlideApplyOptions.getRequestDefaultAvatar())
                .transition(DrawableTransitionOptions().crossFade())
                .into(holder.itemView.iv_avatar)
        } else {
            Glide.with(mContext!!)
                .load(avatar)
                .apply(GlideApplyOptions.getRequestDefaultAvatar())
                .transition(DrawableTransitionOptions().crossFade())
                .into(holder.itemView.iv_avatar)
        }
        holder.itemView.tv_title.text = itemData?.title ?: ""

        //遍历标签
        itemData?.tags?.take(4)?.forEach {
            tagText += (it.name + "/")
        }
        //格式化时间
        val timeFormat = durationFormat(itemData?.duration)

        tagText += timeFormat

        holder.itemView.tv_tag.text = tagText!!
        holder.itemView.tv_category.text = "#" + itemData?.category
        holder.itemView.setOnClickListener {
            goToVideoPlayer(mContext as Activity, holder.itemView.iv_cover_feed, item)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    /**
     * 跳轉到視頻詳情頁播放視頻
     */
    private fun goToVideoPlayer(activity: Activity, view: View, itemData: HomeBean.Issue.Item) {
        val intent = Intent(activity, VideoDetailActivity::class.java)
        intent.putExtra(Constant.BUNDLE_VIDEO_DATA, itemData)
        intent.putExtra(VideoDetailActivity.TRANSITION, true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val pair = Pair(view, VideoDetailActivity.IMG_TRANSITION)
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, pair)
            ActivityCompat.startActivity(activity, intent, activityOptions.toBundle())
        } else {
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.anim_in, R.anim.anim_out)
        }
    }

}

























