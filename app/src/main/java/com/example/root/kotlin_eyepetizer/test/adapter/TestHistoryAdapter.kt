package com.example.root.kotlin_eyepetizer.test.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/20
 *  desc:
 *  version:1.0
 */
class TestHistoryAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

   private var mContext: Context? = context
   private lateinit var beanList: ArrayList<String>

   fun addData(list: ArrayList<String>) {
      beanList.addAll(list)
   }

   override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override fun getItemCount(): Int {
      return beanList.size
   }

   override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

//   class MyViewHolder : RecyclerView.ViewHolder {
//
//
//   }

}