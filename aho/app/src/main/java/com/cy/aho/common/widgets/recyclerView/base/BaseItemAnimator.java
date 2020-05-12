package com.cy.aho.common.widgets.recyclerView.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

/**
 * @创建者 CY
 * @创建时间 2020/3/18 13:19
 * @描述
 */
public class BaseItemAnimator extends SimpleItemAnimator {

    //Item移除回调
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        return false;
    }

    //Item添加回调
    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        return false;
    }

    //用于控制添加，删除，更新时，其它Item的动画执行
    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        return false;
    }

    //Item更新回调
    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        return false;
    }

    //真正控制执行动画的地方
    @Override
    public void runPendingAnimations() {

    }
    //停止某个Item的动画
    @Override
    public void endAnimation(@NonNull RecyclerView.ViewHolder item) {

    }


    //停止所有动画
    @Override
    public void endAnimations() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
