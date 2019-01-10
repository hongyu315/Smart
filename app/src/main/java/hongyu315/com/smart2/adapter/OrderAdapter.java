package hongyu315.com.smart2.adapter;

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

import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.bean.Order;
import hongyu315.com.smart2.constant.Constant;

public class OrderAdapter extends BaseAdapter implements View.OnClickListener {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<Order> orderList;
    private Order order;
    private onOrderItemBtnClickListener mListener;

    public OrderAdapter(Context context, List<Order> orders){
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();

            convertView = mInflater.inflate(R.layout.order_item_layout,null);
            viewHolder.orderItemLayout = (LinearLayout) convertView.findViewById(R.id.order_item_layout);

            viewHolder.orderTime = (TextView) convertView.findViewById(R.id.order_time_txt);
            viewHolder.orderStatus = (TextView) convertView.findViewById(R.id.order_status_text);

            viewHolder.productIcon = (ImageView) convertView.findViewById(R.id.message_item_icon);
            viewHolder.productName = (TextView) convertView.findViewById(R.id.product_name);
            viewHolder.productType = (TextView) convertView.findViewById(R.id.order_name_text);
            viewHolder.productSize = (TextView) convertView.findViewById(R.id.order_num_text);
            viewHolder.productPrice = (TextView) convertView.findViewById(R.id.product_price);
            viewHolder.productNum = (TextView) convertView.findViewById(R.id.product_num);

            viewHolder.totalProduct = (TextView) convertView.findViewById(R.id.total_product);

            viewHolder.leftBtn = (Button) convertView.findViewById(R.id.bottom_left);
            viewHolder.rightBtn = (Button) convertView.findViewById(R.id.bottom_right);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        order = orderList.get(position);
        viewHolder.orderTime.setText(order.getOrder_time());
        viewHolder.orderStatus.setText(order.getOrder_status());

        Glide.with(mContext).load(order.getProduct_icon()).into(viewHolder.productIcon);
        viewHolder.productName.setText(order.getProduct_name());
        viewHolder.productType.setText(order.getProduct_type());
        viewHolder.productSize.setText(order.getProduct_size());
        viewHolder.productPrice.setText("￥" + order.getProduct_price());
        viewHolder.productNum.setText("x" + order.getProduct_num());

        viewHolder.totalProduct.setText("共计" + order.getProduct_num() + "件商品，共计￥" + order.getProduct_price());

        switch (order.order_kind){
            case Constant.COMPLETED:
                viewHolder.leftBtn.setVisibility(View.GONE);
                viewHolder.rightBtn.setText("查看详情");
                break;
            case Constant.WAIT4PAY:
                viewHolder.leftBtn.setVisibility(View.GONE);
                viewHolder.rightBtn.setText("去支付");
                break;
            case Constant.WAIT4DELIVER:
                viewHolder.leftBtn.setVisibility(View.GONE);
                viewHolder.rightBtn.setText("查看详情");
                break;
            case Constant.WAIT4TAKEDELIVER:
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
