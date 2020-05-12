package com.cy.aho.common.widgets.retrofit;

import android.content.Context;

import com.cy.aho.common.bean.BaseResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @创建者 CY
 * @创建时间 2020/4/2 15:56
 * @描述
 */
public abstract class RestObserverBack<T> implements Observer<T> {
    private boolean isShowDialog=false;
    private String dialogMessage=null;
    private Context context;

    public RestObserverBack(){
        this(null);
    }

    public RestObserverBack(Context context){
        this.context=context;
    }


    @Override
    public void onSubscribe(Disposable disposable) {
        if (isShowDialog&&context!=null){
//            progressDialog=new MyProgressDialog(context);
//            progressDialog.show();
        }
    }

    @Override
    public void onNext(T t) {
        BaseResponse baseResponse=(BaseResponse)t;
        if (baseResponse.getCode()==-1){
            onError(baseResponse.getMsg());
        }else {
            onSuccess(t);
        }
    }

    protected abstract void onSuccess(T t);

    @Override
    public void onError(Throwable e) {
//        onError(e.getMessage());
        e.printStackTrace();
    }

    protected abstract void onError(String message);

    @Override
    public  void onComplete(){
//        if (progressDialog!=null){
//            progressDialog.dismiss();
//        }
    }


    public RestObserverBack<T> setShowDialog(boolean showDialog) {
        isShowDialog = showDialog;
        return this;
    }

    public RestObserverBack<T> setDialogMessage(String dialogMessage) {
        this.dialogMessage = dialogMessage;
        return this;
    }
}
