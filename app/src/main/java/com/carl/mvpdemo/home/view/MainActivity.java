package com.carl.mvpdemo.home.view;

import android.view.View;
import android.widget.Button;

import com.carl.mvpdemo.R;
import com.carl.mvpdemo.base.BaseActivity;
import com.carl.mvpdemo.home.interfaces.MainI;
import com.carl.mvpdemo.home.presenter.MainPresenter;
import com.carl.mvpdemo.utils.ToastUtils;

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
}
