package com.bw.movie.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.style.WrapTogetherSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.BeanLogin;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.util.Api;
import com.bw.movie.util.App;
import com.bw.movie.util.EncryptUtil;
import com.bw.movie.util.RxJavaUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 登录
 */
public class LogInActivity extends BaseActivity implements Contract.HomeView {

    @BindView(R.id.login_name)
    EditText mLoginName;
    @BindView(R.id.login_pwd)
    EditText mLoginPwd;
    @BindView(R.id.login_code)
    Button mLoginCode;
    @BindView(R.id.login_t_registered)
    TextView mLoginTRegistered;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.login_weixin)
    ImageView mLoginWeixin;
    @BindView(R.id.login_return)
    ImageView mLoginReturn;
    private Presenter presenter1;
    private Unbinder bind;
    private Map<String, String> hmap = new HashMap<>();
    private static final String TAG = "LogInActivity";
    private String name;
    private String pwd;
    private SharedPreferences.Editor edit;
    private SharedPreferences sp;

    //布局
    @Override
    public int initLayoutResID() {
        return R.layout.activity_main;
    }

    //注册
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        bind = ButterKnife.bind(this);
        /*hmap.put(Api.URL_EMAIL, Api.URL_EMAIL_S);
        hmap.put(Api.URL_PWD, Api.URL_PWD_S);
        presenter1.getLogin(hmap);*/
        //获取SharedPreferences
        sp = getSharedPreferences(Api.SP_SP,MODE_PRIVATE);
        edit = sp.edit();
        //二次进入判断
        boolean aBoolean = sp.getBoolean(Api.SP_IDENTIFICATION, false);
        if (aBoolean){
            startActivity(new Intent(LogInActivity.this, ShowActivity.class));
            finish();
        }
    }

    //点击
    @OnClick({R.id.login_code, R.id.login_t_registered, R.id.login, R.id.login_weixin, R.id.login_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_code://忘记密码
                break;
            case R.id.login_t_registered://注册
                startActivity(new Intent(LogInActivity.this, RegisteredActivity.class));
                finish();
                break;
            case R.id.login://登录
                name = mLoginName.getText().toString().trim();
                pwd = mLoginPwd.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //加密
                String encrypt = EncryptUtil.encrypt(pwd);
                Log.i("bbb", "onViewClicked: " + encrypt);
                hmap.put(Api.URL_EMAIL, name);
                hmap.put(Api.URL_PWD, encrypt);
                presenter1.getLogin(hmap);
                break;
            case R.id.login_weixin://微信登录
                getWeixin();
                break;
            case R.id.login_return://返回
                finish();
                break;
        }
    }
    //唤起微信
    private void getWeixin() {
        // send oauth request
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";

        App.api.sendReq(req);
    }

    @Override
    public BasePresenter initPresenter() {
        presenter1 = new Presenter(this);
        return presenter1;
    }

    //数据
    @Override
    public void initData() {

    }

    //成功
    @Override
    public void onSuccess(Object object) {
        BeanLogin beanLogin = (BeanLogin) object;
        Log.i("aaa", "1111111onSuccrss: " + beanLogin.getMessage());
        BeanLogin.ResultBean result = beanLogin.getResult();
        //UserId
        int userId = result.getUserId();
        Log.i(TAG, "onSuccess: userId"+userId);
        String sessionId = result.getSessionId();
        //sessionId
        Log.i(TAG, "onSuccess: sessionId"+sessionId);
        //当前用户存入sp
        Toast.makeText(this, "" + beanLogin.getMessage(), Toast.LENGTH_SHORT).show();
        if ("0000".equals(beanLogin.getStatus())) {
            startActivity(new Intent(LogInActivity.this, ShowActivity.class));
            edit.putBoolean(Api.SP_IDENTIFICATION,true)
                    .putString(Api.SP_USERID,userId+"")
                    .putString(Api.SP_SESSIONID,sessionId)
                    .commit();
            finish();
        }
    }

    //失败
    @Override
    public void onFailure(String failure) {

    }

    //释放
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }


}
