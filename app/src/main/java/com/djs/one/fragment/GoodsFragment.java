package com.com.one.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superrecycleview.superlibrary.recycleview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.com.one.R;
import com.com.one.adapter.GoodsAdapter;
import com.com.one.bean.Product;

public class GoodsFragment extends BaseFragment {

    private static final int TYPE_PULLREFRESH = 0;
    protected String TAG = "";
    private List<Product> datas = new ArrayList();
//    private Handler handler = new Handler()
//    {
//        public void handleMessage(Message paramAnonymousMessage)
//        {
//            switch (paramAnonymousMessage.what)
//            {
//                default:
//                    return;
//            }
//            GoodsFragment.this.updateloadData(0);
//        }
//    };
    protected Activity mActivity;
    private GoodsAdapter mGoodsAdapter;
    private SuperRecyclerView recyclerView;

    public GoodsFragment() {
    }
    public static GoodsFragment getInstance() {
        return new GoodsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goods, container, false);
    }

}
