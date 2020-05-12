package com.cy.aho.common.widgets.recyclerView.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import com.cy.aho.common.widgets.recyclerView.base.ItemViewDelegate;
import com.cy.aho.common.widgets.recyclerView.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 CY
 * @创建时间 2020/3/16 16:46
 * @描述
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T>{
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mData;
    protected LayoutInflater mInflater;

    public CommonAdapter(Context context, final int layoutId) {
        this(context, layoutId, new ArrayList<T>());
    }

    public CommonAdapter(Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        mContext=context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mData = datas;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return mLayoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T item, int position) {
                CommonAdapter.this.convert(holder, item, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);
}
