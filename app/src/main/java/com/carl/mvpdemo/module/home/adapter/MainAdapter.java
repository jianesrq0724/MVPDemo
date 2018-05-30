package com.carl.mvpdemo.module.home.adapter;

import android.view.View;

import com.carl.mvpdemo.R;
import com.carl.mvpdemo.pub.base.CommonAdapter;
import com.carl.mvpdemo.pub.base.CommonViewHolder;
import com.carl.mvpdemo.pub.utils.ToastUtils;

import java.util.List;

/**
 * @author Carl
 * version 1.0
 * @since 2018/5/29
 */
public class MainAdapter extends CommonAdapter<String> {

    public MainAdapter(List<String> mDataList) {
        super(mDataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.pub_item_recyclerview;
    }

    @Override
    public void conner(final CommonViewHolder holder, final String entity) {
        holder.setText(R.id.content_tv, entity);
    }
}
