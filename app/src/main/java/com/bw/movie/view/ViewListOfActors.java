package com.bw.movie.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;

import butterknife.BindView;

/**
 * author: Xuexiandong
 * data: 2019/10/17 14:14:40
 * function：演员表
 */
public class ViewListOfActors extends LinearLayout {

    @BindView(R.id.listofactors_img)
    ImageView mListofactorsImg;
    @BindView(R.id.listofactors_name)
    TextView mListofactorsName;
    private View inflate;

    public ViewListOfActors(Context context) {
        this(context, null);
    }

    public ViewListOfActors(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ViewListOfActors(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context);
    }

    //加载布局
    private void initAttr(Context context) {
        inflate = LayoutInflater.from(context).inflate(R.layout.listofactors_layout, this, true);


    }
}
