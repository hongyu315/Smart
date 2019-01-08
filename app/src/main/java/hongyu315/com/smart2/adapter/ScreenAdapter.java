package hongyu315.com.smart2.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.superrecycleview.superlibrary.adapter.BaseViewHolder;
import com.superrecycleview.superlibrary.adapter.SuperBaseAdapter;

import java.util.ArrayList;
import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.bean.Product;


public class ScreenAdapter extends SuperBaseAdapter<Product> {

    private List<Product> productList = new ArrayList<>();
    private Context context;
    private Product product;

    public ScreenAdapter(Context mContext){
        super(mContext);
        this.context = mContext;
    }

    public ScreenAdapter(Context mContenxt, List<Product> mDatas){
        super(mContenxt,mDatas);
        this.context = mContenxt;
        productList = mDatas;
    }

    @Override
    protected void convert(BaseViewHolder holder, Product item, int position) {
        product = productList.get(position);
        holder.setText(R.id.product_txt,product.getType());
        Glide.with(context).load(product.getUrl()).into((ImageView)holder.getView(R.id.product_img));
    }

    @Override
    protected int getItemViewLayoutId(int position, Product item) {
        return R.layout.screen_adapter_layout;
    }

//    @Override
//    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
//        if (manager instanceof GridLayoutManager){
//            final GridLayoutManager gridLayoutManager = ((GridLayoutManager) manager);
//            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    Log.e("Smart", "getSpanSize: header counter");
//                    switch (getItemViewType(position)) {
//                        case VIEW_TYPE.HEADER://header
//                            Log.e("Smart", "getSpanSize: header" );
//                            return gridLayoutManager.getSpanCount();
//                        case VIEW_TYPE.FOOTER://footer
//                            Log.e("Smart", "getSpanSize: footer" );
//                            return gridLayoutManager.getSpanCount();
//                        default:
//                            Log.e("Smart", "getSpanSize: cell" );
//                            return 1;
//                    }
//                }
//            });
//        }
//    }

}
