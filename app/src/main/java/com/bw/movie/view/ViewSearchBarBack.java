package com.bw.movie.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.movie.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * author: Xuexiandong
 * data: 2019/10/11 19:19:04
 * function：搜索栏
 */
public class ViewSearchBarBack extends LinearLayout {

    @BindView(R.id.searchfor_EditText)
    EditText mSearchforEditText;
    @BindView(R.id.searchfor_sousuo)
    ImageView mSearchforSousuo;
    @BindView(R.id.searchfor_back)
    ImageView mSearchforBack;
    private View searchbar;

    public ViewSearchBarBack(Context context) {
        this(context, null);
    }

    public ViewSearchBarBack(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ViewSearchBarBack(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context);
        init(context, attrs);

    }

    //加载布局
    private void initAttr(Context context) {
        searchbar = LayoutInflater.from(context).inflate(R.layout.searchbar_back_layout, this, true);
        ButterKnife.bind(this,searchbar);

    }

    //自定义属性
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ViewSearchBarBack);
        //提示文字
        String hint_ = typedArray.getString(R.styleable.ViewSearchBarBack_hint_);
        if (!TextUtils.isEmpty(hint_)) {
            mSearchforEditText.setHint(hint_);
        }
        //提示文字颜色
        int hint_color = typedArray.getColor(R.styleable.ViewSearchBarBack_hint_color, Color.BLACK);
        mSearchforEditText.setHintTextColor(hint_color);
        //输入文字字号
        float text_size = typedArray.getDimension(R.styleable.ViewSearchBarBack_text_size, 20);
        mSearchforEditText.setTextSize(text_size);
        //输入文字颜色
        int text_color = typedArray.getColor(R.styleable.ViewSearchBarBack_text_color, Color.BLACK);
        mSearchforEditText.setTextColor(text_color);
        int img_ = typedArray.getResourceId(R.styleable.ViewSearchBarBack_img_, R.drawable.jiaban);
        mSearchforSousuo.setImageResource(img_);

    }

    //点击搜索
    @OnClick({R.id.searchfor_back, R.id.searchfor_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchfor_back:
                if (getETCallback!=null) {
                    getETCallback.onBack("");
                }
                break;
            case R.id.searchfor_sousuo:
//                Toast.makeText(getContext(), "AAAAA", Toast.LENGTH_SHORT).show();
                    String trim = mSearchforEditText.getText().toString().trim();
                    if (!TextUtils.isEmpty(trim)) {
                        if (getETCallback != null) {
                            getETCallback.onSuccess(trim);
                        }
                    }else {
                        Toast.makeText(getContext(), "搜索内容不能为空", Toast.LENGTH_SHORT).show();
                    }
                break;
        }
    }

    private getETCallback getETCallback;

    public void setGetETCallback(ViewSearchBarBack.getETCallback getETCallback) {
        this.getETCallback = getETCallback;
    }

    //创建接口
    public interface getETCallback {
        void onSuccess(String data);
        void onBack(String data);
    }
}
