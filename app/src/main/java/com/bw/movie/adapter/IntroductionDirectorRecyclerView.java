package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.bean.BeanFindMoviesDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * author: Xuexiandong
 * data: 2019/10/17 15:15:09
 * function：导演列表适配器
 */
public class IntroductionDirectorRecyclerView extends RecyclerView.Adapter<IntroductionDirectorRecyclerView.IntroductionDirectorViewHolder> {
    private List<BeanFindMoviesDetail.ResultBean.MovieDirectorBean> List;
    private Context context;

    public IntroductionDirectorRecyclerView(java.util.List<BeanFindMoviesDetail.ResultBean.MovieDirectorBean> list, Context context) {
        List = list;
        this.context = context;
    }

    //加载布局
    @NonNull
    @Override
    public IntroductionDirectorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.listofactors_layout, viewGroup, false);
        return new IntroductionDirectorViewHolder(inflate);
    }

    //加载数据
    @Override
    public void onBindViewHolder(@NonNull IntroductionDirectorViewHolder introductionDirectorViewHolder, int i) {
        BeanFindMoviesDetail.ResultBean.MovieDirectorBean bean = List.get(i);
        //导演名
        introductionDirectorViewHolder.mListofactorsName.setText(bean.getName());
        //导演照片
        String photo = bean.getPhoto();
        Glide.with(context)
                .load(photo)
                .error(R.drawable.jiaban)
                .placeholder(R.drawable.afanti)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(30, 0)))
                .into(introductionDirectorViewHolder.mListofactorsImg);
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class IntroductionDirectorViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.listofactors_img)
        ImageView mListofactorsImg;
        @BindView(R.id.listofactors_name)
        TextView mListofactorsName;
        public IntroductionDirectorViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}
