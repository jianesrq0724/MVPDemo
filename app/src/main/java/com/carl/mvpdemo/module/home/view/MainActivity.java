package com.carl.mvpdemo.module.home.view;

import android.view.View;
import android.widget.Button;

import com.carl.mvpdemo.R;
import com.carl.mvpdemo.pub.base.BaseActivity;
import com.carl.mvpdemo.module.home.interfaces.MainI;
import com.carl.mvpdemo.module.home.presenter.MainPresenter;
import com.carl.mvpdemo.pub.utils.ToastUtils;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public class MainActivity extends BaseActivity<MainI, MainPresenter> implements MainI {

    private Button mButton;


    @Override
    protected void findView() {
        mButton = findViewById(R.id.button);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mToolbarManager.hideBackIcon();
        mToolbarManager.setToolbarTitle("MVP Demo");
    }

    @Override
    public void setOnInteractListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.testLogin();
            }
        });
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void loginSuccess(String msg) {
        ToastUtils.showShort(msg);
    }

    private long lastClickTime;

    @Override
    public void onBackPressed() {
        long currentClickTime = System.currentTimeMillis();
        if (currentClickTime - lastClickTime > 2000) {
            ToastUtils.showShort("再按一次退出");
            lastClickTime = currentClickTime;
        } else {
            super.onBackPressed();
        }
    }
}
