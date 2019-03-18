package com.djs.one.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djs.one.R;

public class GoodsCommentsFragment extends BaseFragment {

    public GoodsCommentsFragment() {
        // Required empty public constructor
    }

    public static GoodsCommentsFragment getInstance() {
        return new GoodsCommentsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goods_comments, container, false);
    }

}
