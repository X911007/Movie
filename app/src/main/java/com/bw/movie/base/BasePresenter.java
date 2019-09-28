package com.bw.movie.base;

/**
 * author: Xuexiandong
 * data: 2019/9/27 17:17:11
 * function：
 */
public class BasePresenter {
    public IBaseView iBaseView;

    public BasePresenter(IBaseView iBaseView) {
        this.iBaseView = iBaseView;
    }
    //释放
    public void onDestroy() {
        if (iBaseView!=null){
            iBaseView=null;
        }
    }

}
