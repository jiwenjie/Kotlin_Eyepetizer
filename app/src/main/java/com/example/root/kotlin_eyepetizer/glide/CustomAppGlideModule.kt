package com.example.root.kotlin_eyepetizer.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
import java.io.InputStream

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/16
 *  desc:
 *  version:1.0
 */
@GlideModule
class CustomAppGlideModule : AppGlideModule() {

    /**
     * 通过 GlideBuilder 设置默认的结构（Engine，BitmapPool，ArrayPool，MemoryCache 等等）
     */
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        //重新设置内存限制
        builder.setMemoryCache(LruResourceCache(10 * 1024 * 1024))
    }

    /**
     * 清单解析的开启
     * 这里不开启的话，避免添加相同的 modules 两次
     */
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    /**
     * 为 App 注册一个自定义的 String 类型的 BaseGlideUrlLoader
     */
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.append(String::class.java, InputStream::class.java, CustomBaseGlideUrlLoader.Factory())
    }
}






















