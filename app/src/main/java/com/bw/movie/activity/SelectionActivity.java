package com.bw.movie.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.adapter.SelectionMoviehallList;
import com.bw.movie.adapter.SelectionRecyclerView;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.BeanBuyMovieTickets;
import com.bw.movie.bean.BeanFindMovieSchedule;
import com.bw.movie.bean.BeanFindSeatInfo;
import com.bw.movie.bean.BeanPay;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.util.Api;
import com.bw.movie.util.App;
import com.bw.movie.util.EncryptionMD5;
import com.bw.movie.util.RxJavaUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * author: Xuexiandong
 * data: 2019/10/18 19:19:09
 * function：影院选座
 */
public class SelectionActivity extends BaseActivity implements Contract.HomeView {
    @BindView(R.id.selection_back)
    ImageView mSelectionBack;
    @BindView(R.id.selection_JCVideoPlayer)
    JCVideoPlayer mSelectionJCVideoPlayer;
    @BindView(R.id.selection_RecyclerView)
    RecyclerView mSelectionRecyclerView;
    @BindView(R.id.selection_Moviehall)
    TextView mSelectionMoviehall;
    @BindView(R.id.selection_Moviehall_list)
    RecyclerView mSelectionMoviehallList;
    @BindView(R.id.selection_name)
    TextView mSelectionName;
    @BindView(R.id.Pay)
    TextView mPay;
    @BindView(R.id.selection_lin)
    LinearLayout mSelectionLin;
    @BindView(R.id.Pay_weixin)
    RadioButton mPayWeixin;
    @BindView(R.id.Pay_zhifubao)
    RadioButton mPayZhifubao;
    @BindView(R.id.Pay_frame)
    LinearLayout mPayFrame;
    @BindView(R.id.selection_)
    RelativeLayout mSelection;
    @BindView(R.id.Pay_cancel)
    TextView mPayCancel;
    private Unbinder bind;
    private static final String TAG = "SelectionActivity";
    private Presenter presenter;
    private Map<String, Integer> qmap = new HashMap<>();
    private Map<String, Integer> qqmap = new HashMap<>();
    private Map<String, String> hmap = new HashMap<>();
    private Map<String, Object> smap = new HashMap<>();
    private Map<String, Object> zfmap = new HashMap<>();
    private int movieId;
    private int cinemaId;
    private String orderId;
    private SharedPreferences sp;
    private double amount;
    private int zf = 0;

    //布局
    @Override
    public int initLayoutResID() {
        return R.layout.activity_selection;
    }

    //注册
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        bind = ButterKnife.bind(this);
        //获取SharedPreferences
        sp = getSharedPreferences(Api.SP_SP, MODE_PRIVATE);
        //获取电影和
        movieId = RxJavaUtil.getInstance().getMovie().getMovieId();
        cinemaId = RxJavaUtil.getInstance().getMovie().getCinemaId();
        String videoPath = RxJavaUtil.getInstance().getMovie().getVideoPath();
        String movieName = RxJavaUtil.getInstance().getMovie().getMovieName();
        String videoPathImg = RxJavaUtil.getInstance().getMovie().getVideoPathImg();
        Log.i("movieId111", "onCreate: " + movieId);
        Log.i("cinemaId111", "onCreate: " + cinemaId);
        Log.i("videoPath111", "onCreate: " + videoPath);
        Log.i("movieName11", "onCreate: " + movieName);
        //视频
        mSelectionJCVideoPlayer.setUp(videoPath, "");
        //视频预览图
        ImageView ivThumb = mSelectionJCVideoPlayer.ivThumb;
        ivThumb.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(this).load(videoPathImg).into(ivThumb);
        //电影名
        mSelectionName.setText(movieName);

        //根据电影ID和影院ID查询电影排期列表
        qmap.put(Api.URL_MOVIEID, movieId);
        qmap.put(Api.URL_CINEMAID, cinemaId);
        presenter.getFindMovieSchedule(qmap);

