package com.bw.movie.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
//设置
public class SettingActivity extends BaseActivity implements IBaseView {

    @BindView(R.id.serring_back)
    ImageView mSerringBack;
    @BindView(R.id.setting_Cache)
    RelativeLayout mSettingCache;
    @BindView(R.id.setting_Update)
    RelativeLayout mSettingUpdate;
    @BindView(R.id.setting_password)
    LinearLayout mSettingPassword;
    @BindView(R.id.setting_back_login)
    LinearLayout mSettingBackLogin;
    private Unbinder bind;
    private Presenter presenter;
    private static final String TAG = "SettingActivity";

    //布局
    @Override
    public int initLayoutResID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        bind = ButterKnife.bind(this);
    }

    @OnClick({R.id.serring_back, R.id.setting_Cache, R.id.setting_Update, R.id.setting_password, R.id.setting_back_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.serring_back://返回
                Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.setting_Cache://清除缓存
                Toast.makeText(this, "清除缓存", Toast.LENGTH_SHORT).show();

                break;
            case R.id.setting_Update://版本更新
                Toast.makeText(this, "版本更新", Toast.LENGTH_SHORT).show();

                break;
            case R.id.setting_password://重置密码
                Toast.makeText(this, "重置密码", Toast.LENGTH_SHORT).show();

                break;
            case R.id.setting_back_login://退出登录
                Toast.makeText(this, "退出登录", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    //对象
    @Override
    public BasePresenter initPresenter() {
        presenter = new Presenter(this);
        return presenter;
    }

    //数据
    @Override
    public void initData() {

    }
    //成功
    @Override
    public void onSuccess(Object object) {
        Log.i(TAG, "onSuccess: "+object.toString());
    }
    //失败
    @Override
    public void onFailure(String failure) {
        Log.i(TAG, "onFailure: "+failure);
        Toast.makeText(this, ""+failure, Toast.LENGTH_SHORT).show();
    }
    //释放
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
