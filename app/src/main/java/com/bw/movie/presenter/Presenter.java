package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.BeanBanner;
import com.bw.movie.bean.BeanComingSoonMovie;
import com.bw.movie.bean.BeanEmail;
import com.bw.movie.bean.BeanFindAllMovieComment;
import com.bw.movie.bean.BeanFindMoviesDetail;
import com.bw.movie.bean.BeanFindRecommendCinemas;
import com.bw.movie.bean.BeanHotMovie;
import com.bw.movie.bean.BeanLogin;
import com.bw.movie.bean.BeanRegistered;
import com.bw.movie.bean.BeanReleaseMovie;
import com.bw.movie.contract.Contract;
import com.bw.movie.databean.DataBeanMovie;
import com.bw.movie.util.RxJavaUtil;
import com.google.gson.Gson;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: Xuexiandong
 * data: 2019/9/27 17:17:19
 * function：P层请求数据
 */
public class Presenter extends Contract.ContractPresenter {
    private static final String TAG = "Presenter";

    public Presenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    //登录
    @Override
    public void getLogin(Map<String, String> hmap) {
        RxJavaUtil.getInstance()
                .getIApi()
                .getLogin(hmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanLogin>() {
                    @Override
                    public void accept(BeanLogin beanLogin) throws Exception {
                        Log.i(TAG, "beanLogin: " + beanLogin.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanLogin);
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

    //注册
    @Override
    public void getRegistered(Map<String, String> hmap) {
        RxJavaUtil.getInstance()
                .getIApi()
                .getRegistered(hmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanRegistered>() {
                    @Override
                    public void accept(BeanRegistered beanRegistered) throws Exception {
                        Log.i(TAG, "beanRegistered: " + beanRegistered.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanRegistered);
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

    //发送验证码
    @Override
    public void getEmail(Map<String, String> hmap) {
        RxJavaUtil.getInstance()
                .getIApi()
                .getEmail(hmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanEmail>() {
                    @Override
                    public void accept(BeanEmail beanEmail) throws Exception {
                        Log.i(TAG, "beanEmail: " + beanEmail.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanEmail);
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

    //Banner
    @Override
    public void getBanner() {
        RxJavaUtil.getInstance()
                .getIApi()
                .getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanBanner>() {
                    @Override
                    public void accept(BeanBanner beanBanner) throws Exception {
                        Log.i(TAG, "beanBanner: " + beanBanner.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanBanner);
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

    //正在热映
    @Override
    public void getReleaseMovie(Map<String, Integer> hmap) {
        RxJavaUtil.getInstance()
                .getIApi()
                .getReleaseMovie(hmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanReleaseMovie>() {
                    @Override
                    public void accept(BeanReleaseMovie beanReleaseMovie) throws Exception {
                        Log.i(TAG, "beanReleaseMovie: " + beanReleaseMovie.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanReleaseMovie);
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

    //即将上映
    @Override
    public void getComingSoonMovie(Map<String, String> hmap, Map<String, Integer> qmap) {
        RxJavaUtil.getInstance()
                .getIApi()
                .getComingSoonMovie(hmap, qmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanComingSoonMovie>() {
                    @Override
                    public void accept(BeanComingSoonMovie beanComingSoonMovie) throws Exception {
                        Log.i(TAG, "accept: " + beanComingSoonMovie.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanComingSoonMovie);
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

    //热门电影
    @Override
    public void getHotMovie(Map<String, Integer> hmap) {
        RxJavaUtil.getInstance()
                .getIApi()
                .getHotMovie(hmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanHotMovie>() {
                    @Override
                    public void accept(BeanHotMovie beanHotMovie) throws Exception {
                        Log.i(TAG, "accept: " + beanHotMovie.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanHotMovie);
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

    //电影详情页
    @Override
    public void getFindMoviesDetail(Map<String, String> hmap, Map<String, Integer> qmap) {
        RxJavaUtil.getInstance()
                .getIApi()
                .getFindMoviesDetail(hmap, qmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanFindMoviesDetail>() {
                    @Override
                    public void accept(BeanFindMoviesDetail beanFindMoviesDetail) throws Exception {
                        Log.i(TAG, "accept: " + beanFindMoviesDetail.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanFindMoviesDetail);
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

    //电影评论
    @Override
    public void getFindAllMovieComment(Map<String, String> hmap, Map<String, Integer> qmap) {
        RxJavaUtil.getInstance()
                .getIApi()
                .getFindAllMovieComment(hmap, qmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanFindAllMovieComment>() {
                    @Override
                    public void accept(BeanFindAllMovieComment beanFindAllMovieComment) throws Exception {
                        Log.i(TAG, "accept: " + beanFindAllMovieComment.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanFindAllMovieComment);
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
    //推荐影院
    @Override
    public void getFindRecommendCinemas(Map<String, String> hmap, Map<String, Integer> qmap) {
        RxJavaUtil.getInstance()
                .getIApi()
                .getFindRecommendCinemas(hmap,qmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanFindRecommendCinemas>() {
                    @Override
                    public void accept(BeanFindRecommendCinemas beanFindRecommendCinemas) throws Exception {
                        Log.i(TAG, "accept: "+beanFindRecommendCinemas.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanFindRecommendCinemas);
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
