package hongyu315.com.smart2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.activity.AccountManagerActivity;
import hongyu315.com.smart2.activity.FavoriteActivity;
import hongyu315.com.smart2.activity.LoginActivity;
import hongyu315.com.smart2.activity.MessageActivity;
import hongyu315.com.smart2.activity.MyOrderActivity;
import hongyu315.com.smart2.manager.UserManager;
import hongyu315.com.smart2.util.SysUtils;
import hongyu315.com.smart2.view.TopTitleBarView;

public class UserCenterFragment extends BaseFragment implements View.OnClickListener {

    private TopTitleBarView titleBarView;

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

        titleBarView = ((TopTitleBarView) paramView.findViewById(R.id.topTitleBarView));
        titleBarView.mTopTitleBarViewBg.setBackgroundResource(R.color.bg_color);
        titleBarView.mTvTitle.setTextColor(getResources().getColor(R.color.white));
        titleBarView.mTvTitle.setText(getResources().getString(R.string.user_center));
        titleBarView.mTvRightSearch.setImageResource(R.mipmap.menu_icon);
        titleBarView.mTvRightSearch.setOnClickListener(this);

        loginLayout = (LinearLayout) paramView.findViewById(R.id.user_center_login_layout);
        haveDoneLayout = (LinearLayout)paramView.findViewById(R.id.have_done_layout);
        waitForPayLayout = (LinearLayout)paramView.findViewById(R.id.wait_for_pay);
        waitForDeliverLayout = (LinearLayout)paramView.findViewById(R.id.wait_for_deliver);
        waitForReceiveLayout = (LinearLayout)paramView.findViewById(R.id.wait_for_receive);
        favoriteLayout = (LinearLayout)paramView.findViewById(R.id.favorite_layout);

        loginLayout.setOnClickListener(this);
        haveDoneLayout.setOnClickListener(this);
        waitForPayLayout.setOnClickListener(this);
        waitForDeliverLayout.setOnClickListener(this);
        waitForReceiveLayout.setOnClickListener(this);
        favoriteLayout.setOnClickListener(this);
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
            case R.id.top_title_bar_search:
                SysUtils.startActivity(getActivity(),MessageActivity.class);
                break;
            case R.id.have_done_layout:
                Intent intent = new Intent(getActivity(),MyOrderActivity.class);
                intent.putExtra("index",0);
                getActivity().startActivity(intent);
                break;
            case R.id.wait_for_pay:
                Intent intent1 = new Intent(getActivity(),MyOrderActivity.class);
                intent1.putExtra("index",1);
                getActivity().startActivity(intent1);
                break;
            case R.id.wait_for_deliver:
                Intent intent2 = new Intent(getActivity(),MyOrderActivity.class);
                intent2.putExtra("index",2);
                getActivity().startActivity(intent2);
                break;
            case R.id.wait_for_receive:
                Intent intent3 = new Intent(getActivity(),MyOrderActivity.class);
                intent3.putExtra("index",3);
                getActivity().startActivity(intent3);
                break;
            case R.id.favorite_layout:
                SysUtils.startActivity(getActivity(),FavoriteActivity.class);
                break;
            default:
                break;
        }
    }
}
