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
public class FavoriteAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<Product> products;
    Product product;

    public FavoriteAdapter(Context context, List<Product> list) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        products = list;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
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

            convertView = mLayoutInflater.inflate(R.layout.favorite_item_layout,null);

            viewHolder.productIcon = convertView.findViewById(R.id.message_item_icon);
            viewHolder.productTime = convertView.findViewById(R.id.order_name_text);
            viewHolder.productName = convertView.findViewById(R.id.product_name);
            viewHolder.productPrice = convertView.findViewById(R.id.order_num_text);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        try{
            product = products.get(position);

//            if (product.getIs_on_sale() == 1){
                Glide.with(mContext).load(product.getThumb_url()).into(viewHolder.productIcon);
                viewHolder.productTime.setText("");//"上架时间：" + product.getCreated_at());
                viewHolder.productName.setText(product.getTitle() + "");
                viewHolder.productPrice.setText("");//"￥" + ( product.getIs_promote() == 1 ? product.getPromote_price() : product.getMarket_price()));
//            }
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
