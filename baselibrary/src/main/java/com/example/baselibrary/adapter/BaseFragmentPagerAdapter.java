package com.example.baselibrary.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2018/11/04
 * desc: viewpager adapter 的封装
 * version:1.0
 */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;
    private List<String> mTitles;

    public BaseFragmentPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> titles) {
        super(fm);
        this.mList = list;
        this.mTitles = titles;
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














