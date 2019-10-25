package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.bw.movie.R;
import com.bw.movie.bean.BeanFindSeatInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Xuexiandong
 * data: 2019/10/22 14:14:35
 * function：//根据影厅id 查询座位信息 适配器
 */
public class SelectionRecyclerView extends RecyclerView.Adapter<SelectionRecyclerView.SelectionViewHolder> {
    private List<BeanFindSeatInfo.ResultBean> list;
    private Context context;

    public SelectionRecyclerView(List<BeanFindSeatInfo.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //加载布局
    @NonNull
    @Override
    public SelectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.selectionrecyclerview_item_layout, viewGroup, false);
        return new SelectionViewHolder(inflate);
    }

    //加载数据
    @Override
    public void onBindViewHolder(@NonNull SelectionViewHolder selectionViewHolder, int i) {
        final BeanFindSeatInfo.ResultBean bean = list.get(i);
        //每个座位
        selectionViewHolder.mSelectionrecyclerviewItemSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack!=null) {
                    callBack.onSelect(bean.getRow()+bean.getSeat());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SelectionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.selectionrecyclerview_item_Selection)
        RadioButton mSelectionrecyclerviewItemSelection;
        public SelectionViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
    //接口回调
    public interface CallBack{
        void onSelect(String thisone);
    }

}
