package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.RecommendCinemaRecyclerView;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.BeanFindRecommendCinemas;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.util.Api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Xuexiandong
 * data: 2019/10/18 19:19:09
 * function：推荐影院
 */
public class FragmentRecommendCinema extends BaseFragment implements Contract.HomeView {
    Unbinder unbinder;
    @BindView(R.id.recommend_cinema_RecyclerView)
    RecyclerView mRecommendCinemaRecyclerView;
    private Presenter presenter;
    private Map<String, Integer> qmap = new HashMap<>();
    private Map<String, String> hmap = new HashMap<>();
    private static final String TAG = "FragmentRecommendCinema";

    //布局
    @Override
    public int initLayoutInflater() {
        return R.layout.fragment_recommend_cinema_layout;
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
        //推荐影院
        qmap.put(Api.URL_PAGE, 1);
        qmap.put(Api.URL_COUNT, 5);
        hmap.put(Api.URL_USERID, Api.URL_USERID_S);
        hmap.put(Api.URL_SESSIONID, Api.URL_SESSIONID_S);
        presenter.getFindRecommendCinemas(hmap, qmap);

    }

    //成功
    @Override
    public void onSuccess(Object object) {
        Log.i(TAG, "onSuccess: " + object.toString());
        BeanFindRecommendCinemas beanFindRecommendCinemas = (BeanFindRecommendCinemas) object;
        List<BeanFindRecommendCinemas.ResultBean> beanFindRecommendCinemasResult = beanFindRecommendCinemas.getResult();
        //设置适配器
        RecommendCinemaRecyclerView recommendCinemaRecyclerView = new RecommendCinemaRecyclerView(beanFindRecommendCinemasResult, getContext());
        //设置线性
        mRecommendCinemaRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置适配器
        mRecommendCinemaRecyclerView.setAdapter(recommendCinemaRecyclerView);
        //点击影院条目
        recommendCinemaRecyclerView.setCallBackRecommendCinemaRecyclerView(new RecommendCinemaRecyclerView.CallBackRecommendCinemaRecyclerView() {
            @Override
            public void onRecommendCinemaItem(BeanFindRecommendCinemas.ResultBean bean) {
                //获取影院id
                Toast.makeText(getContext(), ""+bean.getId(), Toast.LENGTH_SHORT).show();
            }
        });
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
