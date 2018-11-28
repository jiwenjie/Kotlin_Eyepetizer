package com.example.root.kotlin_eyepetizer.contract

import com.example.root.kotlin_eyepetizer.base.IBaseView
import com.example.root.kotlin_eyepetizer.bean.CategoryBean
import io.reactivex.Observable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/26
 *  desc: Category 的分类部分契约类
 *  version:1.0
 */
interface CategoryContract {

   interface CategoryModel {
      fun getCategoryData() : Observable<ArrayList<CategoryBean>>
   }

   interface CategoryView: IBaseView {
      /**
       * 显示分类的信息
       */
      fun showCategory(categoryList: ArrayList<CategoryBean>)
      /**
       * 显示错误信息
       */
      fun showError(errorMsg: String, errorCode: Int)
   }

   /**
    * 用来测试使用
    */
   interface CategoryPresenter {
      /**
       * 获取分类的信息
       */
      fun getCategoryData()
   }
}



















