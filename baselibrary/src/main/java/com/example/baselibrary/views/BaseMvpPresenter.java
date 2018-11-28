package com.example.baselibrary.views;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2018/11/04
 * desc:Presenter 基类，实现 lifecycler 用于绑定 Activity 和 Fragment 生命周期
 * version:1.0
 */
public abstract class BaseMvpPresenter<V extends BaseMvpViewImpl>
        implements LifecycleObserver {

    /** 防止内存泄漏调用 **/
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected static final String TAG = BaseMvpPresenter.class.getSimpleName();
    /* Activity Fragment 对应的 view 接口 */
    protected V mView;

    /**
     * 构造函数不添加 Model 实例
     * 需要在 View 中初始化 Presenter，如果添加 Model 实例，则 View Model 耦合
     */
    public BaseMvpPresenter(V view) {
        this.mView = view;
    }

    /**
     * 添加 disposable, 主要防止 RxJava 内存泄漏
     */
    protected void addSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate() {
        Log.d(TAG, "onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onStart() {
        Log.d(TAG, "onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void onResume() {
        Log.d(TAG, "onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void onPause() {
        Log.d(TAG, "onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void onStop() {
        Log.d(TAG, "onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
        this.mView = null;
        // 保证 activity 结束的时候取消所有正在执行的订阅
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
        Log.d(TAG, "onDestroy");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    protected void onLifeChange(LifecycleOwner owner, Lifecycle.Event event) {
        Log.d(TAG, "onLifeChange: (" + owner + ", " + event + ")");
    }
}


