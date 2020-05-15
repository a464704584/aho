package com.cy.aho.common.widgets.recyclerView.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.cy.aho.common.widgets.recyclerView.base.ViewHolder;

import java.util.List;

/**
 * @创建者 CY
 * @创建时间 2020/5/13 11:06
 * @描述
 */
public abstract class HideAdapter<T> extends CommonAdapter<T> {

    private volatile int showingPosition=-1;

    public HideAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    public HideAdapter(Context context, int layoutId, List<T> datas) {
        super(context, layoutId, datas);
    }

    public int getShowingPosition() {
        return showingPosition;
    }

    public void setShowingPosition(int showingPosition) {
        this.showingPosition = showingPosition;
    }
}
