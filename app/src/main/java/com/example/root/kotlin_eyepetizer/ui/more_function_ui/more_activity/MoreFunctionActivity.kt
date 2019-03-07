package com.example.root.kotlin_eyepetizer.ui.more_function_ui.more_activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.ui.more_function_ui.more_fragment.BookMessageFragment
import com.example.root.kotlin_eyepetizer.ui.more_function_ui.more_fragment.MovieMessageFragment
import com.example.root.kotlin_eyepetizer.ui.more_function_ui.more_fragment.PickOverMessageFragment
import kotlinx.android.synthetic.main.activity_more_function.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/07
 *  desc:更多功能的页面
 *  version:1.0
 */
@Suppress("UNUSED_EXPRESSION")
class MoreFunctionActivity : AppCompatActivity() {

   private val pickOverMessageFragment by lazy { PickOverMessageFragment.getInstance() }
   private val movieMessageFragment by lazy { MovieMessageFragment.getInstance() }
   private val bookMessageFragment by lazy { BookMessageFragment.getInstance() }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_more_function)

      initView()
   }

   private fun initView() {
      function_bar.setOnNavigationItemSelectedListener(navigationListener)
   }

   private var navigationListener = BottomNavigationView.OnNavigationItemSelectedListener {
      val transaction = supportFragmentManager.beginTransaction()
      // For a little polish, specify a transition animation
      transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
      hideFragments(transaction)    // hide all fragments
      when (it.itemId) {
         R.id.menu_item_pick_over ->  {
            transaction.show(PickOverMessageFragment.getInstance())
            transaction.replace(R.id.function_container, pickOverMessageFragment).commit()
            true
         }
         R.id.menu_item_movie ->  {
            transaction.show(movieMessageFragment)
            transaction.replace(R.id.function_container, movieMessageFragment).commit()
            true
         }
         R.id.menu_item_book ->  {
            transaction.show(bookMessageFragment)
            transaction.replace(R.id.function_container, bookMessageFragment).commit()
            true
         }
      }
      false
   }

   /**
    * 隐藏所有的Fragment
    * @param transaction transaction
    */
   private fun hideFragments(transaction: FragmentTransaction) {
      pickOverMessageFragment.let { transaction.hide(it) }
      movieMessageFragment.let { transaction.hide(it) }
      bookMessageFragment.let { transaction.hide(it) }
   }
}











