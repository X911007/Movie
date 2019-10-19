package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.bean.BeanFindRecommendCinemas;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * author: Xuexiandong
 * data: 2019/10/18 20:20:28
 * function：推荐影院    适配器
 */
public class RecommendCinemaRecyclerView extends RecyclerView.Adapter<RecommendCinemaRecyclerView.RecommendCinemaViewHolder> {
    private List<BeanFindRecommendCinemas.ResultBean> list;
    private Context context;

    public RecommendCinemaRecyclerView(List<BeanFindRecommendCinemas.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //加载布局
    @NonNull
    @Override
    public RecommendCinemaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_recommend_cinema_item_layout, viewGroup, false);
        return new RecommendCinemaViewHolder(inflate);
    }

    //加载数据
    @Override
    public void onBindViewHolder(@NonNull RecommendCinemaViewHolder recommendCinemaViewHolder, int i) {
        final BeanFindRecommendCinemas.ResultBean bean = list.get(i);
        //影院名
        recommendCinemaViewHolder.mRecommendCinemaItemName.setText(bean.getName());
        //影院地址
        recommendCinemaViewHolder.mRecommendCinemaItemAddress.setText(bean.getAddress());
        //影院log
        String logo = bean.getLogo();
        Glide.with(context)
                .load(logo)
                .error(R.drawable.jiaban)
                .placeholder(R.drawable.afanti)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(30, 0)))
                .into(recommendCinemaViewHolder.mRecommendCinemaItemImg);
        //点击影院条目
        recommendCinemaViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBackRecommendCinemaRecyclerView != null) {
                    callBackRecommendCinemaRecyclerView.onRecommendCinemaItem(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecommendCinemaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recommend_cinema_item_img)
        ImageView mRecommendCinemaItemImg;
        @BindView(R.id.recommend_cinema_item_name)
        TextView mRecommendCinemaItemName;
        @BindView(R.id.recommend_cinema_item_address)
        TextView mRecommendCinemaItemAddress;

        public RecommendCinemaViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private CallBackRecommendCinemaRecyclerView callBackRecommendCinemaRecyclerView;

    public void setCallBackRecommendCinemaRecyclerView(CallBackRecommendCinemaRecyclerView callBackRecommendCinemaRecyclerView) {
        this.callBackRecommendCinemaRecyclerView = callBackRecommendCinemaRecyclerView;
    }

    //接口回调
    public interface CallBackRecommendCinemaRecyclerView {
        void onRecommendCinemaItem(BeanFindRecommendCinemas.ResultBean bean);
    }
}
