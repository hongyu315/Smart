package hongyu315.com.smart2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import hongyu315.com.smart2.bean.MenuBean;
import hongyu315.com.smart2.fragment.GoodsCommentsFragment;
import hongyu315.com.smart2.fragment.GoodsDetailFragment;
import hongyu315.com.smart2.fragment.GoodsFragment;

public class ShoppingTitleBarAdapter
        extends FragmentPagerAdapter
{
    private ArrayList<Fragment> mFragments;
    private ArrayList<MenuBean> mTitles;

    public ShoppingTitleBarAdapter(FragmentManager paramFragmentManager,
                                   ArrayList<Fragment> paramArrayList,
                                   ArrayList<MenuBean> paramArrayList1)
    {
        super(paramFragmentManager);
        this.mTitles = paramArrayList1;
        this.mFragments = paramArrayList;
    }

    public int getCount()
    {
        return this.mFragments.size();
    }

    public Fragment getItem(int paramInt)
    {
        if (paramInt == 0) {
            return GoodsFragment.getInstance();
        }
        if (paramInt == 1) {
            return GoodsDetailFragment.getInstance();
        }
        if (paramInt == 2) {
            return GoodsCommentsFragment.getInstance();
        }
        return this.mFragments.get(paramInt);
    }

    public CharSequence getPageTitle(int paramInt)
    {
        return this.mTitles.get(paramInt).getMenuName();
    }
}
