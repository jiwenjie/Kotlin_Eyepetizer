package com.example.root.kotlin_eyepetizer.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.root.kotlin_eyepetizer.test.abstract.CallListener
import com.example.root.kotlin_eyepetizer.test.abstract.TestListener

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/03
 *  desc:
 *  version:1.0
 */
class TestCallListener : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      initView()
   }

   fun initView() {
      var Test = TestListener()

      Test.setListener(object : CallListener() {

         override fun OnItemListener(name: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
         }

      })
   }
}