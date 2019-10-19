package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.activity.MoviedetailspageActivity;
import com.bw.movie.bean.BeanReleaseMovie;
import com.bw.movie.util.App;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * author: Xuexiandong
 * data: 2019/10/10 16:16:47
 * function：正在热映 适配器
 */
public class HotRecyclerView extends RecyclerView.Adapter<HotRecyclerView.HotViewHolder> {
    private List<BeanReleaseMovie.ResultBean> list;
    private Context context;

    public HotRecyclerView(List<BeanReleaseMovie.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //加载布局
    @NonNull
    @Override
    public HotViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.hot_item_layout, viewGroup, false);
        return new HotViewHolder(inflate);
    }

    //加载数据
    @Override
    public void onBindViewHolder(@NonNull final HotViewHolder hotviewHolder, int i) {
        final BeanReleaseMovie.ResultBean bean = list.get(i);

        //电影名
        hotviewHolder.mHotItemName.setText(bean.getName());
        //图片
        String imageUrl = bean.getImageUrl();
        if (TextUtils.isEmpty(imageUrl)) {
            return;
        }
        Glide.with(context)
                .load(imageUrl)
                .error(R.drawable.jiaban)
                .placeholder(R.drawable.afanti)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(30, 0)))
                .into(hotviewHolder.mHotItemImg);
        //评分
        hotviewHolder.mHotItemScore.setText(bean.getScore() + "分");
        //点击购票
        hotviewHolder.mHotItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotviewHolder.mHotItemBtn.setText("已购票");
                hotviewHolder.mHotItemBtn.setBackgroundResource(R.drawable.goupiao_);
                if (callBackTickets != null) {
                    callBackTickets.onTickets(bean);

                }
            }
        });
        //点击条目
        hotviewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBackTickets != null) {
                    callBackTickets.onTickets(bean);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HotViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hot_item_img)
        ImageView mHotItemImg;
        @BindView(R.id.hot_item_score)
        TextView mHotItemScore;
        @BindView(R.id.hot_item_name)
        TextView mHotItemName;
        @BindView(R.id.hot_item_btn)
        Button mHotItemBtn;

        public HotViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    private CallBackTickets callBackTickets;

    public void setCallBackTickets(CallBackTickets callBackTickets) {
        this.callBackTickets = callBackTickets;
    }

    //接口回调
    public interface CallBackTickets {
        void onTickets(BeanReleaseMovie.ResultBean bean);
    }



}
