package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.adapter.IntroductionActor;
import com.bw.movie.adapter.IntroductionDirectorRecyclerView;
import com.bw.movie.adapter.MoviedetailspageViewPagerAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.BeanFindMoviesDetail;
import com.bw.movie.bean.BeanReleaseMovie;
import com.bw.movie.fragment.FragmentFilmReview;
import com.bw.movie.fragment.FragmentIntroduction;
import com.bw.movie.fragment.FragmentStills;
import com.bw.movie.fragment.FragmentTrailNotice;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.util.Api;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author: Xuexiandong
 * data: 2019/9/29 21:21:00
 * function：电影详情页
 */
public class MoviedetailspageActivity extends BaseActivity implements IBaseView {

    @BindView(R.id.Moviedetailspage_back)
    ImageView mMoviedetailspageBack;
    @BindView(R.id.Moviedetailspage_score)
    TextView mMoviedetailspageScore;
    @BindView(R.id.Moviedetailspage_comment)
    TextView mMoviedetailspageComment;
    @BindView(R.id.Moviedetailspage_name)
    TextView mMoviedetailspageName;
    @BindView(R.id.Moviedetailspage_type)
    TextView mMoviedetailspageType;
    @BindView(R.id.Moviedetailspage_releasetime)
    TextView mMoviedetailspageReleasetime;
    @BindView(R.id.Moviedetailspage_tab)
    TabLayout mMoviedetailspageTab;
    @BindView(R.id.Moviedetailspage_vp)
    ViewPager mMoviedetailspageVp;
    @BindView(R.id.Moviedetailspage_lin)
    LinearLayout mMoviedetailspageLin;
    @BindView(R.id.Moviedetailspage_img)
    ImageView mMoviedetailspageImg;
    @BindView(R.id.Moviedetailspage_Filmreview)
    TextView mMoviedetailspageFilmreview;
    @BindView(R.id.Moviedetailspage_Selection)
    TextView mMoviedetailspageSelection;
    private Unbinder bind;
    private static final String TAG = "MoviedetailspageActivit";
    private List<String> list_b = new ArrayList<>();
    private List<Fragment> list_f = new ArrayList<>();
    private Map<String, Integer> qmap = new HashMap<>();
    private Map<String, String> hmap = new HashMap<>();
    private Presenter presenter;

    //布局
    @Override
    public int initLayoutResID() {
        return R.layout.activity_moviedetailspage;
    }

    //注册
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        bind = ButterKnife.bind(this);
        //注册
        EventBus.getDefault().register(this);
        //标签
        list_b.add("介绍");
        list_b.add("预告");
        list_b.add("剧照");
        list_b.add("影评");
        //介绍
        list_f.add(new FragmentIntroduction());
        //预告
        list_f.add(new FragmentTrailNotice());
        //剧照
        list_f.add(new FragmentStills());
        //影评
        list_f.add(new FragmentFilmReview());
        //设置适配器
        MoviedetailspageViewPagerAdapter moviedetailspageViewPagerAdapter = new MoviedetailspageViewPagerAdapter(getSupportFragmentManager(), list_b, list_f);
        mMoviedetailspageVp.setAdapter(moviedetailspageViewPagerAdapter);
        mMoviedetailspageTab.setupWithViewPager(mMoviedetailspageVp);
        //设置预加载
        mMoviedetailspageVp.setOffscreenPageLimit(4);
    }

    //点击
    @OnClick({R.id.Moviedetailspage_back, R.id.Moviedetailspage_lin, R.id.Moviedetailspage_Filmreview, R.id.Moviedetailspage_Selection})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Moviedetailspage_back://返回
                finish();
                break;
            case R.id.Moviedetailspage_lin://线性布局

                break;
            case R.id.Moviedetailspage_Filmreview://影评
                break;
            case R.id.Moviedetailspage_Selection://选座
                Intent intent = new Intent(this, ShowActivity.class);
                intent.putExtra("推荐",0);
                startActivity(intent);
                break;
        }
    }
    //接收EventBus    bean
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getDataBean(BeanReleaseMovie.ResultBean bean) {
        if (bean!=null) {
            int movieId = bean.getMovieId();
            qmap.put(Api.URL_MOVIEID, movieId);
            hmap.put(Api.URL_USERID, Api.URL_USERID_S);
            hmap.put(Api.URL_SESSIONID, Api.URL_SESSIONID_S);
            presenter.getFindMoviesDetail(hmap,qmap);

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
        Log.i(TAG, "onSuccess: " + object);
        BeanFindMoviesDetail beanFindMoviesDetail = (BeanFindMoviesDetail) object;
        BeanFindMoviesDetail.ResultBean bean = beanFindMoviesDetail.getResult();
        //图片
        String imageUrl = bean.getImageUrl();
        Log.i(TAG, "onSuccess: " + imageUrl);
        Glide.with(this)
                .load(imageUrl)
                .into(mMoviedetailspageImg);
        //评分
        mMoviedetailspageScore.setText("评分" + bean.getScore() + "分");
        //评论
        mMoviedetailspageComment.setText("评论" + bean.getCommentNum() + "条");
        //电影名
        mMoviedetailspageName.setText(bean.getName());
        //电影类型
        mMoviedetailspageType.setText(bean.getMovieType() + "    " + bean.getDuration());
        //上映时间+地点
        long releaseTime = bean.getReleaseTime();
        //时间戳转换
        Date d = new Date(releaseTime);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        String format = sf.format(d);
        mMoviedetailspageReleasetime.setText(format + "    " + bean.getPlaceOrigin());
        //预告（视频集合）
        List<BeanFindMoviesDetail.ResultBean.ShortFilmListBean> shortFilmList = bean.getShortFilmList();
        EventBus.getDefault().postSticky(shortFilmList);
        //剧情
        String summary = bean.getSummary();
        EventBus.getDefault().postSticky(summary);
        //演员
        List<BeanFindMoviesDetail.ResultBean.MovieActorBean> movieActorBeanListList = bean.getMovieActor();
        EventBus.getDefault().postSticky(movieActorBeanListList);
        //导演
        List<BeanFindMoviesDetail.ResultBean.MovieDirectorBean> movieDirectorBeanList = bean.getMovieDirector();
        EventBus.getDefault().postSticky(movieDirectorBeanList);


        EventBus.getDefault().postSticky(bean);


    }

    //失败
    @Override
    public void onFailure(String failure) {
        Log.i(TAG, "onFailure: " + failure);
        Toast.makeText(this, "" + failure, Toast.LENGTH_SHORT).show();
    }

    //释放
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

}
