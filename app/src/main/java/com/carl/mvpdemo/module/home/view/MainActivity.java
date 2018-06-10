package com.carl.mvpdemo.module.home.view;

import android.view.View;
import android.widget.TextView;

import com.carl.mvpdemo.R;
import com.carl.mvpdemo.module.home.interfaces.MainI;
import com.carl.mvpdemo.module.home.presenter.MainPresenter;
import com.carl.mvpdemo.pub.base.BaseListActivity;
import com.carl.mvpdemo.pub.base.CommonBaseAdapter;
import com.carl.mvpdemo.pub.base.CommonSimpleAdapter;
import com.carl.mvpdemo.pub.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public class MainActivity extends BaseListActivity<MainI, MainPresenter> implements MainI {

    private List<String> mTitles = new ArrayList<>();
    TextView mTextView;

    @Override
    public void setOnInteractListener() {

    }

    @Override
    protected void initData() {
//        mTitles.add("RecyclerView封装和刷新");
//        mTitles.add("耗时等待转圈");
        mTitles.add("getSms");
    }

    @Override
    protected void initView() {
        mToolbarManager.hideBackIcon();
        mToolbarManager.setToolbarTitle("MVP Demo");

        mBaseAdapter = new CommonSimpleAdapter(mTitles);
        initListView();

        mBaseAdapter.setOnItemClickListener(new CommonBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        mPresenter.getSms("18410101208");
//                        TestListActivity.startActivity(mContext);
                        break;
                    case 1:
//                        mPresenter.testLogin();
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
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
    protected void getFirstData() {

    }

    @Override
    protected void onLoad() {

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
