package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * author: Xuexiandong
 * data: 2019/10/17 19:19:01
 * function：剧照适配器   瀑布流
 */
public class StillsWaterfallsFlowRecyclerView extends RecyclerView.Adapter<StillsWaterfallsFlowRecyclerView.StillsViewHolder> {
    private List<String> list;
    private Context context;

    public StillsWaterfallsFlowRecyclerView(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //加载布局
    @NonNull
    @Override
    public StillsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_stills_item_layout, viewGroup, false);
        return new StillsViewHolder(inflate);
    }

    //加载数据
    @Override
    public void onBindViewHolder(@NonNull StillsViewHolder stillsViewHolder, int i) {
        String s = list.get(i);
        /*//瀑布流
        Glide.with(context)
                .load(s)
                .error(R.drawable.jiaban)
                .placeholder(R.drawable.afanti)
                .into(stillsViewHolder.mFragmentStillsItem);*/
        //网格
        Glide.with(context)
                .load(s)
                .error(R.drawable.jiaban)
                .placeholder(R.drawable.afanti)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(30, 0)))
                .into(stillsViewHolder.mFragmentStillsItem);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StillsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fragment_stills_item)
        ImageView mFragmentStillsItem;
        public StillsViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
