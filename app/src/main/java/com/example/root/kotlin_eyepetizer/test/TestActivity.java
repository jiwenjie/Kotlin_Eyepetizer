package com.example.root.kotlin_eyepetizer.test;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

//import android.widget.Toast;
//import com.example.baselibrary.utils.ToastUtils;
import com.example.root.kotlin_eyepetizer.R;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2018/11/07
 * desc: 单纯用作测试使用
 * version:1.0
 */
public class TestActivity extends AppCompatActivity {

   private ValueAnimator animator;

   private ObjectAnimator objectAnimator;
   private ImageView iv_eye;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_test);
      iv_eye = findViewById(R.id.test);
//      ToastUtils.INSTANCE.showToast(TestActivity.this, "测试", Toast.LENGTH_SHORT);

//      ToastUtils.INSTANCE.showToast(TestActivity.this, "测试成功", Toast.LENGTH_SHORT);
//      SharePreferencesUtils.INSTANCE.saveString(TestActivity.this, "d", "adv");

//      animator = ValueAnimator.ofFloat(0, 1);
//      animator.setDuration(1500);
//      animator.addUpdateListener(animation -> {
//         float scale = (float) animation.getAnimatedValue();
//         iv_eye.setScaleX(scale);
//         iv_eye.setScaleY(scale);
//      });
//      animator.start();
//
//      objectAnimator = ObjectAnimator.ofFloat(iv_eye, "alpha", 0, 1);
//      objectAnimator.setDuration(1500);
//      objectAnimator.start();
   }
}
