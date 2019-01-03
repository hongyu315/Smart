package hongyu315.com.smart2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.bean.Product;


public class ScreenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Product> productList = new ArrayList<>();
    private Context context;
    private Product product;

    public ScreenAdapter(Context mContenxt, List<Product> mDatas){
        context = mContenxt;
        productList = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.screen_adapter_layout,parent,false);
        return new ViewHolde(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolde){
            product = productList.get(position);
            ((ViewHolde) holder).textView.setText(product.getType());
            Glide.with(context).load(product.getUrl()).into(((ViewHolde) holder).productIcon);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ViewHolde extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView productIcon;
        public ViewHolde(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.product_txt);
            productIcon = itemView.findViewById(R.id.product_img);
        }
    }


}
