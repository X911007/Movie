package com.bw.movie.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.bw.movie.R;
import com.bw.movie.activity.LogInActivity;
import com.bw.movie.activity.ShowActivity;
import com.bw.movie.util.Api;
import com.bw.movie.util.App;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.greenrobot.eventbus.EventBus;

/**
 * author: Xuexiandong
 * data: 2019/10/17 20:20:45
 * function：微信登录
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "WXEntryActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getWXApi().handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        //登录回调
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;
                Log.i(TAG, "1111111onResp: "+code);
                //存入code
                SharedPreferences sp = getSharedPreferences(Api.SP_SP, MODE_PRIVATE);
                sp.edit().putString(Api.SP_CODE,code).commit();
                //获取accesstoken
                EventBus.getDefault().post(code);
                //微信跳转
                startActivity(new Intent(App.sContext, ShowActivity.class));
                break;
            //用户拒绝授权
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                finish();
                break;
            //用户取消
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                finish();
                break;
            default:
                finish();
                break;
        }
    }
}
