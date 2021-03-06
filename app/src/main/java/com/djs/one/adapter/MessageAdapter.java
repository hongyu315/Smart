package com.djs.one.adapter;

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

import com.djs.one.R;
import com.djs.one.bean.Message;
import com.djs.one.constant.Constant;

public class MessageAdapter extends BaseAdapter implements View.OnClickListener {

    List<Message> messageList;
    LayoutInflater mInflater;
    Context mContext;
    InnerItemOnclickListener mListener;

    public MessageAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void setData(List<Message> messages) {
        messageList = messages;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messageList == null ? 0 : messageList.size();
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

        try {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();

                convertView = mInflater.inflate(R.layout.message_item_layout, null);

                viewHolder.msgIcon = convertView.findViewById(R.id.message_item_icon);
                viewHolder.msgStatus = convertView.findViewById(R.id.order_status_text);
                viewHolder.msgName = convertView.findViewById(R.id.order_name_text);
                viewHolder.msgNum = convertView.findViewById(R.id.order_num_text);
                viewHolder.msgTime = convertView.findViewById(R.id.message_item_time);

                viewHolder.bottomLayout = convertView.findViewById(R.id.item_bottom_layout);
                viewHolder.deleteBtn = convertView.findViewById(R.id.delete_product);
                viewHolder.addShoppingCar = convertView.findViewById(R.id.add_to_shopping_car);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Message message = messageList.get(position);
            Message.BusinessExtra extra = message.getBusiness_extra();
            Glide.with(mContext).load(extra.getThumb_url()).into(viewHolder.msgIcon);
            viewHolder.msgStatus.setText(message.getTitle());
            viewHolder.msgName.setText(extra.getItem_title());
            switch (message.getType()) {
                case Constant.MESSAGE:
                    viewHolder.msgNum.setText("运单编号: " + extra.getWaybill_no());
                    break;
                case Constant.FAVORITE:
                    viewHolder.bottomLayout.setVisibility(View.VISIBLE);
                    viewHolder.msgNum.setText("运单编号: " + extra.getWaybill_no());
                    viewHolder.deleteBtn.setOnClickListener(this);
                    viewHolder.deleteBtn.setTag(position);
                    viewHolder.addShoppingCar.setTag(position);
                    viewHolder.addShoppingCar.setOnClickListener(this);
                    break;
                default:
                    break;
            }
            viewHolder.msgTime.setText(message.getCreated_at());

        } catch (Exception e) {
        }


        return convertView;
    }

    public interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener) {
        this.mListener = listener;
    }


    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }

    class ViewHolder {
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
