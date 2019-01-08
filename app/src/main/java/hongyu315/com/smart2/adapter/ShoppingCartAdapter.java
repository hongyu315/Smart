package hongyu315.com.smart2.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.superrecycleview.superlibrary.adapter.BaseViewHolder;
import com.superrecycleview.superlibrary.adapter.SuperBaseAdapter;

import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.bean.GoodsInfo;

public class ShoppingCartAdapter
        extends SuperBaseAdapter<GoodsInfo>
{
    public Context mContext;
    private onCheckClickListener onCheckClickListener;
    private onDelClickListener onDelClickListener;

    public ShoppingCartAdapter(Context paramContext)
    {
        super(paramContext);
        this.mContext = paramContext;
    }

    public ShoppingCartAdapter(Context paramContext, List<GoodsInfo> paramList)
    {
        super(paramContext, paramList);
        this.mContext = paramContext;
    }

    protected void convert(BaseViewHolder paramBaseViewHolder, GoodsInfo paramGoodsInfo, final int paramInt)
    {
        paramBaseViewHolder.setText(R.id.goods_name, paramGoodsInfo.getName());
        paramBaseViewHolder.setText(R.id.goods_size, paramGoodsInfo.getSize());
        paramBaseViewHolder.setText(R.id.goods_price, paramGoodsInfo.getPrice() + "");
        paramBaseViewHolder.setText(R.id.goods_prime_price, paramGoodsInfo.getCount() + "");
        paramBaseViewHolder.setOnClickListener(R.id.goods_del, new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                ShoppingCartAdapter.this.onDelClickListener.onDelClickListener(paramInt);
            }
        });
        Glide.with(this.mContext).load(paramGoodsInfo.getImageUrl()).into(((ImageView) paramBaseViewHolder.getView(R.id.goods_image)));
//        GlideImageloader.GlidePictureTailor(this.mContext, paramGoodsInfo.getImageUrl(), (ImageView)paramBaseViewHolder.getView(2131558702), 2, 0);
//        paramBaseViewHolder.setIsChecked(2131558701, paramGoodsInfo.isChoosed());
//        paramBaseViewHolder.setOnCheckedChangeListener(2131558701, new CompoundButton.OnCheckedChangeListener()
//        {
//            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
//            {
//                ShoppingCartAdapter.this.onCheckClickListener.onCheckClickListener(paramInt, paramAnonymousBoolean);
//            }
//        });
    }

    protected int getItemViewLayoutId(int paramInt, GoodsInfo paramGoodsInfo)
    {
        return R.layout.shoppingcart_product_item;
    }

    public void setOnCheckClickListener(onCheckClickListener paramonCheckClickListener)
    {
        this.onCheckClickListener = paramonCheckClickListener;
    }

    public void setOnDelClickListener(onDelClickListener paramonDelClickListener)
    {
        this.onDelClickListener = paramonDelClickListener;
    }

    public static abstract interface onCheckClickListener
    {
        public abstract void onCheckClickListener(int paramInt, boolean paramBoolean);
    }

    public static abstract interface onDelClickListener
    {
        public abstract void onDelClickListener(int paramInt);
    }
}
