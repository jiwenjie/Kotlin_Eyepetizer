package com.example.baselibrary.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2018/11/04
 * desc: viewpager adapter 的封装
 * tips: 该类内的每一个生成的 Fragment 都将保存在内存中，
 * 因此适用于那些相对静态的页，数量页比较少，
 * 如果需要处理很多页 且 数据动态性较大，占用内存较多的情况下，
 * 应该使用 FragmentStatePagerAdapter
 * version:1.0
 */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;
    private List<String> mTitles;

    public BaseFragmentPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> titles) {
        super(fm);
//        this.mList = list;
        this.mTitles = titles;
        setFragments(fm, list, titles);
    }

    // 刷新 fragment
    private void setFragments(FragmentManager fm, List<Fragment> fragments, List<String> mTitles) {
        this.mTitles = mTitles;
        if (this.mList != null) {
            FragmentTransaction ft = fm.beginTransaction();
            for (Fragment fragment : mList) {
                ft.remove(fragment);
            }
            ft.commitAllowingStateLoss();
            fm.executePendingTransactions();
        }
        this.mList = fragments;
        notifyDataSetChanged();
    }
    
    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? super.getPageTitle(position) : mTitles.get(position);
    }
}














