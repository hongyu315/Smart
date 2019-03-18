package com.djs.one.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import com.djs.one.R;
import com.djs.one.bean.Product;


public class UserCenterProductAdapter extends BaseAdapter {

    private List<Product> productList = new ArrayList<>();
    private LayoutInflater mInflater;//布局装载器对象
    private Context context;
    private Product product;

    public UserCenterProductAdapter(Context mContext){
        this.context = mContext;
    }

    public UserCenterProductAdapter(Context mContenxt, List<Product> mDatas){
        this.context = mContenxt;
        mInflater = LayoutInflater.from(mContenxt);
        productList = mDatas;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.screen_adapter_layout,null);

            viewHolder.productIcon = convertView.findViewById(R.id.product_img);
            viewHolder.productName = convertView.findViewById(R.id.product_txt);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        product = productList.get(position);
//        viewHolder.productName.setText(product.getType());
        Glide.with(context).load(product.getThumb_url()).into(viewHolder.productIcon);

        return convertView;
    }

    class ViewHolder{
        ImageView productIcon;
        TextView productName;
        TextView productPrice;
    }
}
