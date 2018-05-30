package com.carl.mvpdemo.pub.base.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carl.mvpdemo.pub.base.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carl
 * version 1.0
 * @since 2018/5/29
 */
public abstract class CommonBaseAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    /**
     * 普通Item View
     */
    protected static final int TYPE_ITEM = 0;

    /**
     * 顶部FootView
     */
    protected static final int TYPE_FOOTER = 1;


    protected List<T> mDataList;
    private OnItemClickListener mOnItenClickListener = null;

    public CommonBaseAdapter(List<T> mDataList) {
        if (mDataList == null) {
            this.mDataList = new ArrayList<>();
        } else {
            this.mDataList = mDataList;
        }
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        final CommonViewHolder commonViewHolder = new CommonViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItenClickListener != null) {
                    mOnItenClickListener.onItemClick(v, commonViewHolder.getAdapterPosition());
                }
            }
        });

        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FOOTER) {

        } else {
            conner(holder, mDataList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }


    public abstract int getLayoutId();

    public abstract void conner(final CommonViewHolder holder, final T entity);

    /**
     * 点击事件
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItenClickListener = listener;
    }

    public void update(List<T> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void addAll(List<T> dataList) {
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
