package com.djs.one.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djs.one.R;
import com.djs.one.bean.MyOrdersBean;
import com.djs.one.constant.Constant;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends BaseAdapter implements View.OnClickListener {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<MyOrdersBean.DataBean.ListBean> orderList = new ArrayList<>();
    private MyOrdersBean.DataBean.ListBean order = new MyOrdersBean.DataBean.ListBean();
    private onOrderItemBtnClickListener mListener;

    public OrderAdapter(Context context, List<MyOrdersBean.DataBean.ListBean> orders){
        if (context == null) return;
        mInflater = LayoutInflater.from(context);
        mContext = context;
        orderList = orders;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
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

            convertView = mInflater.inflate(R.layout.order_item_layout,null);
            viewHolder.orderItemLayout = convertView.findViewById(R.id.order_item_layout);

            viewHolder.orderTime = convertView.findViewById(R.id.order_time_txt);
            viewHolder.orderStatus = convertView.findViewById(R.id.order_status_text);

            viewHolder.productIcon = convertView.findViewById(R.id.message_item_icon);
            viewHolder.productName = convertView.findViewById(R.id.product_name);
            viewHolder.productType = convertView.findViewById(R.id.order_name_text);
            viewHolder.productSize = convertView.findViewById(R.id.order_num_text);
            viewHolder.productPrice = convertView.findViewById(R.id.product_price);
            viewHolder.productNum = convertView.findViewById(R.id.product_num);

            viewHolder.totalProduct = convertView.findViewById(R.id.total_product);

            viewHolder.leftBtn = convertView.findViewById(R.id.bottom_left);
            viewHolder.rightBtn = convertView.findViewById(R.id.bottom_right);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        order = orderList.get(position);
        viewHolder.orderTime.setText(order.getCreated_at() + "");
        viewHolder.orderStatus.setText(getStatusString(order.getStatus()));

        if (order.getStatus() == Constant.WAIT4PAY || order.getStatus() == 0){
            viewHolder.orderStatus.setTextColor(mContext.getResources().getColor(R.color.red));
        }

        Glide.with(mContext).load(order.getItem_info().getThumb_url()).into(viewHolder.productIcon);
        viewHolder.productName.setText(order.getItem_info().getItem_title()+ "");
        viewHolder.productType.setText(order.getItem_info().getSku_title()+ "");
//        viewHolder.productSize.setText(order.getProduct_size());
        viewHolder.productPrice.setText("￥" + order.getPay_amount());
        viewHolder.productNum.setText("x" + order.getTotal_item());

        viewHolder.totalProduct.setText("共计" + order.getTotal_item() + "件商品，共计￥" + order.getPay_amount());

        switch (order.getStatus()){
            case Constant.COMPLETED:
                viewHolder.leftBtn.setVisibility(View.GONE);
                viewHolder.rightBtn.setText("查看详情");
                break;
            case 0:
            case Constant.WAIT4PAY:
                viewHolder.leftBtn.setVisibility(View.GONE);
                viewHolder.rightBtn.setText("去支付");
                break;
            case Constant.WAIT4DELIVER:
                viewHolder.leftBtn.setVisibility(View.GONE);
                viewHolder.rightBtn.setText("查看详情");
                break;
            case Constant.WAIT4TAKEDELIVER:
                viewHolder.leftBtn.setVisibility(View.VISIBLE);
                viewHolder.leftBtn.setText("查看物流");
                viewHolder.rightBtn.setText("确认收货");
                break;
            default:
                break;
        }

        viewHolder.orderItemLayout.setTag(position);
        viewHolder.leftBtn.setTag(position);
        viewHolder.rightBtn.setTag(position);

        viewHolder.orderItemLayout.setOnClickListener(this);
        viewHolder.leftBtn.setOnClickListener(this);
        viewHolder.rightBtn.setOnClickListener(this);

        return convertView;
    }

    private String getStatusString(int status){
        String statusString = "";
        switch (status){
            case Constant.COMPLETED:
                statusString = "已完成";
                break;
            case Constant.WAIT4DELIVER:
                statusString = "待发货";
                break;
            case Constant.WAIT4PAY:
                statusString = "待付款";
                break;
            case Constant.WAIT4TAKEDELIVER:
                statusString = "待收货";
                break;
            default:
                statusString = "待付款";
                break;
        }
        return statusString;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.order_item_layout:
                mListener.onItemLayoutClick(v);
                break;
            case R.id.bottom_left:
                mListener.onLeftBtnClick(v);
                break;
            case R.id.bottom_right:
                mListener.onRightBtnClick(v);
                break;
            default:
                break;
        }
    }

    class ViewHolder{
        LinearLayout orderItemLayout;

        TextView orderTime;
        TextView orderStatus;

        ImageView productIcon;
        TextView productName;
        TextView productType;
        TextView productSize;
        TextView productPrice;
        TextView productNum;

        TextView totalProduct;

        Button leftBtn;
        Button rightBtn;
    }

    public interface onOrderItemBtnClickListener{
        void onItemLayoutClick(View v);
        void onLeftBtnClick(View v);
        void onRightBtnClick(View v);
    }

    public void setOnOrderItemBtnClickListener(onOrderItemBtnClickListener listener){
        mListener = listener;
    }

}
