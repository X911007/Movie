package com.bw.movie.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.bean.BeanFindMoviesDetail;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * author: Xuexiandong
 * data: 2019/10/17 11:11:55
 * function：预告适配器(视频)
 */
public class TrailNotice extends BaseQuickAdapter<BeanFindMoviesDetail.ResultBean.ShortFilmListBean, BaseViewHolder> {
    public TrailNotice(int layoutResId, @Nullable List<BeanFindMoviesDetail.ResultBean.ShortFilmListBean> data) {
        super(layoutResId, data);
    }
    //加载数据
    @Override
    protected void convert(BaseViewHolder helper, BeanFindMoviesDetail.ResultBean.ShortFilmListBean item) {
        //设置视频
        //找控件
        JCVideoPlayer jcVideoPlayer = helper.itemView.findViewById(R.id.TrailNotice_item_JCVideoPlayer);
        //设置数据
        jcVideoPlayer.setUp(item.getVideoUrl(),null);
    }
}
