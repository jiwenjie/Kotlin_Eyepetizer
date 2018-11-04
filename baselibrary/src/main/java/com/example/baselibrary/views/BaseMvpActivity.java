package com.example.baselibrary.views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.baselibrary.ActivityController;
import com.example.baselibrary.PermissionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2018/11/04
 * desc:
 * version:1.0
 */
public abstract class BaseMvpActivity<V extends BaseMvpViewImpl, P extends BaseMvpPresenter<V>>
        extends AppCompatActivity  {

    private static final int PERMISSION_REQUEST_CODE = 0X10000000;
    private PermissionListener permissionListener;
    protected P mPresenter;

    @TargetApi(21)

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (enableTransparentStatus()) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            if (getSupportActionBar() != null) getSupportActionBar().hide();
        }

        ActivityController.addActivity(this);
        setContentView(getLayoutId());
        mPresenter = initPresenter();
        /* 注册 lifecycle */
        getLifecycle().addObserver(mPresenter);
        initActivity(savedInstanceState);
        setListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    protected abstract int getLayoutId();

    protected abstract void initActivity(Bundle savedInstanceState);

    protected abstract P initPresenter();

    protected boolean enableTransparentStatus() { return false; }

    protected void setListener() {

    }

    /**
     * 动态申请权限
     */
    protected void onRuntimePermissionAsk(String[] permissions, PermissionListener listener) {
        Activity topActivity = ActivityController.getTopActivity();
        List<String> deniedPermissions = new ArrayList<>();
        permissionListener = listener;

        if (topActivity != null) {
            for (String p : permissions) {
                if (ContextCompat.checkSelfPermission(topActivity, p) != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(p);
                }
            }

            /* 全部同意则处理下一步 */
            if (deniedPermissions.isEmpty()) {
                listener.onGranted();
            } else {
                ActivityCompat.requestPermissions(topActivity,
                        deniedPermissions.toArray(new String[deniedPermissions.size()]),
                        PERMISSION_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                List<String> deniedPermissionList = new ArrayList<>();
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissionList.add(permissions[i]);
                        }
                    }

                    if (deniedPermissionList.isEmpty()) {
                        permissionListener.onGranted();
                    } else {
                        permissionListener.onDenied(deniedPermissionList);
                    }
                }
                break;
            default:
                break;
        }
    }
}


























