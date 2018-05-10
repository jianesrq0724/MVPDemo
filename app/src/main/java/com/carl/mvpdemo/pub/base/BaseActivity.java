package com.carl.mvpdemo.pub.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.carl.mvpdemo.R;
import com.carl.mvpdemo.pub.loading.LoadingDialog;
import com.carl.mvpdemo.pub.loading.interfaces.ILoading;
import com.carl.mvpdemo.pub.utils.ActivityCollector;
import com.carl.mvpdemo.pub.utils.ToolbarManager;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements ILoading {

    public Context mContext;
    public T mPresenter;
    protected boolean isDestroy;
    /**
     * 防止重复点击设置的标志，涉及到点击打开其他Activity时，将该标志设置为false，在onResume事件中设置为true
     */
    private boolean clickable = true;

    private FrameLayout mContentView;
    private Toolbar mToolbar;
    public ToolbarManager mToolbarManager;

    @Override
    protected void onResume() {
        super.onResume();
        clickable = true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载main的布局
        setContentView(R.layout.pub_activity_base);
        //加载子类的布局
        setContentView(getLayoutId());
        mContext = this;
        ActivityCollector.addActivity(this);
        initLoading();
        initToolBar();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        findView();
        initData();
        initView();
        setOnInteractListener();
        isDestroy = false;
    }

    /**
     * 初始化toolBar
     */
    private void initToolBar() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mToolbarManager = new ToolbarManager(mContext, mToolbar, actionBar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goBack();
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 返回
     */
    private void goBack() {
        finish();
    }

    @Override
    public void setContentView(int layoutResID) {
        if (R.layout.pub_activity_base == layoutResID) {
            super.setContentView(R.layout.pub_activity_base);
            mContentView = (FrameLayout) findViewById(R.id.content_view_fl);
            mContentView.removeAllViews();
        } else if (layoutResID != R.layout.pub_activity_base) {
            View addView = LayoutInflater.from(this).inflate(layoutResID, null);
            mContentView.addView(addView);
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        ActivityCollector.removeActivity(this);
        isDestroy = true;
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (clickable) {
            lockClick();
            super.startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 锁定点击
     */
    protected void lockClick() {
        clickable = false;
    }
}
