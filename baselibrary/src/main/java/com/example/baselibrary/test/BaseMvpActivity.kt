//package com.example.baselibrary.test
//
///**
// * author:Jiwenjie
// * email:278630464@qq.com
// * time:2018/12/15
// * desc:
// * version:1.0
// */
//
//import android.annotation.TargetApi
//import android.app.Activity
//import android.content.pm.PackageManager
//import android.graphics.Color
//import android.os.Build
//import android.os.Bundle
//import android.support.v4.app.ActivityCompat
//import android.support.v4.content.ContextCompat
//import android.support.v7.app.AppCompatActivity
//import android.view.View
//import com.example.baselibrary.ActivityController
//import com.example.baselibrary.PermissionListener
//import com.example.baselibrary.views.BaseMvpPresenter
//import com.example.baselibrary.views.BaseMvpViewImpl
//import com.example.multiple_status_view.MultipleStatusView
//
//import java.util.ArrayList
//
///**
// * author:Jiwenjie
// * email:278630464@qq.com
// * time:2018/11/04
// * desc:
// * version:1.0
// */
//abstract class BaseMvpActivity<V : BaseMvpViewImpl, P : BaseMvpPresenter<V>> : AppCompatActivity() {
//    private var permissionListener: PermissionListener? = null
//    protected var mPresenter: P? = null
//    /** 点击重试的监听  */
//    protected var listener: View.OnClickListener
//
//    protected abstract val layoutId: Int
//
//    /**
//     * 多种状态的 View 切换
//     */
//    protected var mLayoutStatusView: MultipleStatusView? = null
//
//    @TargetApi(21)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        if (enableTransparentStatus()) {
//            val decorView = window.decorView
//            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
//                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            if (supportActionBar != null) supportActionBar!!.hide()
//            // 判断当前版本如果在 21 以上，大于 5.0，则设置以下方法，否则不设置
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                window.navigationBarColor = Color.TRANSPARENT
//                window.statusBarColor = Color.TRANSPARENT
//            }
//        }
//
//        ActivityController.addActivity(this)
//        setContentView(layoutId)
//        mPresenter = initPresenter()
//        /* 注册 lifecycle */
//        if (mPresenter != null) lifecycle.addObserver(mPresenter!!)
//        initActivity(savedInstanceState)
//        if (mLayoutStatusView != null) mLayoutStatusView!!.setOnClickListener(listener = { view -> loadData() })
//        loadData()
//        setListener()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        ActivityController.removeActivity(this)
//    }
//
//    protected abstract fun initActivity(savedInstanceState: Bundle?)
//
//    protected abstract fun initPresenter(): P
//
//    protected abstract fun loadData()
//
//    protected fun enableTransparentStatus(): Boolean {
//        return false
//    }
//
//    protected fun setListener() {
//
//    }
//
//    /**
//     * 动态申请权限
//     */
//    protected fun onRuntimePermissionAsk(permissions: Array<String>, listener: PermissionListener) {
//        val topActivity = ActivityController.getTopActivity()
//        val deniedPermissions = ArrayList<String>()
//        permissionListener = listener
//
//        if (topActivity != null) {
//            for (p in permissions) {
//                if (ContextCompat.checkSelfPermission(topActivity, p) != PackageManager.PERMISSION_GRANTED) {
//                    deniedPermissions.add(p)
//                }
//            }
//
//            /* 全部同意则处理下一步 */
//            if (deniedPermissions.isEmpty()) {
//                listener.onGranted()
//            } else {
//                ActivityCompat.requestPermissions(
//                    topActivity,
//                    deniedPermissions.toTypedArray(),
//                    PERMISSION_REQUEST_CODE
//                )
//            }
//        }
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            PERMISSION_REQUEST_CODE -> {
//                val deniedPermissionList = ArrayList<String>()
//                if (grantResults.size > 0) {
//                    for (i in grantResults.indices) {
//                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
//                            deniedPermissionList.add(permissions[i])
//                        }
//                    }
//
//                    if (deniedPermissionList.isEmpty()) {
//                        permissionListener!!.onGranted()
//                    } else {
//                        permissionListener!!.onDenied(deniedPermissionList)
//                    }
//                }
//            }
//            else -> {
//            }
//        }
//    }
//
//    companion object {
//
//        private val PERMISSION_REQUEST_CODE = 0
//    }
//}
//
//
