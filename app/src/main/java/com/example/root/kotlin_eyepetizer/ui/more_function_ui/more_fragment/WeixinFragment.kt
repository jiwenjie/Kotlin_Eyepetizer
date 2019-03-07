package com.example.root.kotlin_eyepetizer.ui.more_function_ui.more_fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.root.kotlin_eyepetizer.R

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/11
 *  desc: 微信精选的 Fragment
 *  version:1.0
 */
class WeixinFragment : Fragment() {

   companion object {
      fun getInstance(): WeixinFragment {
         val fragment = WeixinFragment()
         val bundle = Bundle()
         fragment.arguments = bundle
         return fragment
      }
   }

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      return LayoutInflater.from(activity!!).inflate(R.layout.fragment_weixin, container, false)

   }
}