package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.SettingActivity;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBaseView;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author: Xuexiandong
 * data: 2019/9/29 21:21:00
 * function：我的
 */
public class FragmentMine extends BaseFragment implements Contract.HomeView {
    @BindView(R.id.mine_)
    TextView mMine;
    @BindView(R.id.mine_xinxi)
    ImageView mMineXinxi;
    @BindView(R.id.back)
    RelativeLayout mBack;
    @BindView(R.id.mine_name)
    RelativeLayout mMineName;
    @BindView(R.id.mine_movieticket)
    RelativeLayout mMineMovieticket;
    @BindView(R.id.mine_attention)
    RadioButton mMineAttention;
    @BindView(R.id.mine_reservation)
    RadioButton mMineReservation;
    @BindView(R.id.mine_recording)
    RadioButton mMineRecording;
    @BindView(R.id.mine_Seen)
    RadioButton mMineSeen;
    @BindView(R.id.mine_comment)
    RadioButton mMineComment;
    @BindView(R.id.mine_opinion)
    RadioButton mMineOpinion;
    @BindView(R.id.mine_Setting)
    RadioButton mMineSetting;
    Unbinder unbinder;
    private static final String TAG = "FragmentMine";
    private Presenter presenter;

    //布局
    @Override
    public int initLayoutInflater() {
        return R.layout.fragment_mine_layout;
    }

    //注册
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    //点击
    @OnClick({R.id.mine_xinxi, R.id.mine_name, R.id.mine_movieticket, R.id.mine_attention, R.id.mine_reservation, R.id.mine_recording, R.id.mine_Seen, R.id.mine_comment, R.id.mine_opinion, R.id.mine_Setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_xinxi://信息
                Toast.makeText(getContext(), "以点击信息", Toast.LENGTH_SHORT).show();

                break;
            case R.id.mine_name://个人信息
                Toast.makeText(getContext(), "以点击个人信息", Toast.LENGTH_SHORT).show();

                break;
            case R.id.mine_movieticket://电影票
                Toast.makeText(getContext(), "以点击电影票", Toast.LENGTH_SHORT).show();

                break;
            case R.id.mine_attention://我的关注
                Toast.makeText(getContext(), "以点击我的关注", Toast.LENGTH_SHORT).show();

                break;
            case R.id.mine_reservation://我的预约
                Toast.makeText(getContext(), "以点击我的预约", Toast.LENGTH_SHORT).show();

                break;
            case R.id.mine_recording://购票记录
                Toast.makeText(getContext(), "以点击购票记录", Toast.LENGTH_SHORT).show();

                break;
            case R.id.mine_Seen://看过的电影
                Toast.makeText(getContext(), "以点击看过的电影", Toast.LENGTH_SHORT).show();

                break;
            case R.id.mine_comment://我的评论
                Toast.makeText(getContext(), "以点击我的评论", Toast.LENGTH_SHORT).show();

                break;
            case R.id.mine_opinion://反馈意见
                Toast.makeText(getContext(), "以点击反馈意见", Toast.LENGTH_SHORT).show();

                break;
            case R.id.mine_Setting://设置
                Toast.makeText(getContext(), "以点击设置", Toast.LENGTH_SHORT).show();
                getActivity().startActivity(new Intent(getContext(), SettingActivity.class));
                break;
        }
    }

    //数据
    @Override
    public void initData() {
        presenter = new Presenter(this);
    }

    //成功
    @Override
    public void onSuccess(Object object) {

    }

    //失败
    @Override
    public void onFailure(String failure) {

    }

    //释放
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
