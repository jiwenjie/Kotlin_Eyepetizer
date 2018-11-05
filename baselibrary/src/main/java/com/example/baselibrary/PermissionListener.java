package com.example.baselibrary;

import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2018/11/04
 * desc: 动态权限申请监听
 * version:1.0
 */
public interface PermissionListener {
   void onGranted();

   void onDenied(List<String> deniedPermissions);
}











