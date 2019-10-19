package com.bw.movie.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.VideoSearchPageHhot;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.BeanReleaseMovie;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.util.Api;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Xuexiandong
 * data: 2019/10/14 15:15:00
 * function：影片搜索页——正在热映
 */
public class FragmentVideoSearchPageHhot extends BaseFragment implements Contract.HomeView {
    Unbinder unbinder;
    private static final String TAG = "FragmentVideoSearchPage";
    @BindView(R.id.VideoSearchPageHhot_XRecyclerView)
    XRecyclerView mVideoSearchPageHhotXRecyclerView;
    private Presenter presenter;
    private Map<String, Integer> qmap = new HashMap<>();
    private List<BeanReleaseMovie.ResultBean> list_s = new ArrayList<>();
    private int page = 1;
    private int count = 5;

    //加载布局
    @Override
    public int initLayoutInflater() {
        return R.layout.videosearchpage_hot_layout;
    }

    //注册
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    //数据
    @Override
    public void initData() {
        presenter = new Presenter(this);
        //联网
        getNet(page, count);
        //刷新加载监听
        mVideoSearchPageHhotXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            //刷新
            @Override
            public void onRefresh() {
                list_s.clear();
                page = 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getNet(page, count);
                        mVideoSearchPageHhotXRecyclerView.refreshComplete();
                    }
                }, 2000);
            }
            //加载
            @Override
            public void onLoadMore() {
                page++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getNet(page, count);
                        mVideoSearchPageHhotXRecyclerView.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }
    //联网
    private void getNet(int page, int count) {
        //正在热映
        qmap.put(Api.URL_PAGE, page);
        qmap.put(Api.URL_COUNT, count);
        presenter.getReleaseMovie(qmap);
    }

    //成功
    @Override
    public void onSuccess(Object object) {
        Log.i(TAG, "onSuccess: " + object.toString());
        BeanReleaseMovie beanReleaseMovie = (BeanReleaseMovie) object;
        List<BeanReleaseMovie.ResultBean> beanReleaseMovielist = beanReleaseMovie.getResult();
        //存入大集合
        list_s.addAll(beanReleaseMovielist);
        Log.i(TAG, "onSuccess: " + beanReleaseMovielist.get(0).getName());
        //设置适配器
//            HotXRecyclerView hotXRecyclerView = new HotXRecyclerView(beanReleaseMovielist,getContext());
        VideoSearchPageHhot videoSearchPageHhot = new VideoSearchPageHhot(list_s, getContext());
        //线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //加载线性布局
        mVideoSearchPageHhotXRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mVideoSearchPageHhotXRecyclerView.setAdapter(videoSearchPageHhot);
        //购票
        videoSearchPageHhot.setCallBackTicketsHot(new VideoSearchPageHhot.CallBackTicketsHot() {
            @Override
            public void onTickets(BeanReleaseMovie.ResultBean bean) {
                Toast.makeText(getContext(), "已购买" + bean.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        //设置加载更多
        mVideoSearchPageHhotXRecyclerView.setLoadingMoreEnabled(true);

    }

    //失败
    @Override
    public void onFailure(String failure) {
        Log.i(TAG, "onFailure: " + failure);
        Toast.makeText(getContext(), "" + failure, Toast.LENGTH_SHORT).show();
    }

    //释放
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
