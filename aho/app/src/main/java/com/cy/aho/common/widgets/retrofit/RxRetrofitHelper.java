package com.cy.aho.common.widgets.retrofit;


import io.reactivex.Observable;

/**
 * @创建者 CY
 * @创建时间 2020/4/2 14:15
 * @描述
 */
public class RxRetrofitHelper {
    public static<T> Observable<T> create(Observable<T> observable){
        return observable
                .compose(new ThreadTransformer());
    }
}
