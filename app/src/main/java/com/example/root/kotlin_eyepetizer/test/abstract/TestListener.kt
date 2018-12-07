package com.example.root.kotlin_eyepetizer.test.abstract

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/03
 *  desc:
 *  version:1.0
 */
class TestListener {

   var mListener: CallListener? = null

   fun setListener(listener: CallListener) {
      this.mListener = listener
   }

}