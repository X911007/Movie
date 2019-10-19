package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.bean.BeanFindAllMovieComment;

import java.util.List;

/**
 * author: Xuexiandong
 * data: 2019/10/17 20:20:07
 * function：电影评论    适配器
 */
public class FilmReviewRecyclerView extends RecyclerView.Adapter<FilmReviewRecyclerView.FilmReviewViewHolder> {
    private List<BeanFindAllMovieComment.ResultBean> list;
    private Context context;

    public FilmReviewRecyclerView(List<BeanFindAllMovieComment.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //加载布局
    @NonNull
    @Override
    public FilmReviewRecyclerView.FilmReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.filmreview_item_layout, viewGroup, false);
        return new FilmReviewViewHolder(inflate);
    }
    //加载数据
    @Override
    public void onBindViewHolder(@NonNull FilmReviewRecyclerView.FilmReviewViewHolder filmReviewViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FilmReviewViewHolder extends RecyclerView.ViewHolder {
        public FilmReviewViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
