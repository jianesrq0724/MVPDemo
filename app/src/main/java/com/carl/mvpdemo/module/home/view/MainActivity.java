package com.carl.mvpdemo.module.home.view;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.carl.mvpdemo.R;
import com.carl.mvpdemo.module.home.adapter.MainBaseAdapter;
import com.carl.mvpdemo.module.home.interfaces.MainI;
import com.carl.mvpdemo.module.home.presenter.MainPresenter;
import com.carl.mvpdemo.module.test.Test1Activity;
import com.carl.mvpdemo.pub.base.BaseActivity;
import com.carl.mvpdemo.pub.base.adapter.CommonBaseAdapter;
import com.carl.mvpdemo.pub.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public class MainActivity extends BaseActivity<MainI, MainPresenter> implements MainI {

    private RecyclerView mRecyclerView;

    private List<String> mTitles = new ArrayList<>();

    @Override
    protected void findView() {
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {

        for (int i = 0; i < 20; i++) {
            mTitles.add(String.valueOf((char) ('A' + i)));
        }
    }

    @Override
    protected void initView() {
        mToolbarManager.hideBackIcon();
        mToolbarManager.setToolbarTitle("MVP Demo");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        MainBaseAdapter mainAdapter = new MainBaseAdapter(mTitles);
        mRecyclerView.setAdapter(mainAdapter);

        mainAdapter.setOnItemClickListener(new CommonBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showLong(mTitles.get(position));
                Test1Activity.startActivity(mContext);
            }
        });

    }

    @Override
    public void setOnInteractListener() {
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPresenter.testLogin();
//            }
//        });
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
