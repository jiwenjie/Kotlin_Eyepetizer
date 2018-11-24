package com.example.root.kotlin_eyepetizer.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.test.adapter.TestAdapter
import kotlinx.android.synthetic.main.activity_watch_history.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/18
 *  desc:
 *  version:1.0
 */
class WatchHistoryActivity : AppCompatActivity() {

   private var adapter: TestAdapter? = null
   private var list: ArrayList<String> = ArrayList()

   init {
      adapter = TestAdapter(this)
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_watch_history)

      for (i in 1..20) {
         list.add("title" + i)
      }

      adapter?.addData(list)
      rlTest.adapter = adapter
      rlTest.layoutManager = LinearLayoutManager(this)
   }
}