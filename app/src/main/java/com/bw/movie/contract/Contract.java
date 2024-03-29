package com.bw.movie.contract;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;

import java.util.Map;

/**
 * author: Xuexiandong
 * data: 2019/9/27 17:17:17
 * function：契约类
 */
public interface Contract {
    interface HomeView extends IBaseView {
        void onSuccess(Object object);

        void onFailure(String failure);
    }

    abstract class ContractPresenter extends BasePresenter {
        public ContractPresenter(IBaseView iBaseView) {
            super(iBaseView);
        }

        //登录
        public abstract void getLogin(Map<String, String> hmap);

        //微信登录
        public abstract void getWeChatBindingLogin(Map<String, String> wxmap);

        //注册
        public abstract void getRegistered(Map<String, String> hmap);

        //发送验证码
        public abstract void getEmail(Map<String, String> hmap);

        //Banner
        public abstract void getBanner();

        //正在热映
        public abstract void getReleaseMovie(Map<String, Integer> hmap);

        //即将上映
        public abstract void getComingSoonMovie(Map<String, String> hmap, Map<String, Integer> qmap);

        //热门电影
        public abstract void getHotMovie(Map<String, Integer> hmap);

        //电影详情页
        public abstract void getFindMoviesDetail(Map<String, String> hmap, Map<String, Integer> qmap);

        //电影评论
        public abstract void getFindAllMovieComment(Map<String, String> hmap, Map<String, Integer> qmap);

        //推荐影院
        public abstract void getFindRecommendCinemas(Map<String, String> hmap, Map<String, Integer> qmap);

        //根据电影ID和影院ID查询电影排期列表
        public abstract void getFindMovieSchedule(Map<String, Integer> qmap);

        //查询附近影院
        public abstract void getFindNearbyCinemas(Map<String, String> hmap, Map<String, Object> qmap);

        //根据影厅id 查询座位信息
        public abstract void getFindSeatInfo(Map<String, Integer> qmap);

        //购票下单
        public abstract void getBuyMovieTickets(Map<String, String> hmap,Map<String, Object> qmap);

        //支付
        public abstract void getPay(Map<String, String> hmap,Map<String, Object> qmap);


    }

}
