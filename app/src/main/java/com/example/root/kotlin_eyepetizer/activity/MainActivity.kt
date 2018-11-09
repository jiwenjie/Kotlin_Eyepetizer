package com.example.root.kotlin_eyepetizer.activity

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import android.widget.Toast
//import com.example.baselibrary.util.ToastUtils
import com.example.baselibrary.views.BaseMvpPresenter
import com.example.root.kotlin_eyepetizer.R
import com.example.root.kotlin_eyepetizer.base.BaseActivity
import com.example.root.kotlin_eyepetizer.base.IBaseView
import com.example.root.kotlin_eyepetizer.base.TabEntity
import com.example.root.kotlin_eyepetizer.fragment.DiscoveryFragment
import com.example.root.kotlin_eyepetizer.fragment.HotFragment
import com.example.root.kotlin_eyepetizer.fragment.IndexFragment
import com.example.root.kotlin_eyepetizer.fragment.MineFragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_main.*

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 */
class MainActivity : BaseActivity<IBaseView, BaseMvpPresenter<IBaseView>>() {

    private val mTitles = arrayOf("每日精选", "发现", "热门", "我的")

    // 未被选中的图标
    private val mIconUnSelected = intArrayOf(
        R.mipmap.ic_home_normal,
        R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal
    )
    // 被选中的图标
    private val mIconSelected = intArrayOf(
        R.mipmap.ic_home_selected,
        R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected
    )

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mIndexFragment: IndexFragment? = null
    private var mDiscoveryFragment: DiscoveryFragment? = null
    private var mHotFragment: HotFragment? = null
    private var mMineFragment: MineFragment? = null

    // 默认为 0
    private var mIndex = 0

    override fun initActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currentIndex")
        }
        initTab()
        tab_layout.currentTab = mIndex
        switchFragment(mIndex)
    }

    /**
     * 初始化底部菜单
     */
    private fun initTab() {
        (0 until mTitles.size).mapTo(mTabEntities) {
            // 映射转换类型，转换成 mapTo 括号中的类型
            TabEntity(mTitles[it], mIconSelected[it], mIconUnSelected[it]) }
        // 给 tab 赋值
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                // 切换 Fragment
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    /**
     * 切换不同的 Fragment
     * position 为下标
     */
    private fun switchFragment(position: Int) {
        val transition = supportFragmentManager.beginTransaction()
        hideFragment(transition)
        when (position) {
            0 -> mIndexFragment?.let { transition.show(it) } ?: IndexFragment.getInstance(mTitles[position]).let {
                mIndexFragment = it
                transition.add(R.id.fl_main, it, "index")
            }
            1 -> mDiscoveryFragment?.let { transition.show(it) } ?: DiscoveryFragment.getInstance(mTitles[position]).let {
                mDiscoveryFragment = it
                transition.add(R.id.fl_main, it, "discovery")
            }
            2 -> mHotFragment?.let { transition.show(it) } ?: HotFragment.getInstance(mTitles[position]).let {
                mHotFragment = it
                transition.add(R.id.fl_main, it, "hot")
            }
            3 -> mMineFragment?.let { transition.show(it) } ?: MineFragment.getInstance(mTitles[position]).let {
                mMineFragment = it
                transition.add(R.id.fl_main, it, "mine")
            }

            else -> {

            }
        }

        mIndex = position
        tab_layout.currentTab = mIndex
        transition.commitAllowingStateLoss()

    }

    /**
     * 隐藏所有的 Fragment
     */
    private fun hideFragment(transaction: FragmentTransaction) {
        mHotFragment?.let { transaction.hide(it) }
        mDiscoveryFragment?.let { transaction.hide(it) }
        mIndexFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initPresenter(): BaseMvpPresenter<IBaseView>? {
        return null
    }

    /**
     * 网络请求获取数据的方法，点击重试也是调用此方法
     */
    override fun loadData() {

    }

    private var mExitTime: Long = 0
    /**
     * 两次点击退出程序
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                Toast.makeText(applicationContext, "再按一次退出程序", Toast.LENGTH_SHORT).show()
//                ToastUtils.showCenterToast(applicationContext, "再按一次退出程序", Toast.LENGTH_SHORT)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}





