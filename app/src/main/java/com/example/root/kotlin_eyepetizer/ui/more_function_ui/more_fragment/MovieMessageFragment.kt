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
 *  time:2018/12/10
 *  desc:展示最近的豆瓣电影评分和简介
 *  version:1.0
 */
class MovieMessageFragment : Fragment() {

   companion object {
      fun getInstance(): MovieMessageFragment {
         val fragment = MovieMessageFragment()
         val bundle = Bundle()
         fragment.arguments = bundle
         return fragment
      }
   }

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      return LayoutInflater.from(activity!!).inflate(R.layout.fragment_movie_message, container, false)
   }
}