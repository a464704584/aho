package com.cy.aho.common.widgets.recyclerView.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cy.aho.common.utils.GlideUtil;

/**
 * @创建者 CY
 * @创建时间 2020/3/16 15:50
 * @描述
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private Context mContext;
    private View mConvertView;

    private ViewHolder(Context context,@NonNull View itemView) {
        super(itemView);
        mContext=context;
        mConvertView=itemView;
        mViews=new SparseArray<>();
    }


    public static ViewHolder createViewHolder(Context context,View itemView){
        return new ViewHolder(context,itemView);
    }


    public static ViewHolder createViewHolder(Context context,ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        return new ViewHolder(context, itemView);
    }

    private  <T extends View>T getView(int viewId){
        View view=mViews.get(viewId);
        if (view==null){
            view=mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }


    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(""+text);
        return this;
    }

    public ViewHolder setText(int viewId, Object text) {
        TextView tv = getView(viewId);
        tv.setText(""+text);
        return this;
    }

    public ViewHolder setTextColor(int viewId,int colorId){
        TextView tv = getView(viewId);
        tv.setTextColor(colorId);
        return this;
    }


    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public ViewHolder setImageByGlide(int viewId,String url){
        ImageView view = getView(viewId);
        GlideUtil.load(mContext,url,view);
        return this;
    }


    public ViewHolder setOnClickListener(int viewId, View.OnClickListener clickListener){
        getView(viewId).setOnClickListener(clickListener);
        return this;
    }


    public ViewHolder setVisibility(int viewId,int visibility){
        getView(viewId).setVisibility(visibility);
        return this;
    }







}
