package com.cy.aho.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cy.aho.bean.Knowledge;
import com.cy.aho.common.bean.BaseResponse;
import com.cy.aho.common.db.AppDatabaseUtil;
import com.cy.aho.common.widgets.retrofit.API;
import com.cy.aho.common.widgets.retrofit.RestObserverBack;
import com.cy.aho.common.widgets.retrofit.RetrofitManager;
import com.cy.aho.common.widgets.retrofit.RxRetrofitHelper;
import com.cy.aho.dao.KnowledgeDao;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @创建者 CY
 * @创建时间 2020/5/14 13:18
 * @描述
 */
public class KnowledgeModel extends ViewModel {

    private LiveData<List<Knowledge>> data;


    public LiveData<List<Knowledge>> getData(){
        if (data==null){
            data= new MutableLiveData();
            loadKnowledge();
        }
        return data;
    }


    private void loadKnowledge(){
        Observable.create(new ObservableOnSubscribe<List<Knowledge>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Knowledge>> e) throws Exception {
                KnowledgeDao knowledgeDao= AppDatabaseUtil.getDatabase().getKnowledgeDao();
                List<Knowledge> knowledges=knowledgeDao.get();
                if (!knowledges.isEmpty()){
                    e.onNext(knowledges);
                }

                RxRetrofitHelper.create(RetrofitManager.create(API.class).listKnowledgeByType(null))
                        .observeOn(Schedulers.io())
                        .subscribe(new RestObserverBack<BaseResponse<List<Knowledge>>>() {
                            @Override
                            protected void onSuccess(BaseResponse<List<Knowledge>> listBaseResponse) {
                                if (!listBaseResponse.getBody().isEmpty()){
                                    knowledgeDao.delAll();
                                    knowledgeDao.insertAll(listBaseResponse.getBody());
                                }
                                e.onNext(listBaseResponse.getBody());
                            }

                            @Override
                            protected void onError(String message) {

                            }
                        });
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Knowledge>>() {
                    @Override
                    public void accept(List<Knowledge> knowledges) throws Exception {
                        ((MutableLiveData)data).setValue(knowledges);
                    }
                });
    }


}
