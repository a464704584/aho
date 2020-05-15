package com.cy.aho.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cy.aho.R;
import com.cy.aho.base.BaseFragment;
import com.cy.aho.bean.Knowledge;
import com.cy.aho.common.widgets.recyclerView.adapter.HideAdapter;
import com.cy.aho.common.widgets.recyclerView.adapter.MultiItemTypeAdapter;
import com.cy.aho.common.widgets.recyclerView.base.ViewHolder;
import com.cy.aho.viewModel.KnowledgeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @创建者 CY
 * @创建时间 2020/5/13 10:57
 * @描述
 */
public class KnowledgeFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private KnowledgeModel knowledgeModel;

    private HideAdapter<Knowledge> adapter;

    private List<Knowledge> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_knowledge, null, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter = new HideAdapter<Knowledge>(getContext(), R.layout.item_name, data) {
            @Override
            protected void convert(ViewHolder holder, Knowledge knowledge, int position) {
                holder.setText(R.id.tv_title, knowledge.getTitle());
                holder.setText(R.id.tv_time, knowledge.getUpDateTime());
                holder.setText(R.id.tv_content, knowledge.getContent());
                if (adapter.getShowingPosition() == position) {
                    //展开
                    holder.setVisibility(R.id.tv_content, View.VISIBLE);
                    holder.setVisibility(R.id.tv_time, View.GONE);
                } else {
                    //隐藏
                    holder.setVisibility(R.id.tv_content, View.GONE);
                    holder.setVisibility(R.id.tv_time, View.VISIBLE);
                }
            }
        });

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ViewHolder holder, Object o, int position) {
                if (adapter.getShowingPosition() == position) {
                    adapter.setShowingPosition(-1);
                    adapter.notifyItemChanged(position);
                    return;
                }
                if (adapter.getShowingPosition() >= 0) {
                    //如果有展开的item 则关闭
                    adapter.notifyItemChanged(adapter.getShowingPosition());
                }
                //设置新的item 为展示item
                adapter.setShowingPosition(position);
                adapter.notifyItemChanged(adapter.getShowingPosition());
            }

            @Override
            public boolean onItemLongClick(View view, ViewHolder holder, Object o, int position) {
                return false;
            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestName(true);
            }
        });
        requestName(true);
    }





    private void requestName(boolean refresh) {
        knowledgeModel= ViewModelProviders.of(this).get(KnowledgeModel.class);
        knowledgeModel.getData().observe(this,knowledges -> {
            swipeRefresh.setRefreshing(false);
            if (refresh){
                adapter.replaceAllData(knowledges);
            }else {
                adapter.addAllData(knowledges);
            }
        });
    }

}
