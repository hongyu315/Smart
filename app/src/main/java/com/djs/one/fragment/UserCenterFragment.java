package com.djs.one.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import com.djs.one.R;
import com.djs.one.activity.AccountManagerActivity;
import com.djs.one.activity.FavoriteActivity;
import com.djs.one.activity.LoginActivity;
import com.djs.one.activity.MessageActivity;
import com.djs.one.activity.MyOrderActivity;
import com.djs.one.constant.Constant;
import com.djs.one.manager.UserManager;
import com.djs.one.util.SysUtils;

public class UserCenterFragment extends BaseFragment implements View.OnClickListener {

    private TitleBar titleBar;

    //个人中心登录模块
    private LinearLayout loginLayout;

    //已完成
    private LinearLayout haveDoneLayout;
    //待付款
    private LinearLayout waitForPayLayout;
    //待发货
    private LinearLayout waitForDeliverLayout;
    //待收货
    private LinearLayout waitForReceiveLayout;
    //收藏夹
    private LinearLayout favoriteLayout;

    //用户头像
    private ImageView userIcon;
    //立刻登录
    private TextView loginRightNowTextView;
    //登录后权益
    private TextView loginRigtsTextView;

    public UserCenterFragment() {
    }

    public static UserCenterFragment getInstance() {
        return new UserCenterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_center, container, false);
    }

    @Override
    public void onViewCreated(View paramView, Bundle paramBundle) {
        super.onViewCreated(paramView, paramBundle);

        findViews(paramView);
    }

    @Override
    protected void findViews(View paramView) {
        super.findViews(paramView);

        titleBar = paramView.findViewById(R.id.user_center_title_bar);
        titleBar.setLineVisible(false);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
            }

            @Override
            public void onTitleClick(View v) {
            }

            @Override
            public void onRightClick(View v) {
                if (UserManager.getInstance().isLogin()){
                    SysUtils.startActivity(getActivity(),MessageActivity.class);
                }else {
                    SysUtils.startActivity(getActivity(),LoginActivity.class);
                }
            }
        });

        loginLayout = paramView.findViewById(R.id.user_center_login_layout);
        userIcon = paramView.findViewById(R.id.user_center_icon);

        haveDoneLayout = paramView.findViewById(R.id.have_done_layout);
        waitForPayLayout = paramView.findViewById(R.id.wait_for_pay);
        waitForDeliverLayout = paramView.findViewById(R.id.wait_for_deliver);
        waitForReceiveLayout = paramView.findViewById(R.id.wait_for_receive);
        favoriteLayout = paramView.findViewById(R.id.favorite_layout);

        loginRightNowTextView = paramView.findViewById(R.id.login_right_now);
        loginRigtsTextView = paramView.findViewById(R.id.login_rights_text);

        loginLayout.setOnClickListener(this);
        haveDoneLayout.setOnClickListener(this);
        waitForPayLayout.setOnClickListener(this);
        waitForDeliverLayout.setOnClickListener(this);
        waitForReceiveLayout.setOnClickListener(this);
        favoriteLayout.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (UserManager.getInstance().isLogin()){
            String nickName = (String) UserManager.getInstance().getUser().getData().getNickname();
            String userIconUrl = (String) UserManager.getInstance().getUser().getData().getAvatar();
            if (!TextUtils.isEmpty(userIconUrl)){
                Glide.with(this).load(userIconUrl).into(userIcon);
            }

            loginRigtsTextView.setVisibility(View.GONE);
            loginRightNowTextView.setText(TextUtils.isEmpty(nickName) ?
                    UserManager.getInstance().getUser().getData().getMobile() : nickName);
        }
    }

    private void onLoginClick(){
        if (UserManager.getInstance().isLogin()){
            SysUtils.startActivity(getActivity(),AccountManagerActivity.class);
        }else {
            SysUtils.startActivity(getActivity(),LoginActivity.class);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_center_login_layout:
                onLoginClick();
                break;
            case R.id.have_done_layout:
                if (UserManager.getInstance().isLogin()){
                    Intent intent = new Intent(getActivity(),MyOrderActivity.class);
                    intent.putExtra("index",0);
                    getActivity().startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }else {
                    Intent intent3 = new Intent(getActivity(),LoginActivity.class);
                    intent3.putExtra(Constant.PAGE,MyOrderActivity.class.getName());
                    intent3.putExtra("index",0);
                    getActivity().startActivity(intent3);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }
                break;
            case R.id.wait_for_pay:
                if (UserManager.getInstance().isLogin()){
                    Intent intent1 = new Intent(getActivity(),MyOrderActivity.class);
                    intent1.putExtra("index",1);
                    getActivity().startActivity(intent1);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }else {
                    Intent intent3 = new Intent(getActivity(),LoginActivity.class);
                    intent3.putExtra(Constant.PAGE,MyOrderActivity.class.getName());
                    intent3.putExtra("index",1);
                    getActivity().startActivity(intent3);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }
                break;
            case R.id.wait_for_deliver:
                if (UserManager.getInstance().isLogin()){
                    Intent intent2 = new Intent(getActivity(),MyOrderActivity.class);
                    intent2.putExtra("index",2);
                    getActivity().startActivity(intent2);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }else {
                    Intent intent3 = new Intent(getActivity(),LoginActivity.class);
                    intent3.putExtra(Constant.PAGE,MyOrderActivity.class.getName());
                    intent3.putExtra("index",2);
                    getActivity().startActivity(intent3);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }
                break;
            case R.id.wait_for_receive:
                if (UserManager.getInstance().isLogin()){
                    Intent intent3 = new Intent(getActivity(),MyOrderActivity.class);
                    intent3.putExtra("index",3);
                    getActivity().startActivity(intent3);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }else {
                    Intent intent3 = new Intent(getActivity(),LoginActivity.class);
                    intent3.putExtra(Constant.PAGE,MyOrderActivity.class.getName());
                    intent3.putExtra("index",3);
                    getActivity().startActivity(intent3);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }
                break;
            case R.id.favorite_layout:
                if (UserManager.getInstance().isLogin()){
                    SysUtils.startActivity(getActivity(),FavoriteActivity.class);
                }else {
                    Intent intent3 = new Intent(getActivity(),LoginActivity.class);
                    intent3.putExtra(Constant.PAGE,FavoriteActivity.class.getName());
                    getActivity().startActivity(intent3);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }
                break;
            default:
                break;
        }
    }
}
