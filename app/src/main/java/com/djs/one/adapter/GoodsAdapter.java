package com.djs.one.adapter;

import android.app.Activity;

import com.superrecycleview.superlibrary.adapter.BaseViewHolder;
import com.superrecycleview.superlibrary.adapter.SuperBaseAdapter;

import java.util.List;

import com.djs.one.bean.Product;

public class GoodsAdapter
        extends SuperBaseAdapter<Product>
{
    private boolean isLove = false;
    private Activity mContext;

    public GoodsAdapter(Activity paramActivity)
    {
        super(paramActivity);
        this.mContext = paramActivity;
    }

    public GoodsAdapter(Activity paramActivity, List<Product> paramList)
    {
        super(paramActivity, paramList);
        this.mContext = paramActivity;
    }

    protected void convert(final BaseViewHolder paramBaseViewHolder, Product paramProductsBean, int paramInt)
    {
//        if (paramProductsBean.isLove()) {
//            paramBaseViewHolder.setImageResource(2131558680, 2130903055);
//        }
//        for (this.isLove = true;; this.isLove = false)
//        {
//            paramBaseViewHolder.setOnClickListener(2131558680, new View.OnClickListener()
//            {
//                public void onClick(View paramAnonymousView)
//                {
//                    if (GoodsAdapter.this.isLove)
//                    {
//                        paramBaseViewHolder.setImageResource(2131558680, 2130903054);
//                        GoodsAdapter.access$002(GoodsAdapter.this, false);
//                        ToastUtil.showToast(GoodsAdapter.this.mContext, "������������");
//                        return;
//                    }
//                    paramBaseViewHolder.setImageResource(2131558680, 2130903055);
//                    ToastUtil.showToast(GoodsAdapter.this.mContext, "������������");
//                    GoodsAdapter.access$002(GoodsAdapter.this, true);
//                }
//            });
//            ((TextView)paramBaseViewHolder.getView(2131558683)).getPaint().setFlags(17);
//            paramBaseViewHolder.setOnClickListener(2131558685, new View.OnClickListener()
//            {
//                public void onClick(View paramAnonymousView)
//                {
//                    ShoppingCartDetailActivity.onGoodsCheckDialog(true);
//                }
//            });
//            paramBaseViewHolder.setOnClickListener(2131558684, new View.OnClickListener()
//            {
//                public void onClick(View paramAnonymousView)
//                {
//                    ShoppingCartDetailActivity.onGoodsCheckDialog(true);
//                }
//            });
//            paramBaseViewHolder.setOnClickListener(2131558686, new View.OnClickListener()
//            {
//                public void onClick(View paramAnonymousView)
//                {
//                    SysUtils.startActivity(GoodsAdapter.this.mContext, ShoppingDetailActivity.class);
//                }
//            });
//            paramBaseViewHolder.setOnClickListener(2131558687, new View.OnClickListener()
//            {
//                public void onClick(View paramAnonymousView)
//                {
//                    SysUtils.startActivity(GoodsAdapter.this.mContext, ShoppingDetailActivity.class);
//                }
//            });
//            paramBaseViewHolder.setOnClickListener(2131558689, new View.OnClickListener()
//            {
//                public void onClick(View paramAnonymousView) {}
//            });
//            paramBaseViewHolder.setOnClickListener(2131558688, new View.OnClickListener()
//            {
//                public void onClick(View paramAnonymousView) {}
//            });
//            return;
//            paramBaseViewHolder.setImageResource(2131558680, 2130903054);
//        }
    }

    protected int getItemViewLayoutId(int paramInt, Product paramProductsBean)
    {
        return 2130968641;
    }
}
