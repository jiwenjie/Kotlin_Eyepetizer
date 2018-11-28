package com.example.baselibrary.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.multiple_status_view.MultipleStatusView;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2018/11/04
 * desc:
 * version:1.0
 */
public abstract class BaseMvpFragment<V extends BaseMvpViewImpl, P extends BaseMvpPresenter<V>> extends Fragment {

   protected View mRootView;
   protected P mPresenter;
   /** 这里注意，一开始被绕进去了，不知道该怎么添加监听事件，
    * 一直都没有成功，其实很简单，new 一个 View.OnClickListener 就 OK 了 **/
   protected View.OnClickListener mListener; /** 点击重试的监听 **/

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      mRootView = inflater.inflate(getLayoutId(), container, false);
      return mRootView;
   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      mPresenter = initPresenter();
      /* 注册 lifecycle */
      if (mPresenter != null) getLifecycle().addObserver(mPresenter);
      initFragment(savedInstanceState);
      //多种状态切换的view 重试点击事件
      if (mLayoutStatusView != null) mLayoutStatusView.setOnClickListener(mListener = v -> loadData());
      loadData();
      setListener();
   }

   protected abstract int getLayoutId();

   protected abstract void initFragment(Bundle savedInstanceState);

   protected abstract P initPresenter();

   protected void setListener() {

   }

   /**
    * 多种状态的 View 切换
    */
   protected MultipleStatusView mLayoutStatusView;

   /**
    * 加载数据
    */
   protected abstract void loadData();

}




















