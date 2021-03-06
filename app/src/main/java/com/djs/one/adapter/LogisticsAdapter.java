package com.djs.one.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.djs.one.R;
import com.djs.one.bean.LogisticsJson;
import com.djs.one.util.DensityUtil;

public class LogisticsAdapter extends RecyclerView.Adapter<LogisticsAdapter.LogisticsAdapterHolder> {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private LogisticsJson bean;

    public LogisticsAdapter(Context context, LogisticsJson bean) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.bean = bean;
    }

    @Override
    public int getItemCount() {
        return bean.getData().getLogistics().size();
    }

    @Override
    public LogisticsAdapter.LogisticsAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LogisticsAdapter.LogisticsAdapterHolder(mLayoutInflater.inflate(R.layout.logistics_recycle_item, parent, false));
    }

    @Override
    public void onBindViewHolder(LogisticsAdapter.LogisticsAdapterHolder holder, int position) {

        try {
            holder.tv_status.setText(bean.getData().getLogistics().get(position).getContext());//订单状态
            holder.tv_time.setText(bean.getData().getLogistics().get(position).getTime());//时间

            if (position == 0) {
                //红色的圆点
                holder.iv_status.setImageResource(R.mipmap.receive);
                RelativeLayout.LayoutParams pointParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(context, 20), DensityUtil.dp2px(context, 20));
                pointParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                holder.iv_status.setLayoutParams(pointParams);

                holder.tv_time.setTextColor(context.getResources().getColor(R.color.home_glod_text_unselect));
                holder.tv_status.setTextColor(context.getResources().getColor(R.color.home_glod_text_unselect));

                //灰色的竖线
                RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(context, 1), ViewGroup.LayoutParams.MATCH_PARENT);
                lineParams.addRule(RelativeLayout.BELOW, R.id.iv_status);//让直线置于圆点下面
                lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                holder.iv_line.setLayoutParams(lineParams);

            } else {
                //物流状态：0在途中、1已揽收、2疑难、3已签收、4退签、5派送中、6退回、7转单
//                holder.iv_status.setBackgroundResource(R.mipmap.ic_logistics_bottom);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DensityUtil.dp2px(context, 20), DensityUtil.dp2px(context, 20));

                switch (bean.getData().getLogistics().get(position).getStatus()){
                    case 0:
                        holder.iv_status.setImageResource(R.mipmap.default_l);
                        lp = new RelativeLayout.LayoutParams(DensityUtil.dp2px(context, 10), DensityUtil.dp2px(context, 10));
                        break;
                    case 1:
                        holder.iv_status.setImageResource(R.mipmap.go_l);
                        break;
                    case 2:

                        break;
                    case 3:
                        holder.iv_status.setImageResource(R.mipmap.qianshou);
                        break;
                    case 4:

                        break;
                    case 5:
                        holder.iv_status.setImageResource(R.mipmap.send_l);
                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:
//                        holder.iv_status.setImageResource(R.mipmap.receive);
                        break;
                    default:
                        holder.iv_status.setImageResource(R.mipmap.default_l);
                        lp = new RelativeLayout.LayoutParams(DensityUtil.dp2px(context, 10), DensityUtil.dp2px(context, 10));
                        break;
                }

                lp.addRule(RelativeLayout.CENTER_IN_PARENT);

                holder.iv_status.setLayoutParams(lp);

                holder.tv_time.setTextColor(context.getResources().getColor(R.color.order_item_time));
                holder.tv_status.setTextColor(context.getResources().getColor(R.color.order_item_time));

                //灰色的竖线
                RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(context, 1), ViewGroup.LayoutParams.MATCH_PARENT);
//                lineParams.addRule(RelativeLayout.BELOW, R.id.iv_status);//让直线置于圆点下面
                lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                holder.iv_line.setLayoutParams(lineParams);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class LogisticsAdapterHolder extends RecyclerView.ViewHolder {

        ImageView iv_status;
        TextView tv_status;
        TextView tv_time;
        ImageView iv_line;

        LogisticsAdapterHolder(View view) {
            super(view);
            iv_line = view.findViewById(R.id.iv_line);
            iv_status = view.findViewById(R.id.iv_status);
            tv_status = view.findViewById(R.id.tv_status);
            tv_time = view.findViewById(R.id.tv_time);

        }
    }
}
