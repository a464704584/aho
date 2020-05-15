package com.cy.aho.common.widgets.retrofit;

import com.cy.aho.bean.Knowledge;
import com.cy.aho.bean.KnowledgeType;
import com.cy.aho.common.bean.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @创建者 CY
 * @创建时间 2019/8/21 19:45
 * @描述
 */
public interface API {

    @GET("k/skt")
    Observable<BaseResponse<List<KnowledgeType>>> listKnowledgeType(@Query("parentId")String parentId);


    @GET("k/sk")
    Observable<BaseResponse<List<Knowledge>>> listKnowledgeByType(@Query("typeId")String typeId);
}
