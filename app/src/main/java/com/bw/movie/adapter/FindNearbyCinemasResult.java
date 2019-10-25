package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.bean.BeanFindNearbyCinemas;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * author: Xuexiandong
 * data: 2019/10/21 19:19:53
 * function：附近影院    适配器
 */
public class FindNearbyCinemasResult extends XRecyclerView.Adapter<FindNearbyCinemasResult.FindNearbyCinemasResultViewHolder> {
    private List<BeanFindNearbyCinemas.ResultBean> list;
    private Context context;

    public FindNearbyCinemasResult(List<BeanFindNearbyCinemas.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //加载布局
    @NonNull
    @Override
    public FindNearbyCinemasResultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.findnearbycinemasresult_cinema_item_layout, viewGroup, false);
        return new FindNearbyCinemasResultViewHolder(inflate);
    }

    //加载数据
    @Override
    public void onBindViewHolder(@NonNull FindNearbyCinemasResultViewHolder findNearbyCinemasResultViewHolder, int i) {
        final BeanFindNearbyCinemas.ResultBean bean = list.get(i);
        //影院名
        findNearbyCinemasResultViewHolder.mFindnearbycinemasresultName.setText(bean.getName());
        //地址
        findNearbyCinemasResultViewHolder.mFindnearbycinemasresultAddress.setText(bean.getAddress());
        //距离
        findNearbyCinemasResultViewHolder.mFindnearbycinemasresultDistance.setText(bean.getDistance()/1000.0+"km");
        //logo
        String logo = bean.getLogo();
        Glide.with(context)
                .load(logo)
                .error(R.drawable.jiaban)
                .placeholder(R.drawable.afanti)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(30, 0)))
                .into(findNearbyCinemasResultViewHolder.mFindnearbycinemasresultImg);

        //点击条目
        findNearbyCinemasResultViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBackFindNearbyCinemasResult!=null) {
                    //获取附近影院id
                    callBackFindNearbyCinemasResult.onNearbyCinemas(bean.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FindNearbyCinemasResultViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.findnearbycinemasresult_img)
        ImageView mFindnearbycinemasresultImg;
        @BindView(R.id.findnearbycinemasresult_name)
        TextView mFindnearbycinemasresultName;
        @BindView(R.id.findnearbycinemasresult_address)
        TextView mFindnearbycinemasresultAddress;
        @BindView(R.id.findnearbycinemasresult_distance)
        TextView mFindnearbycinemasresultDistance;
        public FindNearbyCinemasResultViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
    private CallBackFindNearbyCinemasResult callBackFindNearbyCinemasResult;

    public void setCallBackFindNearbyCinemasResult(CallBackFindNearbyCinemasResult callBackFindNearbyCinemasResult) {
        this.callBackFindNearbyCinemasResult = callBackFindNearbyCinemasResult;
    }

    public interface CallBackFindNearbyCinemasResult{
        void onNearbyCinemas(int str);
    }

}
