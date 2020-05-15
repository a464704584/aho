package com.cy.aho.base;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


/**
 * @创建者 CY
 * @创建时间 2019/9/12 13:10
 * @描述
 */
public class BaseDialogFragment extends DialogFragment {

    protected final String TAG=getClass().getSimpleName();

    protected View rootView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i(TAG,"onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.i(TAG,"onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG,"onPause");
        super.onPause();
    }
}
