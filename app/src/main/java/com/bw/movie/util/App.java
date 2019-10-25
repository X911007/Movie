package com.bw.movie.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.bw.movie.activity.SelectionActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
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
    //微信支付
    public static void WXZF(String appId,String partnerId,String prepayId,String packageValue,String nonceStr,String timeStamp,String sign){
        PayReq request = new PayReq();
        request.appId = appId;
        request.partnerId = partnerId;
        request.prepayId= prepayId;
        request.packageValue = packageValue;
        request.nonceStr= nonceStr;
        request.timeStamp= timeStamp;
        request.sign= sign;
        api.sendReq(request);
    }
    /*
    appId: wxb3852e6a6b7d9516
    partnerId: 1510865081
    prepayId: wx241930124278588e979b40111486120500
    packageValue: Sign=WXPay
    nonceStr: QOST63ify3fLh8vH
    timeStamp: 1571916564
    sign: 043AC72821EB4CF25B84866322A4C539
    */
}
