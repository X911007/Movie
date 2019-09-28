package com.bw.movie.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bw.movie.R;

/**
 * author: Xuexiandong
 * data: 2019/9/27 17:17:09
 * function：
 */
public abstract class BaseActivity extends AppCompatActivity {
    public BasePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (initLayoutResID() != 0) {
            setContentView(initLayoutResID());
            presenter=initPresenter();
            initData();
        }
    }

    public abstract int initLayoutResID();

    public abstract BasePresenter initPresenter();

    public abstract void initData();


    //释放
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.onDestroy();
        }

    }
}
