package com.djs.one.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.djs.one.R;
import com.djs.one.activity.ProductDetailActivity;
import com.djs.one.bean.Product;
import com.djs.one.bean.ProductCategoryBean;
import com.djs.one.bean.ProductDetailBean;
import com.djs.one.constant.Constant;
import com.djs.one.view.AmountView;

import java.util.List;

public class ProductDetailDialogAdapter extends BaseAdapter {

    private LayoutInflater mInflate;
    private Context mContext;
    private List<ProductDetailBean.DataBean.SkuBeanX.SkuBean> skuBeans;
    ProductDetailBean.DataBean.SkuBeanX.SkuBean bean;

    public ProductDetailDialogAdapter(Context ctx){
        mContext = ctx;
        mInflate = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        skuBeans = list;
    }

    public void setData(List<ProductDetailBean.DataBean.SkuBeanX.SkuBean> productCategoryBean) {
        if (productCategoryBean != null) {
            skuBeans = productCategoryBean;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return skuBeans == null ? 0 : skuBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return skuBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final  int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;

        if(convertView==null){
            holder = new ViewHolder();
            convertView = mInflate.inflate(R.layout.product_detail_dialog_item, null);

            holder.mSize = convertView.findViewById(R.id.product_detail_dialog_size);
            holder.mPrice = convertView.findViewById(R.id.product_detail_dialog_price);
            holder.mTime = convertView.findViewById(R.id.product_detail_dialog_time);
            holder.mLeft = convertView.findViewById(R.id.product_detail_dialog_left);
            holder.mAccount = convertView.findViewById(R.id.product_detail_dialog_account);

            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        bean = skuBeans.get(i);

        holder.mSize.setText("" + bean.getSize());
        holder.mPrice.setText("￥" + bean.getMarket_price());
        holder.mTime.setText("发货周期:" + bean.getDelivery_cycle());
        holder.mLeft.setText("" + bean.getStock());

        holder.mAccount.setGoods_storage(bean.getStock());
        holder.mAccount.etAmount.setText(bean.getAmount() + "");
        holder.mAccount.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                skuBeans.get(i).setAmount(amount);
                Intent intent = new Intent();
                intent.setAction("com.dsj.one.product_dialog");
                mContext.sendBroadcast(intent);
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        private TextView mSize ;
        private TextView mPrice;
        private TextView mTime;

        private TextView mLeft;
        private AmountView mAccount;
    }

}
