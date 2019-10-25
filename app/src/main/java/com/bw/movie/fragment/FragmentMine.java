package com.bw.movie.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.activity.MineActivity;
import com.bw.movie.activity.SettingActivity;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.BeanWeChatBindingLogin;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.util.Api;
import com.bw.movie.util.App;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

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
    @BindView(R.id.mine_img)
    ImageView mMineImg;
    @BindView(R.id.mine_name_)
    TextView mMineName_;
    private Presenter presenter;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private List<String >list=new ArrayList<>();
    private Map<String, String> wxmap = new HashMap<>();

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
        sp = App.sContext.getSharedPreferences(Api.SP_SP, Context.MODE_PRIVATE);
        edit = sp.edit();
        //注册
        //EventBus.getDefault().register(this);
        return rootView;
    }

    /*//接收EventBus    bean
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getDataBean(BeanWeChatBindingLogin bean) {
        if (bean != null) {
            //微信userId
            int userId = bean.getResult().getUserId();
            //微信sessionId
            String sessionId = bean.getResult().getSessionId();
            //存入SharedPreferences
            edit.putString(Api.SP_USERID,userId+"");
            edit.putString(Api.SP_SESSIONID,sessionId);
            edit.putBoolean(Api.SP_IDENTIFICATION,true);
            edit.commit();
            //用户信息
            BeanWeChatBindingLogin.ResultBean.UserInfoBean userInfo = bean.getResult().getUserInfo();
            //用户名
            String nickName = userInfo.getNickName();
            String[] s = nickName.split("_");
            for (int i = 0; i < s.length; i++) {
                String s1 = s[i];
                list.add(s1);
            }
            mMineName_.setText(list.get(0));
            //用户头像
            String headPic = userInfo.getHeadPic();
            Glide.with(getContext())
                    .load(headPic)
                    .error(R.drawable.jiaban)
                    .placeholder(R.drawable.afanti)
                    .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(30, 0)))
                    .into(mMineImg);
            //性别
            int sex = userInfo.getSex();
        }
    }*/

    //点击
    @OnClick({R.id.mine_xinxi, R.id.mine_name, R.id.mine_movieticket, R.id.mine_attention, R.id.mine_reservation, R.id.mine_recording, R.id.mine_Seen, R.id.mine_comment, R.id.mine_opinion, R.id.mine_Setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_xinxi://信息
                Toast.makeText(getContext(), "以点击信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_name://个人信息
                Toast.makeText(getContext(), "以点击个人信息", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), MineActivity.class));
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

        //微信登录
        SharedPreferences sp = getContext().getSharedPreferences(Api.SP_SP, Context.MODE_PRIVATE);
        String code = sp.getString(Api.SP_CODE, "");
        if (TextUtils.isEmpty(code)){
            return;
        }
        wxmap.put(Api.SP_CODE,code);
        presenter.getWeChatBindingLogin(wxmap);
    }

    //成功
    @Override
    public void onSuccess(Object object) {
        //微信登录
        BeanWeChatBindingLogin beanWeChatBindingLogin= (BeanWeChatBindingLogin) object;
        String json = new Gson().toJson(beanWeChatBindingLogin);
        Log.i(TAG, "jsononSuccess: "+json);
        BeanWeChatBindingLogin.ResultBean beanWeChatBindingLoginResult = beanWeChatBindingLogin.getResult();
        //Log.i(TAG, "onSuccess: "+beanWeChatBindingLoginResult.getUserInfo().getNickName());
//        Toast.makeText(getContext(), "微信登录成功", Toast.LENGTH_SHORT).show();
        //传值
//        EventBus.getDefault().postSticky(beanWeChatBindingLogin);
        String s2 = beanWeChatBindingLogin.getResult().getUserInfo().toString();
        Log.i(TAG, "s2onSuccess: "+s2);
        //微信userId
        int userId = beanWeChatBindingLoginResult.getUserId();
        Log.i(TAG, "userId: "+userId);
        //微信sessionId
        String sessionId = beanWeChatBindingLoginResult.getSessionId();
        Log.i(TAG, "sessionId: "+sessionId);
        //存入SharedPreferences
        edit.putString(Api.SP_USERID,userId+"");
        edit.putString(Api.SP_SESSIONID,sessionId);
        edit.putBoolean(Api.SP_IDENTIFICATION,true);
        edit.commit();
        //用户信息
        BeanWeChatBindingLogin.ResultBean.UserInfoBean userInfo = beanWeChatBindingLoginResult.getUserInfo();
        //用户名
        String nickName = userInfo.getNickName();
        String[] s = nickName.split("_");
        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            list.add(s1);
        }
        mMineName_.setText(list.get(0));
        //用户头像
        String headPic = userInfo.getHeadPic();
        Glide.with(getContext())
                .load(headPic)
                .error(R.drawable.jiaban)
                .placeholder(R.drawable.afanti)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(mMineImg);
        //性别
        int sex = userInfo.getSex();
        Log.i(TAG, "sexonSuccess: "+sex);
    }

    //失败
    @Override
    public void onFailure(String failure) {
        Log.i(TAG, "onFailure: "+failure);
//        Toast.makeText(getContext(), ""+failure, Toast.LENGTH_SHORT).show();
    }

    //释放
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
