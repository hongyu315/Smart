package hongyu315.com.smart2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import hongyu315.com.smart2.bean.TabEntity;
import hongyu315.com.smart2.fragment.HomeFragment;
import hongyu315.com.smart2.fragment.ShoppingFragment;
import hongyu315.com.smart2.fragment.UserCenterFragment;
import hongyu315.com.smart2.util.ToastUtils;

public class MainActivity extends FragmentActivity {

    private String[] mTitles = { "首页", "购物车", "我的" };
    private long exitTime = 0L;
    private ArrayList<Fragment> mFragments = new ArrayList();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList();
    private static CommonTabLayout mTabLayout;
    private int[] mIconSelectIds = { R.mipmap.tab_home_select,  R.mipmap.shopping_cat_select, R.mipmap.tab_user_select };
    private int[] mIconUnselectIds = { R.mipmap.tab_home_unselect, R.mipmap.shopping_cat_unselect, R.mipmap.tab_user_unselect };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_main);

        findViews();
//        try
//        {
////            if ((Product)getIntent().getSerializableExtra("product") != null) {
////                setCurrentTab(2);
////            }
//            return;
//        }
//        catch (Exception paramBundle) {}
    }

    protected void findViews()
    {
        this.mFragments.add(HomeFragment.getInstance());
        this.mFragments.add(ShoppingFragment.getInstance());
        this.mFragments.add(UserCenterFragment.getInstance());

        int i = 0;
        while (i < this.mTitles.length)
        {
            this.mTabEntities.add(new TabEntity(mTitles[i], this.mIconSelectIds[i], this.mIconUnselectIds[i]));
            i += 1;
        }
        mTabLayout = (CommonTabLayout)findViewById(R.id.mainTabLayout);
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
