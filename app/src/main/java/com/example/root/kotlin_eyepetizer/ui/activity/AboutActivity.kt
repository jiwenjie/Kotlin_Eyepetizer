package com.example.root.kotlin_eyepetizer.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.root.kotlin_eyepetizer.App
import com.example.root.kotlin_eyepetizer.R
import com.example.baselibrary.utils.AppUtils
import com.example.root.kotlin_eyepetizer.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_about.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/18
 *  desc:
 *  version:1.0
 */
class AboutActivity : AppCompatActivity() {

   @SuppressLint("SetTextI18n", "PrivateResource")
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_about)

      /**
       * 因为只是单独的 about 界面，所以就不需要继承 BaseActivity 麻烦了
       *
       */
      StatusBarUtil.darkMode(this)
      StatusBarUtil.setPaddingSmart(this, about_toolbar)
      about_tv_appName.text = "V${AppUtils.getVerName(App.INSTANCE!!.applicationContext)}"
      // 返回
      about_toolbar.setNavigationOnClickListener {
         finish()
         overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom)
      }
      // 访问 GitHub
      relayout_gitHub.setOnClickListener {
         val uri = Uri.parse("https://github.com/jiwenjie/Kotlin_Eyepetizer")
         val intent = Intent(Intent.ACTION_VIEW, uri)
         startActivity(intent)
      }
   }
}

























