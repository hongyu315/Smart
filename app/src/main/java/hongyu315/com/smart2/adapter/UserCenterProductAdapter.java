package hongyu315.com.smart2.adapter;

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

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.bean.Product;


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

            viewHolder.productIcon = (ImageView)convertView.findViewById(R.id.product_img);
            viewHolder.productName = (TextView) convertView.findViewById(R.id.product_txt);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        product = productList.get(position);
        viewHolder.productName.setText(product.getType());
        Glide.with(context).load(product.getUrl()).into((ImageView)viewHolder.productIcon);

        return convertView;
    }

    class ViewHolder{
        ImageView productIcon;
        TextView productName;
        TextView productPrice;
    }
}
