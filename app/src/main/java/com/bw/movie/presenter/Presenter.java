package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.BeanBanner;
import com.bw.movie.bean.BeanBuyMovieTickets;
import com.bw.movie.bean.BeanComingSoonMovie;
import com.bw.movie.bean.BeanEmail;
import com.bw.movie.bean.BeanFindAllMovieComment;
import com.bw.movie.bean.BeanFindMovieSchedule;
import com.bw.movie.bean.BeanFindMoviesDetail;
import com.bw.movie.bean.BeanFindNearbyCinemas;
import com.bw.movie.bean.BeanFindRecommendCinemas;
import com.bw.movie.bean.BeanFindSeatInfo;
import com.bw.movie.bean.BeanHotMovie;
import com.bw.movie.bean.BeanLogin;
import com.bw.movie.bean.BeanPay;
import com.bw.movie.bean.BeanRegistered;
import com.bw.movie.bean.BeanReleaseMovie;
import com.bw.movie.bean.BeanWeChatBindingLogin;
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
                        Log.i(TAG, "beanLoginssssssss: " + beanLogin.getMessage());
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
    //微信登录
    @Override
    public void getWeChatBindingLogin(Map<String, String> wxmap) {
        RxJavaUtil.getInstance()
                .getIApi()
                .getWeChatBindingLogin(wxmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanWeChatBindingLogin>() {
                    @Override
                    public void accept(BeanWeChatBindingLogin beanWeChatBindingLogin) throws Exception {
                        Log.i(TAG, "accept: "+beanWeChatBindingLogin.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanWeChatBindingLogin);
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
                .getFindRecommendCinemas(hmap, qmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanFindRecommendCinemas>() {
                    @Override
                    public void accept(BeanFindRecommendCinemas beanFindRecommendCinemas) throws Exception {
                        Log.i(TAG, "accept: " + beanFindRecommendCinemas.getMessage());
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

    //根据电影ID和影院ID查询电影排期列表
    @Override
    public void getFindMovieSchedule(Map<String, Integer> qmap) {
        RxJavaUtil.getInstance()
                .getIApi()
                .getFindMovieSchedule(qmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanFindMovieSchedule>() {
                    @Override
                    public void accept(BeanFindMovieSchedule beanFindMovieSchedule) throws Exception {
                        Log.i(TAG, "111111111111accept: " + beanFindMovieSchedule.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanFindMovieSchedule);
                            /*RxJavaUtil.getInstance().getMovie().setMovieName("");
                            RxJavaUtil.getInstance().getMovie().setVideoPath("");
                            RxJavaUtil.getInstance().getMovie().setCinemaId(0);
                            RxJavaUtil.getInstance().getMovie().setMovieId(0);
//                            RxJavaUtil.getInstance().getMovie().setMovieId();*/
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

    //查询附近影院
    @Override
    public void getFindNearbyCinemas(Map<String, String> hmap, Map<String, Object> qmap) {
        RxJavaUtil.getInstance()
                .getIApi()
                .getFindNearbyCinemas(hmap, qmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanFindNearbyCinemas>() {
                    @Override
                    public void accept(BeanFindNearbyCinemas beanFindNearbyCinemas) throws Exception {
                        Log.i(TAG, "222222accept: " + beanFindNearbyCinemas.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanFindNearbyCinemas);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "eeeeeeeeeeeeaccept: " + throwable.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onFailure(throwable.getMessage());
                        }
                    }
                });
    }

    //根据影厅id 查询座位信息
    @Override
    public void getFindSeatInfo(Map<String, Integer> qmap) {
        RxJavaUtil.getInstance()
                .getIApi()
                .getFindSeatInfo(qmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanFindSeatInfo>() {
                    @Override
                    public void accept(BeanFindSeatInfo beanFindSeatInfo) throws Exception {
                        Log.i(TAG, "rrrrrrrraccept: " + beanFindSeatInfo.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanFindSeatInfo);
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
    //购票下单
    @Override
    public void getBuyMovieTickets(Map<String, String> hmap, Map<String, Object> qmap) {
        RxJavaUtil.getInstance()
                .getIApi()
                .getBuyMovieTickets(hmap,qmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanBuyMovieTickets>() {
                    @Override
                    public void accept(BeanBuyMovieTickets beanBuyMovieTickets) throws Exception {
                        Log.i(TAG, "accept: "+beanBuyMovieTickets.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanBuyMovieTickets);
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
    //支付
    @Override
    public void getPay(Map<String, String> hmap, Map<String, Object> qmap) {
        RxJavaUtil.getInstance()
                .getIApi()
                .getPay(hmap,qmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanPay>() {
                    @Override
                    public void accept(BeanPay beanPay) throws Exception {
                        Log.i(TAG, "accept: "+beanPay.getMessage());
                        if (iBaseView != null) {
                            iBaseView.onSuccess(beanPay);
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
