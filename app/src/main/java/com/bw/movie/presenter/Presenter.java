package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.BeanLogin;
import com.bw.movie.contract.Contract;
import com.bw.movie.util.RxJavaUtil;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: Xuexiandong
 * data: 2019/9/27 17:17:19
 * function：
 */
public class Presenter extends Contract.ContractPresenter {
    public Presenter(IBaseView iBaseView) {
        super(iBaseView);
    }
    //回调
    @Override
    public void getData(Map<String, String> hmap) {
        //登录
        RxJavaUtil.getInstance()
                .getIApi()
                .getLogin(hmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanLogin>() {
                    @Override
                    public void accept(BeanLogin beanLogin) throws Exception {
                        Log.i("bbb", "accept: "+beanLogin.getMessage());
                        if ("0000".equals(beanLogin.getStatus())) {
                            if (iBaseView != null) {
                                iBaseView.onSuccrss(beanLogin);
                            }
                        } else {
                            if (iBaseView != null) {
                                iBaseView.onFailure(beanLogin.getMessage());
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iBaseView != null) {
                            iBaseView.onFailure(throwable.getMessage());
                        }
                    }
                });

    }

    //释放
    public void onDestroy() {
        if (iBaseView != null) {
            iBaseView = null;
        }
    }
}
