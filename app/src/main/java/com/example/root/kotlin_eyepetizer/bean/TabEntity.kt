package com.example.root.kotlin_eyepetizer.bean

import com.flyco.tablayout.listener.CustomTabEntity

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/08
 *  desc:
 *  version:1.0
 */
class TabEntity(var title: String, private var selectedIcon: Int, private var unSelectedIcon: Int): CustomTabEntity {

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabTitle(): String {
        return title
    }
}












