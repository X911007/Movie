package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.BeanFindMovieSchedule;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Xuexiandong
 * data: 2019/10/21 14:14:12
 * function：//根据电影ID和影院ID查询电影排期列表   适配器
 */
public class SelectionMoviehallList extends RecyclerView.Adapter<SelectionMoviehallList.SelectionMoviehallListViewHolder> {
    private List<BeanFindMovieSchedule.ResultBean> list;
    private Context context;


    public SelectionMoviehallList(List<BeanFindMovieSchedule.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    //加载布局
    @NonNull
    @Override
    public SelectionMoviehallListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.selectionmoviehalllist_item_layout, viewGroup, false);
        return new SelectionMoviehallListViewHolder(inflate);
    }
    //加载数据
    @Override
    public void onBindViewHolder(@NonNull final SelectionMoviehallListViewHolder selectionMoviehallListViewHolder, int i) {
        final BeanFindMovieSchedule.ResultBean bean = list.get(i);
        List<String >list_StartTime=new ArrayList<>();
        List<String >list_EndTime=new ArrayList<>();
        //影厅名
        selectionMoviehallListViewHolder.mSelectionmoviehalllistItemName.setText(bean.getScreeningHall());
        //开始时间
        String beginStartTime = bean.getBeginTime();
        String[] splitStart = beginStartTime.split(":");
        for (int i1 = 0; i1 < splitStart.length; i1++) {
            String s = splitStart[i1];
            list_StartTime.add(s);
        }
        selectionMoviehallListViewHolder.mSelectionmoviehalllistItemStart.setText(list_StartTime.get(0)+":"+list_StartTime.get(1));
        //结束时间
        String beginEndTime = bean.getEndTime();
        String[] splitEnd = beginEndTime.split(":");
        for (int i2 = 0; i2 < splitEnd.length; i2++) {
            String s2 = splitEnd[i2];
            list_EndTime.add(s2);
        }
        selectionMoviehallListViewHolder.mSelectionmoviehalllistItemEnd.setText(list_EndTime.get(0)+":"+list_EndTime.get(1));
        //点击条目
        selectionMoviehallListViewHolder.mSelectionmoviehalllistItemLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectionMoviehallListViewHolder.mSelectionmoviehalllistItemSelect.setBackgroundResource(R.drawable.select_yes);
                if (callBackSelectionMoviehall!=null) {
                    callBackSelectionMoviehall.onSelectionMoviehall(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SelectionMoviehallListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.selectionmoviehalllist_item_name)
        TextView mSelectionmoviehalllistItemName;
        @BindView(R.id.selectionmoviehalllist_item_select)
        ImageView mSelectionmoviehalllistItemSelect;
        @BindView(R.id.selectionmoviehalllist_item_Start)
        TextView mSelectionmoviehalllistItemStart;
        @BindView(R.id.selectionmoviehalllist_item_End)
        TextView mSelectionmoviehalllistItemEnd;
        @BindView(R.id.selectionmoviehalllist_item_lin)
        LinearLayout mSelectionmoviehalllistItemLin;

        public SelectionMoviehallListViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    private CallBackSelectionMoviehall callBackSelectionMoviehall;

    public void setCallBackSelectionMoviehall(CallBackSelectionMoviehall callBackSelectionMoviehall) {
        this.callBackSelectionMoviehall = callBackSelectionMoviehall;
    }

    //接口回调
    public interface CallBackSelectionMoviehall {
        void onSelectionMoviehall(BeanFindMovieSchedule.ResultBean bean);
    }

}
