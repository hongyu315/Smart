package com.com.one.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.com.one.R;

public class GoodsDetailFragment extends BaseFragment {

    public GoodsDetailFragment() {
        // Required empty public constructor
    }

    public static GoodsDetailFragment getInstance() {
        return new GoodsDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goods_detail, container, false);
    }
}
