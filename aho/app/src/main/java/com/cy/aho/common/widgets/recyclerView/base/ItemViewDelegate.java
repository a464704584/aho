package com.cy.aho.common.widgets.recyclerView.base;

/**
 * @创建者 CY
 * @创建时间 2020/3/16 16:37
 * @描述
 */
public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}