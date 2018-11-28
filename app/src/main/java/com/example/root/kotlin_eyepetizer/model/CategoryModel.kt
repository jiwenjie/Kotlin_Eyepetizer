package com.example.root.kotlin_eyepetizer.model

import com.example.baselibrary.RetrofitManager
import com.example.root.kotlin_eyepetizer.api.ApiService
import com.example.root.kotlin_eyepetizer.api.Constant
import com.example.root.kotlin_eyepetizer.bean.CategoryBean
import com.example.root.kotlin_eyepetizer.contract.CategoryContract
import com.example.root.kotlin_eyepetizer.utils.SchedulerUtils
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/26
 *  desc:分类本身列表的 Model (分类的每个 item 点击进去的详情也是列表页面)
 *  version:1.0
 */
class CategoryModel : CategoryContract.CategoryModel {

   override fun getCategoryData(): Observable<ArrayList<CategoryBean>> {
      return RetrofitManager.provideClient(Constant.BASE_URL)
              .create(ApiService::class.java)
              .getCategory()
              .compose(SchedulerUtils.ioToMain())
   }
}