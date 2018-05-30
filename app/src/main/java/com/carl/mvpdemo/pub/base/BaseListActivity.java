package com.carl.mvpdemo.pub.base;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.carl.mvpdemo.R;

/**
 * @author Carl
 * version 1.0
 * @since 2018/5/30
 */
public class BaseListActivity<V, T extends BasePresenter<V>> extends BaseActivity<V, T> {

    private RecyclerView mRecyclerView;
    protected CommonAdapter mBaseAdapter;

    @Override
    protected void findView() {
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    /**
     * 初始化listView
     */
    protected void initListView() {
        if (mRecyclerView != null && mBaseAdapter != null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            mRecyclerView.setAdapter(mBaseAdapter);
        }
    }

    @Override
    public void setOnInteractListener() {

    }

    @Override
    public T createPresenter() {
        return null;
    }


    @Override
    public int getLayoutId() {
        return 0;
    }
}
