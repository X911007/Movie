package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.bean.BeanReleaseMovie;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * author: Xuexiandong
 * data: 2019/10/10 16:16:47
 * function：影片搜索页——正在热映 适配器
 */
public class VideoSearchPageHhot extends XRecyclerView.Adapter<VideoSearchPageHhot.HotViewHolder> {
    private List<BeanReleaseMovie.ResultBean> list;
    private Context context;

    public VideoSearchPageHhot(List<BeanReleaseMovie.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //加载布局
    @NonNull
    @Override
    public HotViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.videosearchpagehhot_item_layout, viewGroup, false);
        return new HotViewHolder(inflate);
    }

    //加载数据
    @Override
    public void onBindViewHolder(@NonNull final HotViewHolder hotviewHolder, int i) {
         List<String >list_s=new ArrayList<>();

        final BeanReleaseMovie.ResultBean bean = list.get(i);
        //电影名
        hotviewHolder.mVideoSearchPageHhotItemName.setText(bean.getName());
        //图片
        String imageUrl = bean.getImageUrl();
        Glide.with(context)
                .load(imageUrl)
                .error(R.drawable.jiaban)
                .placeholder(R.drawable.afanti)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(30, 0)))
                .into(hotviewHolder.mVideoSearchPageHhotItemImg);
        //导演
        hotviewHolder.mVideoSearchPageHhotItemDirector.setText("导演："+bean.getDirector());
        //主演
        String starring = bean.getStarring();
        String[] split = starring.split(",");
        for (int j = 0; j < split.length; j++) {
            String s = split[j];
            list_s.add(s);
        }
        hotviewHolder.mVideoSearchPageHhotItemStarring.setText("主演："+list_s.get(0)+","+list_s.get(1));
        //评分
        hotviewHolder.mVideoSearchPageHhotItemScore.setText("评分："+bean.getScore()+"分");
        //点击购票
        hotviewHolder.mVideoSearchPageHhotItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotviewHolder.mVideoSearchPageHhotItemBtn.setText("已购票");
                hotviewHolder.mVideoSearchPageHhotItemBtn.setBackgroundResource(R.drawable.goupiao_);
                if (callBackTicketsHot != null) {
                    callBackTicketsHot.onTickets(bean);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HotViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.VideoSearchPageHhot_item_img)
        ImageView mVideoSearchPageHhotItemImg;
        @BindView(R.id.VideoSearchPageHhot_item_name)
        TextView mVideoSearchPageHhotItemName;
        @BindView(R.id.VideoSearchPageHhot_item_director)
        TextView mVideoSearchPageHhotItemDirector;
        @BindView(R.id.VideoSearchPageHhot_item_Starring)
        TextView mVideoSearchPageHhotItemStarring;
        @BindView(R.id.VideoSearchPageHhot_item_score)
        TextView mVideoSearchPageHhotItemScore;
        @BindView(R.id.VideoSearchPageHhot_item_btn)
        Button mVideoSearchPageHhotItemBtn;

        public HotViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private CallBackTicketsHot callBackTicketsHot;

    public void setCallBackTicketsHot(CallBackTicketsHot callBackTicketsHot) {
        this.callBackTicketsHot = callBackTicketsHot;
    }

    //接口回调
    public interface CallBackTicketsHot {
        void onTickets(BeanReleaseMovie.ResultBean bean);
    }



}
