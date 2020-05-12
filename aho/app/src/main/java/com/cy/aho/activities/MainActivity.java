package com.cy.aho.activities;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cy.aho.R;
import com.cy.aho.base.BaseActivity;
import com.cy.aho.bean.KnowledgeType;
import com.cy.aho.common.bean.BaseResponse;
import com.cy.aho.common.widgets.recyclerView.adapter.CommonAdapter;
import com.cy.aho.common.widgets.recyclerView.adapter.MultiItemTypeAdapter;
import com.cy.aho.common.widgets.recyclerView.base.ViewHolder;
import com.cy.aho.common.widgets.retrofit.API;
import com.cy.aho.common.widgets.retrofit.RestObserverBack;
import com.cy.aho.common.widgets.retrofit.RetrofitManager;
import com.cy.aho.common.widgets.retrofit.RxRetrofitHelper;
import com.google.android.material.bottomappbar.BottomAppBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.bottom_app_bar)
    BottomAppBar bottomAppBar;

    private CommonAdapter<KnowledgeType> adapter;

    private List<KnowledgeType> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter=new CommonAdapter<KnowledgeType>(this,R.layout.item_name,data) {

            @Override
            protected void convert(ViewHolder holder, KnowledgeType knowledgeType, int position) {
                holder.setText(R.id.tv_name,knowledgeType.getName());
            }
        });

        requestName();
    }

    private void requestName(){
        RxRetrofitHelper.create(RetrofitManager.create(API.class).listKnowledgeType(null)).subscribe(new RestObserverBack<BaseResponse<List<KnowledgeType>>>() {
            @Override
            protected void onSuccess(BaseResponse<List<KnowledgeType>> listBaseResponse) {
                data.addAll(listBaseResponse.getBody());
                adapter.notifyDataSetChanged();
            }

            @Override
            protected void onError(String message) {

            }
        });
    }

}
