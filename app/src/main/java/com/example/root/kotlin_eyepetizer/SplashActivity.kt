package com.example.root.kotlin_eyepetizer

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.example.root.kotlin_eyepetizer.activity.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/06
 *  desc: App 启动页
 *  version:1.0
 */
class SplashActivity : AppCompatActivity() {

   private lateinit var objAlphaIv: ObjectAnimator
   private lateinit var objAlphaTv: ObjectAnimator

   private lateinit var objScaleXIv: ObjectAnimator
   private lateinit var objScaleYIv: ObjectAnimator
   private lateinit var objScaleXTv: ObjectAnimator
   private lateinit var objScaleYTv: ObjectAnimator

   @SuppressLint("PrivateResource")
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_splash)

      /**
       * 注意这里要在 0 和 1 后面加 f，加 f
       * 此处为 透明动画
       */
      objAlphaIv = ObjectAnimator.ofFloat(iv_splash_eye, "alpha", 0f, 1f)
      objAlphaTv = ObjectAnimator.ofFloat(stv_english, "alpha", 0f, 1f)

      /**
       * 缩放动画
       */
      objScaleXIv = ObjectAnimator.ofFloat(iv_splash_eye, "scaleX", 0f, 1f)
      objScaleYIv = ObjectAnimator.ofFloat(iv_splash_eye, "scaleY", 0f, 1f)
      objScaleXTv = ObjectAnimator.ofFloat(stv_english, "scaleX", 0f, 1f)
      objScaleYTv = ObjectAnimator.ofFloat(stv_english, "scaleY", 0f, 1f)
      val animationSet = AnimatorSet()
      animationSet.playTogether(objScaleXIv, objScaleYIv, objScaleXTv, objScaleYTv, objAlphaIv, objAlphaTv)
      animationSet.duration = 2400
      animationSet.start()

      Handler().postDelayed({
         val intent = Intent(this, MainActivity::class.java)
         startActivity(intent)
         finish()
         overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
      }, 4000)

   }
}