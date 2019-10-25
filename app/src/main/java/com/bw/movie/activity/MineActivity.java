package com.bw.movie.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.BeanReleaseMovie;
import com.bw.movie.bean.BeanWeChatBindingLogin;
import com.bw.movie.util.Api;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author: Xuexiandong
 * data: 2019/9/29 21:21:00
 * function：我的(个人信息)
 */
public class MineActivity extends BaseActivity {


    @BindView(R.id.mine_fan)
    ImageView mMineFan;
    @BindView(R.id.mine_tou)
    RelativeLayout mMineTou;
    @BindView(R.id.mine_name)
    RelativeLayout mMineName;
    @BindView(R.id.mine_sex)
    RelativeLayout mMineSex;
    @BindView(R.id.mine_birthday)
    RelativeLayout mMineBirthday;
    @BindView(R.id.mine_mailbox)
    LinearLayout mMineMailbox;
    private Unbinder bind;

    //布局
    @Override
    public int initLayoutResID() {
        return R.layout.activity_mine;
    }

    //注册
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        bind = ButterKnife.bind(this);
    }
    //点击
    @OnClick({R.id.mine_fan, R.id.mine_tou, R.id.mine_name, R.id.mine_sex, R.id.mine_birthday, R.id.mine_mailbox})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_fan:
                Toast.makeText(this, "已返回", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.mine_tou:
                Toast.makeText(this, "修改头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_name:
                Toast.makeText(this, "修改昵称", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_sex:
                Toast.makeText(this, "修改性别", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_birthday:
                Toast.makeText(this, "修改生日", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_mailbox:
                Toast.makeText(this, "修改邮箱", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    //接收EventBus    bean
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getDataBean(BeanWeChatBindingLogin bean) {
        if (bean!=null) {

        }
    }
    //对象
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    //数据
    @Override
    public void initData() {

    }

    //释放
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

}
