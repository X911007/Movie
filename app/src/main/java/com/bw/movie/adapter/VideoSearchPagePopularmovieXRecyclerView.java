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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * author: Xuexiandong
 * data: 2019/10/10 16:16:47
 * function：影片搜索页——热门电影 适配器
 */
public class VideoSearchPagePopularmovieXRecyclerView extends RecyclerView.Adapter<VideoSearchPagePopularmovieXRecyclerView.HotMovieViewHolder> {
    private List<BeanHotMovie.ResultBean> list;
    private Context context;
    private List<String >list_s=new ArrayList<>();

    public VideoSearchPagePopularmovieXRecyclerView(List<BeanHotMovie.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //加载视图
    @NonNull
    @Override
    public HotMovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.videosearchpagepopularmovie_item_layout, viewGroup, false);
        return new HotMovieViewHolder(inflate);
    }

    //加载数据
    @Override
    public void onBindViewHolder(@NonNull final HotMovieViewHolder hotMovieViewHolder, int i) {
        BeanHotMovie.ResultBean resultBean = list.get(0);
        final BeanHotMovie.ResultBean bean = list.get(i);
        //电影名
        hotMovieViewHolder.mVideoSearchPagePopularmovieItemName.setText(bean.getName());
        //图片
        String imageUrl = bean.getImageUrl();
        Glide.with(context)
                .load(imageUrl)
                .error(R.drawable.jiaban)
                .placeholder(R.drawable.afanti)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(30, 0)))
                .into(hotMovieViewHolder.mVideoSearchPagePopularmovieItemImg);
        //导演
        hotMovieViewHolder.mVideoSearchPagePopularmovieItemDirector.setText("导演："+bean.getDirector());
        //主演
        String starring = bean.getStarring();
        String[] split = starring.split(",");
        for (int j = 0; j < split.length; j++) {
            String s = split[j];
            list_s.add(s);
        }
        hotMovieViewHolder.mVideoSearchPagePopularmovieItemStarring.setText("主演："+list_s.get(0)+","+list_s.get(1));
        //评分
        hotMovieViewHolder.mVideoSearchPagePopularmovieItemScore.setText(bean.getScore() + "分");
        //点击购票
        hotMovieViewHolder.mVideoSearchPagePopularmovieItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotMovieViewHolder.mVideoSearchPagePopularmovieItemBtn.setText("已购票");
                hotMovieViewHolder.mVideoSearchPagePopularmovieItemBtn.setBackgroundResource(R.drawable.goupiao_);
                if (callBackHotMoviemovie != null) {
                    callBackHotMoviemovie.onHotMovie(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HotMovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.VideoSearchPagePopularmovie_item_img)
        ImageView mVideoSearchPagePopularmovieItemImg;
        @BindView(R.id.VideoSearchPagePopularmovie_item_name)
        TextView mVideoSearchPagePopularmovieItemName;
        @BindView(R.id.VideoSearchPagePopularmovie_item_director)
        TextView mVideoSearchPagePopularmovieItemDirector;
        @BindView(R.id.VideoSearchPagePopularmovie_item_Starring)
        TextView mVideoSearchPagePopularmovieItemStarring;
        @BindView(R.id.VideoSearchPagePopularmovie_item_score)
        TextView mVideoSearchPagePopularmovieItemScore;
        @BindView(R.id.VideoSearchPagePopularmovie_item_btn)
        Button mVideoSearchPagePopularmovieItemBtn;

        public HotMovieViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    private CallBackHotMoviemovie callBackHotMoviemovie;

    public void setCallBackHotMoviemovie(CallBackHotMoviemovie callBackHotMoviemovie) {
        this.callBackHotMoviemovie = callBackHotMoviemovie;
    }

    //接口回调
    public interface CallBackHotMoviemovie {
        void onHotMovie(BeanHotMovie.ResultBean bean);
    }

}
