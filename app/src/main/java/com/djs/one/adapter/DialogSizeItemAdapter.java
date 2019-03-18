package com.djs.one.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.djs.one.R;
import com.djs.one.util.DensityUtil;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/1/16<p>
 * <p>更改时间：2019/1/16<p>
 * <p>版本号：1<p>
 */
public class DialogSizeItemAdapter extends BaseAdapter {

    List<String> sizeItems;
    Context mContext;
    LayoutInflater mInflater;
    int mSelect = 0;

    public DialogSizeItemAdapter(Context context, List<String> items){
        mContext = context;
        sizeItems = items;
        mInflater = LayoutInflater.from(context);
    }

    public void changeSelected(int position){
        if (position != mSelect){
            mSelect = position;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return sizeItems.size();
    }

    @Override
    public Object getItem(int position) {
        return sizeItems.get(position);
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

            convertView = mInflater.inflate(R.layout.dialog_size_item_layout,null);
            viewHolder.sizeItem = convertView.findViewById(R.id.dialog_size_item);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.sizeItem.setText(sizeItems.get(position));

        if (mSelect == position){
            viewHolder.sizeItem.setBackgroundColor(mContext.getResources().getColor(R.color.home_glod_text_unselect));
            viewHolder.sizeItem.setTextColor(mContext.getResources().getColor(R.color.white));
        }else {
            viewHolder.sizeItem.setBackground(mContext.getDrawable(R.drawable.bg_amount_layout));
            viewHolder.sizeItem.setTextColor(mContext.getResources().getColor(R.color.home_glod_text_unselect));
        }

        viewHolder.sizeItem.setPadding(DensityUtil.dp2px(mContext,8),
                DensityUtil.dp2px(mContext,9),
                DensityUtil.dp2px(mContext,8),
                DensityUtil.dp2px(mContext,9));

        return convertView;
    }

    public class ViewHolder{
        TextView sizeItem;
    }
}
