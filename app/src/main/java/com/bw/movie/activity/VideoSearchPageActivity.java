package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.VideoSearchPageAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.fragment.FragmentVideoSearchPageComingSoon;
import com.bw.movie.fragment.FragmentVideoSearchPageHhot;
import com.bw.movie.fragment.FragmentVideoSearchPagePopularmovie;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.view.ViewSearchBarBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//影片搜索页
public class VideoSearchPageActivity extends BaseActivity implements IBaseView {

    @BindView(R.id.Videosearchpage_recommend)
    TextView mVideosearchpageRecommend;
    @BindView(R.id.Videosearchpage_Cinema)
    TextView mVideosearchpageCinema;
    @BindView(R.id.Videosearchpage_region)
    TextView mVideosearchpageRegion;
    @BindView(R.id.vp_Videosearchpage)
    ViewPager mVpVideosearchpage;
    @BindView(R.id.Videosearchpage_ViewSearchBar)
    ViewSearchBarBack mVideosearchpageViewSearchBar;
    @BindView(R.id.searchfor_back)
    ImageView mSearchfor_back;
    /*@BindView(R.id.searchfor_sousuo)
    ImageView mSearchfor_sousuo;*/
    private Unbinder bind;
    private static final String TAG = "VideoSearchPageActivity";
    private List<Fragment> list_f = new ArrayList<>();

    //布局
    @Override
    public int initLayoutResID() {
        return R.layout.activity_video_search_page;
    }

    //注册
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        bind = ButterKnife.bind(this);
        //正在热映
        list_f.add(new FragmentVideoSearchPageHhot());
        //即将上映
        list_f.add(new FragmentVideoSearchPageComingSoon());
        //热门电影
        list_f.add(new FragmentVideoSearchPagePopularmovie());
        mVpVideosearchpage.setOffscreenPageLimit(3);
        //设置适配器
        VideoSearchPageAdapter videoSearchPageAdapter = new VideoSearchPageAdapter(getSupportFragmentManager(), list_f);
        mVpVideosearchpage.setAdapter(videoSearchPageAdapter);
        //mVpVideosearchpage滑动监听
        /*mVpVideosearchpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        mVpVideosearchpage.setCurrentItem(0);
                        break;
                    case 1:
                        mVpVideosearchpage.setCurrentItem(1);
                        break;
                    case 2:
                        mVpVideosearchpage.setCurrentItem(2);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });*/


        //自定义View搜索
        mVideosearchpageViewSearchBar.setGetETCallback(new ViewSearchBarBack.getETCallback() {
            //搜索
            @Override
            public void onSuccess(String data) {
                Toast.makeText(VideoSearchPageActivity.this, "" + data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBack(String data) {
            }
        });

    }

    //点击
    @OnClick({R.id.Videosearchpage_recommend, R.id.Videosearchpage_Cinema, R.id.Videosearchpage_region, R.id.searchfor_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Videosearchpage_recommend://正在热映
                //Toast.makeText(this, "正在热映", Toast.LENGTH_SHORT).show();
                mVpVideosearchpage.setCurrentItem(0);
                break;
            case R.id.Videosearchpage_Cinema://即将上映
                //Toast.makeText(this, "即将上映", Toast.LENGTH_SHORT).show();
                mVpVideosearchpage.setCurrentItem(1);
                break;
            case R.id.Videosearchpage_region://热门电影
                //Toast.makeText(this, "热门电影", Toast.LENGTH_SHORT).show();
                mVpVideosearchpage.setCurrentItem(2);
                break;
            case R.id.searchfor_back://返回键
                //Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
                finish();
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
        Log.i(TAG, "onSuccess: " + object.toString());
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
