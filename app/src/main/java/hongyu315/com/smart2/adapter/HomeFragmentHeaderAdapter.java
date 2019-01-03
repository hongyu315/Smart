package hongyu315.com.smart2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.superrecycleview.superlibrary.adapter.BaseViewHolder;
import com.superrecycleview.superlibrary.adapter.SuperBaseAdapter;

import java.util.List;

import hongyu315.com.smart2.R;


public class HomeFragmentHeaderAdapter extends SuperBaseAdapter {
    public HomeFragmentHeaderAdapter(Context context, List data) {
        super(context,data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Object item, int position) {
        holder.setText(R.id.home_header_txt,"Text");
    }

    @Override
    protected int getItemViewLayoutId(int position, Object item) {
        return R.layout.fragment_home_header;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
}