        //根据影厅id 查询座位信息
        qqmap.put(Api.URL_HALLID, 5);
        presenter.getFindSeatInfo(qmap);
    }

    //点击
    @OnClick({R.id.selection_back, R.id.selection_JCVideoPlayer, R.id.Pay, R.id.Pay_weixin, R.id.Pay_zhifubao, R.id.selection_, R.id.Pay_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.selection_back://返回
                //Toast.makeText(this, "已返回", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.Pay://点击支付
                //Toast.makeText(this, "选择支付方式", Toast.LENGTH_SHORT).show();
                //支付选择
                //showShadow();
                //showAnimation();
                mPayFrame.setVisibility(View.VISIBLE);
                break;
            case R.id.Pay_weixin://点击微信
                //Toast.makeText(this, "微信", Toast.LENGTH_SHORT).show();
                zf = 1;
                //支付金额
//                mPay.setText("微信支付"+amount+"元");
                mPayCancel.setText("微信支付" + amount + "元");
                break;
            case R.id.Pay_zhifubao://点击支付宝
                //Toast.makeText(this, "支付宝", Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, "" + orderId, Toast.LENGTH_SHORT).show();
                zf = 2;
                mPayCancel.setText("支付宝支付" + amount + "元");
                break;
            case R.id.selection_://点击隐藏支付窗口
                //Toast.makeText(this, "隐藏", Toast.LENGTH_SHORT).show();
                mPayFrame.setVisibility(View.GONE);
                break;
            case R.id.Pay_cancel://点击取消隐藏支付窗口
                //Toast.makeText(this, "隐藏", Toast.LENGTH_SHORT).show();
                mPayFrame.setVisibility(View.GONE);
                //获取userid和sessionid
                String userid = sp.getString(Api.SP_USERID, "");
                String sessionid = sp.getString(Api.SP_SESSIONID, "");
                //支付
                switch (zf) {
                    /*case 0:
                        mPayFrame.setVisibility(View.GONE);
                        break;*/
                    case 1:
                        //微信支付
                        //Toast.makeText(this, "微信支付已支付", Toast.LENGTH_SHORT).show();
                        hmap.put(Api.SP_USERID, userid);
                        hmap.put(Api.SP_SESSIONID, sessionid);
                        zfmap.put(Api.SP_PAYTYPE, 1);
                        zfmap.put(Api.SP_ORDERID, orderId);
                        presenter.getPay(hmap, zfmap);
                        mPayFrame.setVisibility(View.GONE);
                        break;
                    case 2:
                        //支付宝支付
                        Toast.makeText(this, "支付宝支付已支付", Toast.LENGTH_SHORT).show();
                        mPayFrame.setVisibility(View.GONE);
                        break;
                    default:
                        mPayFrame.setVisibility(View.GONE);
                        break;
                }
                break;
        }
    }

    /*//支付选择
    private void showShadow() {
        LayoutInflater inflater = (LayoutInflater) SelectionActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vPopupWindow = inflater.inflate(R.layout.popupwindow_item_layout, null, false);//引入弹窗布局
        PopupWindow popupWindow = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //引入依附的布局
        View parentView = LayoutInflater.from(SelectionActivity.this).inflate(R.layout.popupwindow_item_layout, null);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(true);
        //设置焦点
        popupWindow.setFocusable(true);
        //找控件
        RadioButton Pay_weixin = parentView.findViewById(R.id.Pay_weixin);
        RadioButton Pay_zhifubao = parentView.findViewById(R.id.Pay_zhifubao);
        //点击微信支付
        Pay_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectionActivity.this, "微信", Toast.LENGTH_SHORT).show();
            }
        });
        //点击支付宝支付
        Pay_zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectionActivity.this, "支付宝", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showAnimation() {
        *//*LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vPopupWindow = inflater.inflate(R.layout.popupwindow_item_layout, null, false);//引入弹窗布局
        popupWindow = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置背景透明
//        addBackground();

        //设置进出动画
//        popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);

        //引入依附的布局
        View parentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_item_layout, null);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);*//*
    }*/
    //调用微信支付
    /*private void wxPay(WxPayBean wxPayBean){
        //WxPayBean是我自己项目的服务器返回的微信支付签名的数据，这个需要你和你们公司后台对接
        WxPayBean.ResultBean result = wxPayBean.getResult();
        //调用API前，需要先向微信注册您的APPID
        IWXAPI wxAPI = WXAPIFactory.createWXAPI(this, Api.WX_APP_ID);
        wxAPI.registerApp(result.getAppid()); // 将该app注册到微信
        PayReq req = new PayReq();
        req.appId = result.getAppid(); //微信开放平台审核通过的应用APPID
        req.partnerId = result.getPartnerid(); //商户号
        req.prepayId = result.getPrepayid(); //微信返回的支付交易会话ID
        req.packageValue = "Sign=WXPay"; //固定值Sign=WXPay
        req.nonceStr = result.getNoncestr(); //随机字符串
        req.timeStamp = result.getTimestamp(); //时间戳
        req.sign = result.getSign(); //签名
        //调起微信支付
        wxAPI.sendReq(req);
    }*/

    //对象
    @Override
    public BasePresenter initPresenter() {
        presenter = new Presenter(this);
        return presenter;
    }

    //数据
    @Override
    public void initData() {

    }

    //成功
    @Override
    public void onSuccess(Object object) {
        Log.i(TAG, "onSuccess: " + object);
        if (object instanceof BeanFindMovieSchedule) {
            //选择影厅
            BeanFindMovieSchedule beanFindMovieSchedule = (BeanFindMovieSchedule) object;
            List<BeanFindMovieSchedule.ResultBean> beanFindMovieScheduleResult = beanFindMovieSchedule.getResult();
            Log.i(TAG, "11111111111onSuccess: " + beanFindMovieScheduleResult.get(0).getScreeningHall());
            //设置适配器
            SelectionMoviehallList selectionMoviehallList = new SelectionMoviehallList(beanFindMovieScheduleResult, this);
            //线性    水平
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
            mSelectionMoviehallList.setLayoutManager(linearLayoutManager);
            //设置适配器
            mSelectionMoviehallList.setAdapter(selectionMoviehallList);
            //点击选择影厅条目
            selectionMoviehallList.setCallBackSelectionMoviehall(new SelectionMoviehallList.CallBackSelectionMoviehall() {


                @Override
                public void onSelectionMoviehall(BeanFindMovieSchedule.ResultBean bean) {
                    //已选择影厅
                    //Toast.makeText(SelectionActivity.this, "" + bean.getScreeningHall(), Toast.LENGTH_SHORT).show();

                    //userid
                    String userid = sp.getString(Api.SP_USERID, "");
                    //sessionid
                    String sessionid = sp.getString(Api.SP_SESSIONID, "");
                    //获取排期时间
                    int id = bean.getId();
                    String encryption = "";
                    //加密
                    encryption = userid + id + "movie";
                    EncryptionMD5 encryptionMD5 = new EncryptionMD5();
                    //得到签名
                    String signature = encryptionMD5.MD5(encryption);

                    //购票下单
                    hmap.put(Api.SP_USERID, userid);
                    hmap.put(Api.SP_SESSIONID, sessionid);

                    smap.put(Api.URL_SCHEDULEID, id);
                    smap.put(Api.URL_SANQI, "1-1");
                    smap.put(Api.URL_SIGN, signature);

                    presenter.getBuyMovieTickets(hmap, smap);
                    //已选电影金额
                    amount = bean.getFare();
                    mPay.setText(amount + "元");
                    mPayCancel.setText("需支付" + amount + "元");
                    mPay.setVisibility(View.VISIBLE);
                }
            });
        } else if (object instanceof BeanFindSeatInfo) {
            //根据影厅id 查询座位信息
            BeanFindSeatInfo beanFindSeatInfo = (BeanFindSeatInfo) object;
            List<BeanFindSeatInfo.ResultBean> beanFindSeatInfoResult = beanFindSeatInfo.getResult();
            //Log.i(TAG, "sssssssonSuccess: " + beanFindSeatInfoResult.get(0).getSeat() + beanFindSeatInfoResult.get(0).getRow());
            //设置适配器
            SelectionRecyclerView selectionRecyclerView = new SelectionRecyclerView(beanFindSeatInfoResult, this);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
            mSelectionRecyclerView.setLayoutManager(gridLayoutManager);
            mSelectionRecyclerView.setAdapter(selectionRecyclerView);
            //点击哪个座位
            selectionRecyclerView.setCallBack(new SelectionRecyclerView.CallBack() {
                @Override
                public void onSelect(String thisone) {
                    Toast.makeText(SelectionActivity.this, "" + thisone, Toast.LENGTH_SHORT).show();
                }
            });
        } else if (object instanceof BeanBuyMovieTickets) {
            //购票下单
            BeanBuyMovieTickets beanBuyMovieTickets = (BeanBuyMovieTickets) object;
            //提示消息
            String message = beanBuyMovieTickets.getMessage();
            //单号
            orderId = beanBuyMovieTickets.getOrderId();
            Log.i(TAG, "onSuccess: " + message);
            //Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
        } else if (object instanceof BeanPay) {
            //支付
            BeanPay beanPay = (BeanPay) object;
            String message = beanPay.getMessage();
            Log.i(TAG, "message: " + message);
            String appId = beanPay.getAppId();
            Log.i(TAG, "appId: " + appId);
            String nonceStr = beanPay.getNonceStr();
            Log.i(TAG, "nonceStr: " + nonceStr);
            String packageValue = beanPay.getPackageValue();
            Log.i(TAG, "packageValue: " + packageValue);
            String partnerId = beanPay.getPartnerId();
            String prepayId = beanPay.getPrepayId();
            Log.i(TAG, "partnerId: " + partnerId);
            Log.i(TAG, "prepayId: " + prepayId);
            String sign = beanPay.getSign();
            Log.i(TAG, "sign: " + sign);
            String status = beanPay.getStatus();
            Log.i(TAG, "status: " + status);
            String timeStamp = beanPay.getTimeStamp();
            Log.i(TAG, "timeStamp: " + timeStamp);
            //微信支付
            App.WXZF(appId, partnerId, prepayId, packageValue, nonceStr, timeStamp, sign);
        }
    }

    //失败
    @Override
    public void onFailure(String failure) {
        Log.i(TAG, "onFailure: " + failure);
        Toast.makeText(this, "" + failure, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSelectionJCVideoPlayer.releaseAllVideos();
    }

    //释放
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        mSelectionJCVideoPlayer.releaseAllVideos();
    }
}
