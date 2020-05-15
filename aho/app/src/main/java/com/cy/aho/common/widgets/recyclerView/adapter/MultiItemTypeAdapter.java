package com.cy.aho.common.widgets.recyclerView.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.cy.aho.common.widgets.recyclerView.base.ViewHolder;
import com.cy.aho.common.widgets.recyclerView.base.ItemViewDelegate;
import com.cy.aho.common.widgets.recyclerView.base.ItemViewDelegateManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 CY
 * @创建时间 2020/3/16 16:29
 * @描述
 */
public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private List<T> mData;
    protected ItemViewDelegateManager mItemViewDelegateManager;
    protected OnItemClickListener<T> mOnItemClickListener;

    public int offset = 0;

    public MultiItemTypeAdapter(Context context, List<T> data) {
        mContext = context;
        mData = data;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    public MultiItemTypeAdapter(Context context) {
        this(context, new ArrayList<T>());
    }


    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager()) return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(mData.get(position), position);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mItemViewDelegateManager.getItemViewLayoutId(viewType);
        ViewHolder holder = ViewHolder.createViewHolder(mContext, parent, layoutId);
        setListener(parent, holder, viewType);
        return holder;
    }

    public void convert(ViewHolder holder, T t) {
        mItemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }


    protected void setListener(final ViewGroup parent, final ViewHolder viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, viewHolder, mData.get(position - offset), position);
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    return mOnItemClickListener.onItemLongClick(v, viewHolder, mData.get(position - offset), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public List<T> getData() {
        return mData;
    }

    public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public MultiItemTypeAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    protected boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, ViewHolder holder, T o, int position);
        boolean onItemLongClick(View view, ViewHolder holder, T o, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void addAllData(List data) {
        mData.addAll(data);
        notifyItemRangeChanged(getItemCount()-data.size(),data.size());
    }

    public void replaceAllData(List data) {
        mData.clear();
        mData.addAll(data);
        notifyItemRangeChanged(getItemCount()-data.size(),data.size());
    }

    public void addData(int position,T t){
        mData.add(position,t);
        notifyItemInserted(position);
    }

    public void addData(T t){
        mData.add(t);
        notifyItemInserted(getItemCount()-1);
    }

    public void removeData(int position){
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }

}
