package com.djs.one.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djs.one.R;
import com.djs.one.bean.Product;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/1/13<p>
 * <p>更改时间：2019/1/13<p>
 * <p>版本号：1<p>
 */
public class ProductAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<Product> mProducts;
    Product product;

    public ProductAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<Product> products) {
        mProducts = products;
    }

    @Override
    public int getCount() {
        return mProducts != null ? mProducts.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = mLayoutInflater.inflate(R.layout.product_item_layout,null);

            viewHolder.productIcon = convertView.findViewById(R.id.product_item_icon);
            viewHolder.productTime = convertView.findViewById(R.id.product_item_time);
            viewHolder.productName = convertView.findViewById(R.id.product_item_title);
            viewHolder.productPrice = convertView.findViewById(R.id.product_item_price);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        try{
            product = mProducts.get(position);

            if (product.getIs_on_sale() == 1){
                Glide.with(mContext).load(product.getThumb_url()).into(viewHolder.productIcon);
                viewHolder.productTime.setText("上架时间：" + product.getCreated_at());
                viewHolder.productName.setText(product.getTitle() + "");
                viewHolder.productPrice.setText("￥" + ( product.getIs_promote() == 1 ? product.getPromote_price() : product.getMarket_price()));
            }
        }catch (Exception e){
        }

        return convertView;
    }

    public class ViewHolder {
        ImageView productIcon;
        TextView productTime;
        TextView productName;
        TextView productPrice;
    }
}
