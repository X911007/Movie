package com.bw.movie.util;

import android.app.Application;
import android.content.Context;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * author: Xuexiandong
 * data: 2019/9/27 17:17:24
 * function：
 */
public class App extends Application {
    //获取上下文
    public static Context sContext;
    //微信登录
    public static IWXAPI api;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        //微信登录
        regToWx();
        //Fresco初始化
    }
    //微信登录
    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this,Api.WX_APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(Api.WX_APP_ID);
    }
    //供外部调用 微信登录
    public static IWXAPI getWXApi(){
        return api;
    }
}
