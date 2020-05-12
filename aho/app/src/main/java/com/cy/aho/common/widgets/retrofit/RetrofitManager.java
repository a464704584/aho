package com.cy.aho.common.widgets.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @创建者 CY
 * @创建时间 2019/8/21 19:45
 * @描述
 */

public class RetrofitManager {

    private static int CACHE_SIZE = 10 * 1024 * 1024; //緩存大小

    private static int CONNECT_TIME_OUT = 10;    //連接超時

    private static int READ_TIME_OUT = 10; //讀取超時

    private static int WRITE_TIME_OUT = 10; //寫入超時

    private static volatile OkHttpClient sOkHttpClient;

    private static OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (sOkHttpClient == null) {
                    sOkHttpClient = new OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)//默认重试一次，
                            .addInterceptor(new MyHttpInterceptor())
                            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return sOkHttpClient;
    }

    public static <T> T create(Class<T> tClass) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(APIConst.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(tClass);
    }
}
