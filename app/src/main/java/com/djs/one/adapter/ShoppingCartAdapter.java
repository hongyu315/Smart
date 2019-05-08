package com.djs.one.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djs.one.R;
import com.djs.one.bean.ShoppingCarItems;
import com.djs.one.view.AmountView;

import java.util.List;

public class ShoppingCartAdapter
        extends BaseAdapter
{
    public Context mContext;
    private List<ShoppingCarItems.DataBean.ListBean> goodsInfoList;
    private LayoutInflater mInflater;
    private ShoppingCarItems.DataBean.ListBean goodsInfo;

    private onCheckboxClickListener mListener;
    private onAmountValueChangeListener onAmountValueChangeListener;

    public ShoppingCartAdapter(Context paramContext, List<ShoppingCarItems.DataBean.ListBean> paramList,
                               onCheckboxClickListener listener, onAmountValueChangeListener amountValueChangeListener)
    {
        this.mContext = paramContext;
        goodsInfoList = paramList;
        mInflater = LayoutInflater.from(paramContext);
        mListener = listener;
        onAmountValueChangeListener = amountValueChangeListener;
    }

    @Override
    public int getCount() {
        return goodsInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();

            convertView = mInflater.inflate(R.layout.shoppingcart_product_item,null);

            viewHolder.checkBox = convertView.findViewById(R.id.single_checkBox);
            viewHolder.icon = convertView.findViewById(R.id.goods_image);
            viewHolder.name = convertView.findViewById(R.id.goods_name);
            viewHolder.goodContent = convertView.findViewById(R.id.goods_content);
            viewHolder.goodSize = convertView.findViewById(R.id.goods_size);
            viewHolder.goodPrice = convertView.findViewById(R.id.goods_price);
            viewHolder.amountView = convertView.findViewById(R.id.amount_view);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        try{
            goodsInfo = goodsInfoList.get(position);
            String colorStr = "";
            String sizeStr = "";
            List<ShoppingCarItems.DataBean.ListBean.SpecsBean> specs = goodsInfo.getSpecs();
            for (ShoppingCarItems.DataBean.ListBean.SpecsBean sp : specs) {
                if (sp.getAttr_name().equalsIgnoreCase("尺寸")) {
                    sizeStr = sp.getValue();
                }else if (sp.getAttr_name().equalsIgnoreCase("颜色")){
                    colorStr = sp.getValue();
                }
            }

            viewHolder.checkBox.setTag(position);
            Glide.with(mContext).load(goodsInfo.getThumb_url()).into(viewHolder.icon);
            viewHolder.name.setText(goodsInfo.getTitle());
            viewHolder.goodPrice.setText("￥"  + goodsInfo.getMarket_price());
            viewHolder.goodContent.setText(colorStr);
            viewHolder.goodSize.setText(sizeStr);
            viewHolder.checkBox.setChecked(goodsInfo.isChoosed());

            viewHolder.amountView.setGoods_storage(Integer.MAX_VALUE);
            int quantity = goodsInfo.getQuantity();
            if (quantity < 1) quantity = 1;
            viewHolder.amountView.etAmount.setText(quantity + "");
            viewHolder.amountView.setTag(position);
            viewHolder.amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                    Log.e("Smart", "onAmountChange: mount = "  + amount );
                    onAmountValueChangeListener.onAmountValueChangeListener(view,amount);
                }
            });
            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mListener.onCheckboxClick(buttonView,isChecked);
                }
            });
        }catch (Exception e){
        }

        return convertView;
    }

    public class ViewHolder{
        CheckBox checkBox;
        ImageView icon;
        TextView name;
        TextView goodContent;
        TextView goodSize;
        TextView goodPrice;
        AmountView amountView;
    }

    public interface onCheckboxClickListener{
        void onCheckboxClick(View view, boolean isChecked);
    }

    public interface onAmountValueChangeListener{
        void onAmountValueChangeListener(View view , int amount);
    }
}
