package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.bw.movie.util.EncryptUtil;

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
    private Presenter presenter1;
    private Unbinder bind;
    private Map<String, String> hmap = new HashMap<>();

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
    }

    //点击
    @OnClick({R.id.login_code, R.id.login_t_registered, R.id.login, R.id.login_weixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_code://获取验证码
                break;
            case R.id.login_t_registered://注册
                break;
            case R.id.login://登录
                String name = mLoginName.getText().toString().trim();
                String pwd = mLoginPwd.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                hmap.put(Api.URL_EMAIL,Api.URL_EMAIL_S);
                hmap.put(Api.URL_PWD,Api.URL_PWD_S);
//                String encrypt = EncryptUtil.encrypt(pwd);
                presenter1.getData(hmap);

                break;
            case R.id.login_weixin://微信登录
                break;
        }
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
    public void onSuccrss(Object object) {
        BeanLogin beanLogin= (BeanLogin) object;
        Log.i("aaa", "onSuccrss: "+beanLogin.getMessage());
        if ("0000".equals(beanLogin.getStatus())){
            startActivity(new Intent(LogInActivity.this, ShowActivity.class));
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
