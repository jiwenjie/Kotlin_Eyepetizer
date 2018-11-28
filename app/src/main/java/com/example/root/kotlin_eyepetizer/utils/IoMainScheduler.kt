package com.example.root.kotlin_eyepetizer.utils

import com.example.root.kotlin_eyepetizer.base.BaseScheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/15
 *  desc:
 *  version:1.0
 */
class IoMainScheduler<T>
    : BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())