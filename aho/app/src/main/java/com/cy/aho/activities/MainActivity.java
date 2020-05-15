package com.cy.aho.activities;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cy.aho.R;
import com.cy.aho.base.BaseActivity;
import com.cy.aho.base.BaseFragment;
import com.cy.aho.fragments.KnowledgeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fragment_box)
    FrameLayout fragmentBox;


    private BaseFragment knowledgeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        knowledgeFragment=new KnowledgeFragment();

        if (savedInstanceState!=null){
            FragmentManager manager= getSupportFragmentManager();
            knowledgeFragment=(KnowledgeFragment) manager.findFragmentByTag(KnowledgeFragment.class.getSimpleName());
        }else {

        }

        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.fragment_box,knowledgeFragment,knowledgeFragment.getClass().getSimpleName());
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }
    }
}
