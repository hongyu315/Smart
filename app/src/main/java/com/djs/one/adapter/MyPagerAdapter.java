package com.com.one.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

public class MyPagerAdapter
        extends FragmentPagerAdapter
{
    private FragmentManager mfragmentManager;
    private List<Fragment> mlist;

    public MyPagerAdapter(FragmentManager paramFragmentManager, List<Fragment> paramList)
    {
        super(paramFragmentManager);
        this.mlist = paramList;
        this.mfragmentManager = paramFragmentManager;
    }

    public int getCount()
    {
        return this.mlist.size();
    }

    public Fragment getItem(int paramInt)
    {
        return this.mlist.get(paramInt);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
