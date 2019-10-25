package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.BeanEmail;
import com.bw.movie.bean.BeanRegistered;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.util.Api;
import com.bw.movie.util.App;
import com.bw.movie.util.EncryptUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注册
 */
public class RegisteredActivity extends BaseActivity implements Contract.HomeView {

    @BindView(R.id.registered_return)
    ImageView mRegisteredReturn;
    @BindView(R.id.registered_name)
    EditText mRegisteredName;
    @BindView(R.id.registered_email)
    EditText mRegisteredEmail;
    @BindView(R.id.registered_pwd)
    EditText mRegisteredPwd;
    @BindView(R.id.empty_pwd)
    ImageView mEmptyPwd;
    @BindView(R.id.show_pwd)
    ImageView mShowPwd;
    @BindView(R.id.registered_s_code)
    EditText mRegisteredSCode;
    @BindView(R.id.registered_h_code)
    Button mRegisteredHCode;
    @BindView(R.id.registered_t_login)
    TextView mRegisteredTLogin;
    @BindView(R.id.registered)
    Button mRegistered;
    private Presenter presenter1;
    private Unbinder bind;
    private Map<String, String> registeredmap = new HashMap<>();
    private Map<String, String> emailmap = new HashMap<>();
    private boolean showpwd = false;
    private static final String TAG = "RegisteredActivity";

    //布局
    @Override
    public int initLayoutResID() {
        return R.layout.activity_registered;
    }

    //注册
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        bind = ButterKnife.bind(this);
        //默认密码输入框隐藏密码
        Log.i("ccc", "initPresenter: " + showpwd);
        //mRegisteredPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        mShowPwd.setVisibility(View.GONE);
        //密码输入框的监听
        mRegisteredPwd.addTextChangedListener(new TextWatcher() {
            //输入前
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mEmptyPwd.setVisibility(View.GONE);
                mShowPwd.setVisibility(View.GONE);
            }

            //输入框输入改变监听
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pwd_s = mRegisteredPwd.getText().toString().trim();
                if (!TextUtils.isEmpty(pwd_s)) {
                    mEmptyPwd.setVisibility(View.VISIBLE);
                    mShowPwd.setVisibility(View.VISIBLE);
                }
            }

            //输入后
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //点击
    @OnClick({R.id.registered_return, R.id.empty_pwd, R.id.show_pwd, R.id.registered_h_code, R.id.registered_t_login, R.id.registered})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.registered_return://点击返回
                finish();
                break;
            case R.id.empty_pwd://清空密码
                String q = "";
                mRegisteredPwd.setText(q);
                mEmptyPwd.setVisibility(View.GONE);
                break;
            case R.id.show_pwd://显示密码
                if (showpwd) {
                    showpwd = false;
                    //隐藏
                    mRegisteredPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    showpwd = true;
                    //显示
                    mRegisteredPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.registered_h_code://获取验证码
                String name_y = mRegisteredName.getText().toString().trim();
                String email_y = mRegisteredEmail.getText().toString().trim();
                String pwd_y = mRegisteredPwd.getText().toString().trim();
                if (TextUtils.isEmpty(name_y)) {
                    Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email_y)) {
                    Toast.makeText(this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pwd_y)) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                emailmap.put(Api.URL_EMAIL, email_y);
                presenter1.getEmail(emailmap);
                Toast.makeText(this, "以获取验证码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.registered_t_login://跳转登录
                startActivity(new Intent(RegisteredActivity.this, LogInActivity.class));
                finish();
                break;
            case R.id.registered://注册
                String name = mRegisteredName.getText().toString().trim();
                String email = mRegisteredEmail.getText().toString().trim();
                String pwd = mRegisteredPwd.getText().toString().trim();
                String scode = mRegisteredSCode.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(scode)) {
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String encrypt = EncryptUtil.encrypt(pwd);
                registeredmap.put(Api.URL_NICKNAME, name);
                registeredmap.put(Api.URL_PWD, encrypt);
                registeredmap.put(Api.URL_EMAIL, email);
                registeredmap.put(Api.URL_CODE, scode);
                presenter1.getRegistered(registeredmap);
                break;
        }
    }

    //对象
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
        BeanEmail beanEmail = (BeanEmail) object;
        BeanRegistered beanRegistered = (BeanRegistered) object;
        Log.i(TAG, "beanEmail: "+beanEmail.getMessage());
        Toast.makeText(App.sContext, ""+beanEmail.getMessage(), Toast.LENGTH_SHORT).show();
        if ("发送成功".equals(beanEmail.getMessage())) {
            Toast.makeText(App.sContext, ""+beanEmail.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i(TAG, "beanRegistered: "+beanRegistered.getMessage());
        Toast.makeText(RegisteredActivity.this, ""+beanRegistered.getMessage(), Toast.LENGTH_SHORT).show();
        if ("注册成功".equals(beanRegistered.getMessage())){
            Toast.makeText(RegisteredActivity.this, ""+beanRegistered.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

    }

    //失败
    @Override
    public void onFailure(String failure) {
        Log.i(TAG, "onFailure: " + failure);
    }

    //释放
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }


}
