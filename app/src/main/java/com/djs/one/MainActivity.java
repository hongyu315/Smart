package com.djs.one;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.djs.one.activity.LoginActivity;
import com.djs.one.activity.SplashActivity;
import com.djs.one.bean.TabEntity;
import com.djs.one.fragment.HomeFragment;
import com.djs.one.fragment.MainFragment;
import com.djs.one.fragment.ShoppingFragment;
import com.djs.one.fragment.UserCenterFragment;
import com.djs.one.manager.UserManager;
import com.djs.one.util.ToastUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.jaeger.library.StatusBarUtil;
import com.umeng.message.PushAgent;

import java.util.ArrayList;

//import com.umeng.message.PushAgent;

public class MainActivity extends FragmentActivity {
    public static MainActivity mainActivity ;

    private String[] mTitles = { "首页","分类", "购物车", "我的" };

    private long exitTime = 0L;
    private ArrayList<Fragment> mFragments = new ArrayList();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList();
    private static CommonTabLayout mTabLayout;
    private int[] mIconSelectIds = { R.mipmap.tab_home_select,  R.mipmap.tab_category_selected,R.mipmap.shopping_cat_select, R.mipmap.tab_user_select };
    private int[] mIconUnselectIds = { R.mipmap.tab_home_unselect, R.mipmap.tab_category_unselected,R.mipmap.shopping_cat_unselect, R.mipmap.tab_user_unselect };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (UserManager.getInstance().isLogin()){
            setContentView(R.layout.content_main);

            StatusBarUtil.setColor(MainActivity.this,getResources().getColor(R.color.white),1);

            findViews();

            PushAgent.getInstance(MainActivity.this).onAppStart();

            mainActivity = this;
        }else {
            Intent intent ;
            intent= new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        }
    }

    protected void findViews()
    {

        this.mFragments.add(MainFragment.getInstance());
        this.mFragments.add(HomeFragment.getInstance());
        this.mFragments.add(ShoppingFragment.getInstance());
        this.mFragments.add(UserCenterFragment.getInstance());

        int i = 0;
        while (i < this.mTitles.length)
        {
            this.mTabEntities.add(new TabEntity(mTitles[i], this.mIconSelectIds[i], this.mIconUnselectIds[i]));
            i += 1;
        }
        mTabLayout = findViewById(R.id.mainTabLayout);
        mTabLayout.setTabData(this.mTabEntities,this,R.id.container_layout,this.mFragments);
    }

    public static void setCurrentTab(int paramInt)
    {
        mTabLayout.setCurrentTab(paramInt);
    }

    public static void showDot(int paramInt1, int paramInt2)
    {
        if (paramInt1 == 0) {
            mTabLayout.showDot(0);
        }
        if (paramInt1 == 1) {
            mTabLayout.showMsg(1, paramInt2);
        }
        if (paramInt1 == 2) {
            mTabLayout.showDot(2);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4){
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit()
    {
        if (System.currentTimeMillis() - this.exitTime > 2000L)
        {
            ToastUtils.showToast(this, "再按一次退出程序");
            this.exitTime = System.currentTimeMillis();
            return;
        }
        finish();
        System.exit(0);
    }

}
