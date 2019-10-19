package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.FilmReviewRecyclerView;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.BeanFindAllMovieComment;
import com.bw.movie.bean.BeanFindMoviesDetail;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.util.Api;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Xuexiandong
 * data: 2019/9/29 21:21:00
 * function：影评
 */
public class FragmentFilmReview extends BaseFragment implements IBaseView {
    @BindView(R.id.FilmReview_RecyclerView)
    RecyclerView mFilmReviewRecyclerView;
    Unbinder unbinder;
    private Presenter presenter;
    private static final String TAG = "FragmentFilmReview";
    private Map<String, Integer> qmap = new HashMap<>();
    private Map<String, String> hmap = new HashMap<>();

    //布局
    @Override
    public int initLayoutInflater() {
        return R.layout.fragment_filmreview_layout;
    }

    //注册
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        //注册
        EventBus.getDefault().register(this);
        return rootView;
    }

    //接收EventBus    bean
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getDataBean(BeanFindMoviesDetail.ResultBean bean) {
        int movieId = bean.getMovieId();

        hmap.put(Api.URL_USERID, Api.URL_USERID_S);
        hmap.put(Api.URL_SESSIONID, Api.URL_SESSIONID_S);

        qmap.put(Api.URL_MOVIEID, movieId);
        qmap.put(Api.URL_PAGE, 1);
        qmap.put(Api.URL_COUNT, 5);
        //请求
        //presenter.getFindMoviesDetail(hmap, qmap);
    }

    //数据
    @Override
    public void initData() {
        presenter = new Presenter(this);
    }

    //成功
    @Override
    public void onSuccess(Object object) {
        Log.i(TAG, "onSuccess: " + object.toString());
        BeanFindAllMovieComment beanFindAllMovieComment= (BeanFindAllMovieComment) object;
        List<BeanFindAllMovieComment.ResultBean> findAllMovieCommentResult = beanFindAllMovieComment.getResult();
        FilmReviewRecyclerView filmReviewRecyclerView = new FilmReviewRecyclerView(findAllMovieCommentResult,getContext());
        //线性
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mFilmReviewRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mFilmReviewRecyclerView.setAdapter(filmReviewRecyclerView);
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
