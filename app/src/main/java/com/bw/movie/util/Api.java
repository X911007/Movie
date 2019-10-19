package com.bw.movie.util;

/**
 * author: Xuexiandong
 * data: 2019/9/27 21:21:05
 * function：接口
 */
public interface Api {
    //Base
    String URL_BASE="http://mobile.bwstudent.com/";
    String URL_BASE_="http://172.17.8.100/";

    //登录
    String URL_LOGIN="movieApi/user/v2/login";
    //发送验证码
    String URL_EMAIL_E="movieApi/user/v2/sendOutEmailCode";
    //注册
    String URL_REGISTER="movieApi/user/v2/register";
    //Banner
    String URL_BANNER="movieApi/tool/v2/banner";
    //正在热映
    String URL_RELEASEMOVIELIST="movieApi/movie/v2/findReleaseMovieList";
    //即将上映
    String URL_COMINGSOONMOVIE="movieApi/movie/v2/findComingSoonMovieList";
    //热门电影
    String URL_HOTMOVIE="movieApi/movie/v2/findHotMovieList";
    //电影详情页
    String URL_FINDMOVIESDETAIL="movieApi/movie/v2/findMoviesDetail";
    //电影评论
    String URL_FINDALLMOVIECOMMENT="movieApi/movie/v2/findAllMovieComment";
    //推荐影院
    String URL_FINDRECOMMENDCINEMAS="movieApi/cinema/v1/findRecommendCinemas";

    //账号
    String URL_EMAIL_S="1229863813@qq.com";
    //密码
    String URL_PWD_S="eWLPHopE945d2ivttHaQTQ==";

    //email
    String URL_EMAIL="email";
    //pwd
    String URL_PWD="pwd";
    //昵称
    String URL_NICKNAME="nickName";
    //验证码
    String URL_CODE="code";
    //页数
    String URL_PAGE="page";
    //一页几条
    String URL_COUNT="count";
    //userId
    String URL_USERID="userId";
    String URL_USERID_S="13679";
    //sessionId
    String URL_SESSIONID="sessionId";
    String URL_SESSIONID_S="157071374447513679";
    //电影ID
    String URL_MOVIEID="movieId";
    //微信    AppID
    String WX_APP_ID="wxb3852e6a6b7d9516";




}
