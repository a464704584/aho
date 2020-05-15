package com.cy.aho.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.cy.aho.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * @创建者 CY
 * @创建时间 2020/5/14 15:01
 * @描述
 */
public abstract class BaseBottomSheetFragment extends BottomSheetDialogFragment {

    //顶部向下偏移量
    private int topOffset = 0;
    private BottomSheetBehavior behavior;
    protected Window window;




    @Override
    public void onStart() {
        super.onStart();
        window.findViewById(R.id.design_bottom_sheet).setBackground(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams params = window.getAttributes();
        params.windowAnimations=R.style.BottomDialogAnimation;
        window.setAttributes(params);
    }

    /**
     * 获取屏幕高度
     *
     * @return height
     */
    private int getHeight() {
        int height = 1920;
        if (getContext() != null) {
            WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            Point point = new Point();
            if (wm != null) {
                // 使用Point已经减去了状态栏高度
                wm.getDefaultDisplay().getSize(point);
                height = point.y - getTopOffset();
            }
        }
        return height;
    }
    public int getTopOffset() {
        return topOffset;
    }
    public void setTopOffset(int topOffset) {
        this.topOffset = topOffset;
    }
    public BottomSheetBehavior<FrameLayout> getBehavior() {
        return behavior;
    }


}
