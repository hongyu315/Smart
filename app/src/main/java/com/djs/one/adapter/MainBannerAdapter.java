package com.djs.one.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.djs.one.R;
import com.djs.one.bean.HomeBanners;
import com.djs.one.util.SysUtils;

import java.util.List;

public class MainBannerAdapter extends BaseAdapter {

    List<HomeBanners.Data> banners;
    LayoutInflater mLayoutInflater;
    Activity mContext;

    public MainBannerAdapter(Activity context, List<HomeBanners.Data> list) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        banners = list;
    }

    @Override
    public int getCount() {
        return  banners.size();
    }

    @Override
    public Object getItem(int i) {
        return banners.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();

            view = mLayoutInflater.inflate(R.layout.home_banner_image_layout,null);

            viewHolder.img = view.findViewById(R.id.home_banner_img);

            ViewGroup.LayoutParams layoutParams = viewHolder.img.getLayoutParams();
            layoutParams.width = SysUtils.getScreenWidth(mContext);
            layoutParams.height = SysUtils.getScreenWidth(mContext);
            viewHolder.img.setLayoutParams(layoutParams);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Glide.with(mContext).load(banners.get(i).getImages_url()).into(viewHolder.img);

        return view;
    }

    public class ViewHolder{
        ImageView img;
    }
}
