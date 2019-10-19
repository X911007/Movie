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
import com.bw.movie.bean.BeanComingSoonMovie;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * author: Xuexiandong
 * data: 2019/10/10 16:16:47
 * function：影片搜索页——即将上映 适配器
 */
public class VideoSearchPageComingSoon extends XRecyclerView.Adapter<VideoSearchPageComingSoon.HotViewHolder> {
    private List<BeanComingSoonMovie.ResultBean> list;
    private Context context;

    public VideoSearchPageComingSoon(List<BeanComingSoonMovie.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //加载布局
    @NonNull
    @Override
    public HotViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.videosearchpagecomingsoon_item_layout, viewGroup, false);
        return new HotViewHolder(inflate);
    }

    //加载数据
    @Override
    public void onBindViewHolder(@NonNull final HotViewHolder hotviewHolder, int i) {
        final BeanComingSoonMovie.ResultBean bean = list.get(i);
        //电影名
        hotviewHolder.mVideoSearchPageComingSoonItemName.setText(bean.getName());
        //图片
        String imageUrl = bean.getImageUrl();
        Glide.with(context)
                .load(imageUrl)
                .error(R.drawable.jiaban)
                .placeholder(R.drawable.afanti)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(30, 0)))
                .into(hotviewHolder.mVideoSearchPageComingSoonItemImg);
        //人数
        hotviewHolder.mVideoSearchPageComingSoonItemQuantity.setText(bean.getWantSeeNum()+"人想看");
        //点击预约
        hotviewHolder.mVideoSearchPageComingSoonItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotviewHolder.mVideoSearchPageComingSoonItemBtn.setText("已预约");
                hotviewHolder.mVideoSearchPageComingSoonItemBtn.setBackgroundResource(R.drawable.goupiao_);
                if (callBackTicketsSoon!=null) {
                    callBackTicketsSoon.onTickets(bean);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HotViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.VideoSearchPageComingSoon_item_name)
        TextView mVideoSearchPageComingSoonItemName;
        @BindView(R.id.VideoSearchPageComingSoon_item_time)
        TextView mVideoSearchPageComingSoonItemTime;
        @BindView(R.id.VideoSearchPageComingSoon_item_quantity)
        TextView mVideoSearchPageComingSoonItemQuantity;
        @BindView(R.id.VideoSearchPageComingSoon_item_btn)
        Button mVideoSearchPageComingSoonItemBtn;
        @BindView(R.id.VideoSearchPageComingSoon_item_img)
        ImageView mVideoSearchPageComingSoonItemImg;

        public HotViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private CallBackTicketsSoon callBackTicketsSoon;

    public void setCallBackTicketsSoon(CallBackTicketsSoon callBackTicketsSoon) {
        this.callBackTicketsSoon = callBackTicketsSoon;
    }

    //接口回调
    public interface CallBackTicketsSoon {
        void onTickets(BeanComingSoonMovie.ResultBean bean);
    }

}
