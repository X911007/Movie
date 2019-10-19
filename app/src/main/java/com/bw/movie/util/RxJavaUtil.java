package com.bw.movie.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bw.movie.database.DaoMaster;
import com.bw.movie.database.DaoSession;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: Xuexiandong
 * data: 2019/9/27 17:17:21
 * function：工具类
 */
public class RxJavaUtil {
    private static RxJavaUtil rxJavaUtil = null;
    private final IApi iApi;
    private final DaoSession daoSession;

    private RxJavaUtil() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient ok = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.URL_BASE)
                .client(ok)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        iApi = retrofit.create(IApi.class);
        //创建数据库
        daoSession = DaoMaster.newDevSession(App.sContext, "bw.db");
    }

    //供外部调用
    public IApi getIApi() {
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

    //供外部调用数据库
    public DaoSession getDaoSession() {
        return daoSession;
    }
    //判断网络
    public boolean getNet(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null) {
                return info.isConnected();
            }
        }
        return false;
    }
}
