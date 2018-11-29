package com.example.root.kotlin_eyepetizer.test;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;
import com.example.baselibrary.PermissionListener;
import com.example.baselibrary.utils.ToastUtils;
import com.example.baselibrary.views.BaseMvpActivity;
import com.example.baselibrary.views.BaseMvpPresenter;
import com.example.root.kotlin_eyepetizer.R;
import com.example.root.kotlin_eyepetizer.basic.base.IBaseView;

import java.util.List;

//import android.widget.Toast;
//import com.example.baselibrary.utils.ToastUtils;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2018/11/07
 * desc: 单纯用作测试使用
 * version:1.0
 */
public class TestActivity extends BaseMvpActivity<IBaseView, BaseMvpPresenter<IBaseView>> {

   //需要申请的权限
   private String[] needContactsPermissions = {
           Manifest.permission.READ_PHONE_STATE,
           Manifest.permission.WRITE_EXTERNAL_STORAGE,
           Manifest.permission.READ_CONTACTS,
           Manifest.permission.WRITE_CONTACTS
   };

   @Override
   protected int getLayoutId() {
      return R.layout.activity_test;
   }

   @Override
   protected void initActivity(Bundle savedInstanceState) {
      onRuntimePermissionAsk(needContactsPermissions, new PermissionListener() {
         @Override
         public void onGranted() {
            ToastUtils.INSTANCE.showToast(getApplicationContext(), "授权成功", Toast.LENGTH_SHORT);
            loadData();
         }

         @Override
         public void onDenied(List<String> deniedPermissions) {
            Toast.makeText(getApplicationContext(), "权限未申请成功", Toast.LENGTH_SHORT).show();
         }
      });
   }

   @Override
   protected BaseMvpPresenter<IBaseView> initPresenter() {
      return null;
   }

   @Override
   protected void loadData() {
      Toast.makeText(getApplicationContext(), "请求数据成功", Toast.LENGTH_SHORT).show();
   }


   //   private ValueAnimator animator;
//
//   private ObjectAnimator objectAnimator;
//   private ImageView iv_eye;
//
//   @Override
//   protected void onCreate(@Nullable Bundle savedInstanceState) {
//      super.onCreate(savedInstanceState);
//      setContentView(R.layout.activity_test);
//      iv_eye = findViewById(R.id.test);
////      ToastUtils.INSTANCE.showToast(TestActivity.this, "测试", Toast.LENGTH_SHORT);
//
////      ToastUtils.INSTANCE.showToast(TestActivity.this, "测试成功", Toast.LENGTH_SHORT);
////      SharePreferencesUtils.INSTANCE.saveString(TestActivity.this, "d", "adv");
//
////      animator = ValueAnimator.ofFloat(0, 1);
////      animator.setDuration(1500);
////      animator.addUpdateListener(animation -> {
////         float scale = (float) animation.getAnimatedValue();
////         iv_eye.setScaleX(scale);
////         iv_eye.setScaleY(scale);
////      });
////      animator.start();
////
////      objectAnimator = ObjectAnimator.ofFloat(iv_eye, "alpha", 0, 1);
////      objectAnimator.setDuration(1500);
////      objectAnimator.start();
//   }
}
