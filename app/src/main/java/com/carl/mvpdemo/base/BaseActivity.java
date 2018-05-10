package com.carl.mvpdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.carl.mvpdemo.interfaces.ILoading;
import com.carl.mvpdemo.loading.LoadingDialog;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements ILoading {

    public Context mContext;
    public T mPresenter;
    private int mLayoutId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = this;
        initLoading();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V)this);
        }
        findView();
        initData();
        initView();
        setOnInteractListener();
    }

    protected abstract void findView();

    protected abstract void initData();

    protected abstract void initView();


    public abstract void setOnInteractListener();

    public abstract T createPresenter();

    public abstract int getLayoutId();

    LoadingDialog mLoadingDialog;

    /**
     * 初始化进度条
     */
    private void initLoading() {
        mLoadingDialog = new LoadingDialog(mContext);
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.show();
        }
    }

    @Override
    public void dismissLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }


}
