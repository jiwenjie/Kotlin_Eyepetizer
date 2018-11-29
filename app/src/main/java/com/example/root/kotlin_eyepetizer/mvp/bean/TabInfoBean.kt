package com.example.root.kotlin_eyepetizer.mvp.bean

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/13
 *  desc: 热门的 tabInfo
 *  version:1.0
 */
data class TabInfoBean(val tabInfo: TabList,
                       val defaultIdx: Int) {

    data class TabList(val tabList: ArrayList<Tab>) {

        data class Tab(val id: Long,
                       val name: String,
                       val apiUrl: String,
                       val tabType: Long,
                       val nameType: Long,
                       val adTrack: Any)
    }
}





