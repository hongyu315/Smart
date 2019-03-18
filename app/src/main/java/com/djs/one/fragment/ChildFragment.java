package com.com.one.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChildFragment
        extends Fragment
{
    private String mTitle;

    public static ChildFragment getInstance(String paramString)
    {
        ChildFragment localChildFragment = new ChildFragment();
        localChildFragment.mTitle = paramString;
        return localChildFragment;
    }

    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        return null;
//        paramLayoutInflater = paramLayoutInflater.inflate(2130968638, null);
//        ((TextView)paramLayoutInflater.findViewById(2131558677)).setText(this.mTitle);
//        return paramLayoutInflater;
    }
}
