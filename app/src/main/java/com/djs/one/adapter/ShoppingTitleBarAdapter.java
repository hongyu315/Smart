package com.djs.one.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import com.djs.one.bean.MenuBean;
import com.djs.one.fragment.GoodsCommentsFragment;
import com.djs.one.fragment.GoodsDetailFragment;
import com.djs.one.fragment.GoodsFragment;

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
