package com.bw.movie.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: Xuexiandong
 * data: 2019/9/27 17:17:21
 * function：
 */
public class RxJavaUtil {
    private static RxJavaUtil rxJavaUtil = null;
    private final IApi iApi;

    private RxJavaUtil() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient ok = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.URL_BASE)
                .client(ok)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        iApi = retrofit.create(IApi.class);
    }
    //供外部调用
    public IApi getIApi(){
        return iApi;
    }

    public static RxJavaUtil getInstance() {
        if (rxJavaUtil == null) {
            synchronized (RxJavaUtil.class) {
                if (rxJavaUtil == null) {
                    rxJavaUtil = new RxJavaUtil();
                }
            }
        }
        return rxJavaUtil;
    }
}
