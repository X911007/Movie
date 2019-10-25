package com.bw.movie.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.bw.movie.R;
import com.bw.movie.util.Api;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import javax.security.auth.login.LoginException;

/**
 * author: Xuexiandong
 * data: 2019/10/23 18:18:45
 * function：微信支付
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pay_result);

        //调用API前，需要先向微信注册您的APPID
        api = WXAPIFactory.createWXAPI(this, Api.WX_APP_ID);
        api.handleIntent(getIntent(), this);
        //支付
        api.registerApp(Api.WX_APP_ID);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
    //回调结果的处理
    @Override
    public void onResp(BaseResp resp) {
        int errCode = resp.errCode;
        Log.i(TAG, "errCode:---->" + errCode);

        String result = "";
        switch (errCode) {
            case 0:
                Log.i(TAG, "onResp:支付成功");
                result = "支付成功";
                Log.i(TAG, "支付成功onResp: "+result);
//                tvTitle.setText("支付成功");
                break;
            case -1:
                //可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
                Log.i(TAG, "onResp:支付失败");
                result = "支付失败,请重试";
                finish();
                break;
            case -2:
                //用户取消支付
                Log.i(TAG, "onResp:用户取消支付");
                result = "您取消了支付";
                Log.i(TAG, "支付失败onResp: "+result);
                finish();
                break;
        }

//        ToastUtils.showToast(this, result);
    }
    //net.sourceforge.simcpux.wxapi
}
