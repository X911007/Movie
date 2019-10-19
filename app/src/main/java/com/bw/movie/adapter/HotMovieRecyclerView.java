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
import com.bw.movie.bean.BeanHotMovie;
import com.bw.movie.bean.BeanReleaseMovie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * author: Xuexiandong
 * data: 2019/10/10 16:16:47
 * function：热门电影 适配器
 */
public class HotMovieRecyclerView extends RecyclerView.Adapter<HotMovieRecyclerView.HotMovieViewHolder> {
    private List<BeanHotMovie.ResultBean> list;
    private Context context;

    public HotMovieRecyclerView(List<BeanHotMovie.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //加载视图
    @NonNull
    @Override
    public HotMovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.hotmovie_item_layout, viewGroup, false);
        return new HotMovieViewHolder(inflate);
    }

    //加载数据
    @Override
    public void onBindViewHolder(@NonNull final HotMovieViewHolder hotMovieViewHolder, int i) {
        BeanHotMovie.ResultBean resultBean = list.get(0);
        final BeanHotMovie.ResultBean bean = list.get(i);
        //电影名
        hotMovieViewHolder.mHotMovieItemName.setText(bean.getName());
        //图片
        String imageUrl = bean.getImageUrl();
        Glide.with(context)
                .load(imageUrl)
                .error(R.drawable.jiaban)
                .placeholder(R.drawable.afanti)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(30, 0)))
                .into(hotMovieViewHolder.mHotMovieItemImg);
        //评分
        hotMovieViewHolder.mHotMovieItemScore.setText(bean.getScore() + "分");
        //点击购票
        hotMovieViewHolder.mHotMovieItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotMovieViewHolder.mHotMovieItemBtn.setText("已购票");
                hotMovieViewHolder.mHotMovieItemBtn.setBackgroundResource(R.drawable.goupiao_);
                if (callBackHotMovie != null) {
                    callBackHotMovie.onHotMovie(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HotMovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hotMovie_item_img)
        ImageView mHotMovieItemImg;
        @BindView(R.id.hotMovie_item_score)
        TextView mHotMovieItemScore;
        @BindView(R.id.hotMovie_item_name)
        TextView mHotMovieItemName;
        @BindView(R.id.hotMovie_item_btn)
        Button mHotMovieItemBtn;

        public HotMovieViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public class HotMovieTouViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hotMovie_item_img)
        ImageView mHotMovieItemImg;
        @BindView(R.id.hotMovie_item_score)
        TextView mHotMovieItemScore;
        @BindView(R.id.hotMovie_item_name)
        TextView mHotMovieItemName;
        @BindView(R.id.hotMovie_item_btn)
        Button mHotMovieItemBtn;

        public HotMovieTouViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    private CallBackHotMovie callBackHotMovie;

    public void setCallBackHotMovie(CallBackHotMovie callBackHotMovie) {
        this.callBackHotMovie = callBackHotMovie;
    }

    //接口回调
    public interface CallBackHotMovie {
        void onHotMovie(BeanHotMovie.ResultBean bean);
    }

}
