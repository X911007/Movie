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
 * data: 2019/10/17 15:15:32
 * function：演员列表适配器
 */
public class IntroductionActor extends RecyclerView.Adapter<IntroductionActor.IntroductionActorViewHolder> {
    private List<BeanFindMoviesDetail.ResultBean.MovieActorBean> List;
    private Context context;

    public IntroductionActor(java.util.List<BeanFindMoviesDetail.ResultBean.MovieActorBean> list, Context context) {
        List = list;
        this.context = context;
    }

    //加载布局
    @NonNull
    @Override
    public IntroductionActorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.listofactors_layout, viewGroup, false);
        return new IntroductionActorViewHolder(inflate);
    }

    //加载数据
    @Override
    public void onBindViewHolder(@NonNull IntroductionActorViewHolder introductionActorViewHolder, int i) {
        BeanFindMoviesDetail.ResultBean.MovieActorBean bean = List.get(i);
        //演员名
        introductionActorViewHolder.mListofactorsName.setText(bean.getName().trim());
        //饰演
        introductionActorViewHolder.mListofactorsPlay.setText("饰："+bean.getRole());
        //演员照片
        String role = bean.getPhoto();
        Log.i("11111111", "onBindViewHolder: "+role);
        Glide.with(context)
                .load(role)
                .error(R.drawable.jiaban)
                .placeholder(R.drawable.afanti)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(30, 0)))
                .into(introductionActorViewHolder.mListofactorsImg);
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class IntroductionActorViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.listofactors_img)
        ImageView mListofactorsImg;
        @BindView(R.id.listofactors_name)
        TextView mListofactorsName;
        @BindView(R.id.listofactors_Play)
        TextView mListofactorsPlay;
        public IntroductionActorViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}
