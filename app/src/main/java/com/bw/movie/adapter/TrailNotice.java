package com.bw.movie.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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

    private JCVideoPlayer jcVideoPlayer;

    public TrailNotice(int layoutResId, @Nullable List<BeanFindMoviesDetail.ResultBean.ShortFilmListBean> data) {
        super(layoutResId, data);
    }
    //加载数据
    @Override
    protected void convert(BaseViewHolder helper, BeanFindMoviesDetail.ResultBean.ShortFilmListBean item) {
        //设置视频
        //找控件
        jcVideoPlayer = helper.itemView.findViewById(R.id.TrailNotice_item_JCVideoPlayer);
        //视频预览图
        ImageView ivThumb = jcVideoPlayer.ivThumb;
        ivThumb.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(helper.itemView.getContext())
                .load(item.getImageUrl())
                .into(ivThumb);
        //设置数据
        jcVideoPlayer.setUp(item.getVideoUrl(),null);

    }
    public interface CallBackJCVideoPlayer{
        void onSuccess(JCVideoPlayer jcVideoPlayer);
    }
    //停止播放
    public void getJcVideoPlayer(){
        jcVideoPlayer.releaseAllVideos();
    }
}
