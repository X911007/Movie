package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.StillsRecyclerView;
import com.bw.movie.adapter.StillsWaterfallsFlowRecyclerView;
import com.bw.movie.adapter.TrailNotice;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.BeanFindMoviesDetail;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Xuexiandong
 * data: 2019/9/29 21:21:00
 * function：剧照
 */
public class FragmentStills extends BaseFragment {
    @BindView(R.id.Stills_RecyclerView)
    RecyclerView mStillsRecyclerView;
    Unbinder unbinder;

    //布局
    @Override
    public int initLayoutInflater() {
        return R.layout.fragment_stills_layout;
    }

    //注册
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }
    //接收EventBus    bean
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getDataBean(BeanFindMoviesDetail.ResultBean bean) {
        List<String> posterList = bean.getPosterList();
        Log.i("3333333333", "getDataBean: "+posterList.size());
        //设置适配器     //网格
        StillsRecyclerView stillsRecyclerView = new StillsRecyclerView(posterList,getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mStillsRecyclerView.setLayoutManager(gridLayoutManager);

        //瀑布流
        StillsWaterfallsFlowRecyclerView stillsWaterfallsFlowRecyclerView = new StillsWaterfallsFlowRecyclerView(posterList, getContext());
        /*//瀑布流
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mStillsRecyclerView.setLayoutManager(staggeredGridLayoutManager);*/
        //设置适配器
        mStillsRecyclerView.setAdapter(stillsRecyclerView);
    }

    //数据
    @Override
    public void initData() {

    }


    //释放
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
