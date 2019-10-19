package com.bw.movie.adapter;

import android.content.Context;
import android.graphics.Color;
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
import com.bw.movie.bean.BeanComingSoonMovie;
import com.bw.movie.bean.BeanReleaseMovie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * author: Xuexiandong
 * data: 2019/10/10 16:16:47
 * function：即将上映 适配器
 */
public class ReleasedRecyclerView extends RecyclerView.Adapter<ReleasedRecyclerView.ReleasedViewHolder> {
    private List<BeanComingSoonMovie.ResultBean> list;
    private Context context;

    public ReleasedRecyclerView(List<BeanComingSoonMovie.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //加载布局
    @NonNull
    @Override
    public ReleasedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.released_item_layout, viewGroup, false);
        return new ReleasedViewHolder(inflate);
    }

    //加载数据
    @Override
    public void onBindViewHolder(@NonNull final ReleasedViewHolder releasedViewHolder, int i) {
        final BeanComingSoonMovie.ResultBean bean = list.get(i);
        //电影名
        releasedViewHolder.mReleasedItemName.setText(bean.getName());
        //图片
        String imageUrl = bean.getImageUrl();
        if (TextUtils.isEmpty(imageUrl)) {
            return;
        }
        Glide.with(context)
                .load(imageUrl)
                .error(R.drawable.jiaban)
                .placeholder(R.drawable.afanti)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(30,0)))
                .into(releasedViewHolder.mReleasedItemImg);
        //人数
        releasedViewHolder.mReleasedItemQuantity.setText(bean.getWantSeeNum()+"人想看");
        //点击预约
        releasedViewHolder.mReleasedItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releasedViewHolder.mReleasedItemBtn.setText("已预约");
                releasedViewHolder.mReleasedItemBtn.setBackgroundResource(R.drawable.goupiao_);
                if (callBackReleased!=null) {
                    callBackReleased.onReleased(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ReleasedViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.released_item_name)
        TextView mReleasedItemName;
        @BindView(R.id.released_item_time)
        TextView mReleasedItemTime;
        @BindView(R.id.released_item_quantity)
        TextView mReleasedItemQuantity;
        @BindView(R.id.released_item_btn)
        Button mReleasedItemBtn;
        @BindView(R.id.released_item_img)
        ImageView mReleasedItemImg;
        public ReleasedViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private CallBackReleased callBackReleased;

    public void setCallBackReleased(CallBackReleased callBackReleased) {
        this.callBackReleased = callBackReleased;
    }

    //接口回调
    public interface CallBackReleased{
        void onReleased(BeanComingSoonMovie.ResultBean bean);
    }
}
