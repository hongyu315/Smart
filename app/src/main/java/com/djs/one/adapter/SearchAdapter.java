package com.djs.one.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.djs.one.R;
import com.djs.one.bean.SearchContent;

public class SearchAdapter extends BaseAdapter {

    private List<SearchContent> contentList;
    private LayoutInflater layoutInflater;

    public SearchAdapter(Context context, List<SearchContent> list){
        this.contentList = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return contentList.size();
    }

    @Override
    public Object getItem(int position) {
        return contentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.search_item_content,null);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.search_item_content);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        SearchContent content = contentList.get(position);
        if (content != null){
            holder.textView.setText(content.getsContent());
        }
        return convertView;
    }

    class ViewHolder{
        TextView textView;
    }
}
