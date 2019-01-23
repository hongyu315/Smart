package hongyu315.com.smart2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.bean.Product;

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
    List<Product> products;
    Product product;

    public ProductAdapter(Context context, List<Product> list) {
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
        ViewHolder viewHolder;
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

        product = products.get(position);

        Glide.with(mContext).load(product.getUrl()).into(viewHolder.productIcon);
//        viewHolder.productName.setText(product.get);

        return convertView;
    }

    public class ViewHolder {
        ImageView productIcon;
        TextView productTime;
        TextView productName;
        TextView productPrice;
    }
}
