package com.example.root.kotlin_eyepetizer.bean

import java.io.Serializable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/13
 *  desc: 分类 Bean
 *  version:1.0
 */
data class CategoryBean(val id: Long,
                        val name: String,
                        val alias: Any,
                        val description: String,
                        val bgPicture: String,
                        val bgColor: String,
                        val headerImage: String,
                        val defaultAuthorId: Long) : Serializable


















