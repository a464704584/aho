package com.cy.aho.fragments.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cy.aho.R;
import com.cy.aho.base.BaseBottomSheetFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @创建者 CY
 * @创建时间 2020/5/13 15:56
 * @描述
 */
public class AddKnowledgeFragmentDialog extends BaseBottomSheetFragment {

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_add_knowledge, container, false);
        unbinder=ButterKnife.bind(this,view);
        window=getDialog().getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        window.findViewById(R.id.design_bottom_sheet).setBackground(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams params = window.getAttributes();
        params.windowAnimations=R.style.BottomDialogAnimation;
        window.setAttributes(params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){
            unbinder.unbind();
        }
    }
}
