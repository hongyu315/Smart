package hongyu315.com.smart2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.bean.Message;
import hongyu315.com.smart2.constant.Constant;

public class MessageAdapter extends BaseAdapter implements View.OnClickListener {

    List<Message> messageList;
    LayoutInflater mInflater;
    Context mContext;
    InnerItemOnclickListener mListener;

    public MessageAdapter(Context context,List<Message> messages){
        mInflater = LayoutInflater.from(context);
        messageList = messages;
        mContext = context;
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try{
            ViewHolder viewHolder;
            if (convertView == null){
                viewHolder = new ViewHolder();

                convertView = mInflater.inflate(R.layout.message_item_layout,null);

                viewHolder.msgIcon = (ImageView)convertView.findViewById(R.id.message_item_icon);
                viewHolder.msgStatus = (TextView) convertView.findViewById(R.id.order_status_text);
                viewHolder.msgName = (TextView) convertView.findViewById(R.id.order_name_text);
                viewHolder.msgNum = (TextView) convertView.findViewById(R.id.order_num_text);
                viewHolder.msgTime = (TextView) convertView.findViewById(R.id.message_item_time);

                viewHolder.bottomLayout = (RelativeLayout) convertView.findViewById(R.id.item_bottom_layout);
                viewHolder.deleteBtn = (Button) convertView.findViewById(R.id.delete_product);
                viewHolder.addShoppingCar = (Button) convertView.findViewById(R.id.add_to_shopping_car);

                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Message message = messageList.get(position);
            Glide.with(mContext).load(message.getIconUrl()).into(viewHolder.msgIcon);
            viewHolder.msgStatus.setText(message.getOrderStatus());
            viewHolder.msgName.setText(message.getOrderName());
            switch (message.getType()){
                case Constant.MESSAGE:
                    viewHolder.msgNum.setText("运单编号: " + message.getOrderNum());
                    break;
                case Constant.FAVORITE:
                    viewHolder.bottomLayout.setVisibility(View.VISIBLE);
                    viewHolder.msgNum.setText("尺寸: " + message.getOrderNum());
                    viewHolder.deleteBtn.setOnClickListener(this);
                    viewHolder.deleteBtn.setTag(position);
                    viewHolder.addShoppingCar.setTag(position);
                    viewHolder.addShoppingCar.setOnClickListener(this);
                    break;
                default:
                    break;
            }
            viewHolder.msgTime.setText(message.getOrderTime());

        }catch (Exception e){
        }


        return convertView;
    }

    public interface InnerItemOnclickListener{
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener){
        this.mListener = listener;
    }


    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }

    class ViewHolder{
        ImageView msgIcon;
        TextView msgStatus;
        TextView msgName;
        TextView msgNum;
        TextView msgTime;

        RelativeLayout bottomLayout;
        Button deleteBtn;
        Button addShoppingCar;
    }
}
