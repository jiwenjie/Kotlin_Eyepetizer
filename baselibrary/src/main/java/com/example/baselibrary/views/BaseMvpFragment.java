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
      loadData();
      setListener();
   }

   protected abstract int getLayoutId();

   protected abstract void initFragment(Bundle savedInstanceState);

   protected abstract P initPresenter();

   protected void setListener() {
      mRetryClickListener listener = v -> loadData();
      if (mLayoutStatusView != null) mLayoutStatusView.setOnRetryClickListener(listener);
   }

   /**
    * 多种状态的 View 切换
    */
   protected MultipleStatusView mLayoutStatusView;

   private interface mRetryClickListener extends View.OnClickListener { }

   /**
    * 加载数据
    */
   protected abstract void loadData();

}




















