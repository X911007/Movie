package com.bw.movie.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.util.Api;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author: Xuexiandong
 * data: 2019/10/18 19:19:09
 * function：引导页
 */
public class GuidePagesActivity extends BaseActivity {

    @BindView(R.id.Jump)
    ImageView mJump;
    private Unbinder bind;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;

    //布局
    @Override
    public int initLayoutResID() {
        return R.layout.activity_guide_pages;
    }
    //注册
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        bind = ButterKnife.bind(this);
        sp = getSharedPreferences(Api.SP_SP, MODE_PRIVATE);
        edit = sp.edit();
        //二次进入时判断
        boolean first = sp.getBoolean(Api.SP_FIRST, false);
        if (first){
            startActivity(new Intent(this,LogInActivity.class));
            finish();
        }
    }
    //点击
    @OnClick(R.id.Jump)
    public void onViewClicked(View view) {
        if (view.getId()==R.id.Jump){
            //跳转到登录页
            startActivity(new Intent(this,LogInActivity.class));
            edit.putBoolean(Api.SP_FIRST,true).commit();
            finish();
        }
    }
    //对象
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    //数据
    @Override
    public void initData() {

    }
    //释放
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
