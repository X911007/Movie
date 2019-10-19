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
import com.bw.movie.R;
import com.bw.movie.bean.BeanReleaseMovie;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Xuexiandong
 * data: 2019/10/10 16:16:47
 * function：正在热映 适配器
 */
public class HotXRecyclerView extends XRecyclerView.Adapter<HotXRecyclerView.HotViewHolder> {
    private List<BeanReleaseMovie.ResultBean> list;
    private Context context;

    public HotXRecyclerView(List<BeanReleaseMovie.ResultBean> list, Context context) {
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
    public void onBindViewHolder(@NonNull HotViewHolder hotviewHolder, int i) {
        BeanReleaseMovie.ResultBean bean = list.get(i);
        //电影名
        hotviewHolder.mHotItemName.setText(bean.getName());
        //图片
        String imageUrl = bean.getImageUrl();
        Glide.with(context)
                .load(imageUrl)
                .into(hotviewHolder.mHotItemImg);
        //评分
        hotviewHolder.mHotItemScore.setText(bean.getScore()+"分");

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

//    public interface


}
