package hongyu315.com.smart2.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.adapter.DialogSizeItemAdapter;
import hongyu315.com.smart2.adapter.ShoppingCartAdapter;
import hongyu315.com.smart2.bean.GoodsInfo;
import hongyu315.com.smart2.util.DensityUtil;
import hongyu315.com.smart2.util.SysUtils;
import hongyu315.com.smart2.view.AmountView;

public class ShoppingFragment extends BaseFragment implements View.OnClickListener, ShoppingCartAdapter.onCheckboxClickListener, ShoppingCartAdapter.onAmountValueChangeListener {


    public LinearLayout check_LL;
    private int count = 0;
    private List<GoodsInfo> datas = new ArrayList();
    public View layout_empty_shopcart;
    protected Activity mActivity;
    public CheckBox mAllCheckBox;
    public TextView mClearShoppingCart;
    public TextView mGoPay;
    public RelativeLayout mLlCart;
    public LinearLayout mOrderInfo;
    private ShoppingCartAdapter mShoppingCartAdapter;
    public TextView mTotalPrice;
    private double price = 0.0D;

    private SwipeToLoadLayout swipeToLoadLayout;
    private ListView recyclerView;

    //合计 ： 960元
    private LinearLayout moneyLayout;

    public ShoppingFragment() {
    }

    public static ShoppingFragment getInstance() {
        return new ShoppingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shopping, container, false);
    }

    @Override
    public void onViewCreated(View paramView, Bundle paramBundle) {
        super.onViewCreated(paramView, paramBundle);
        if (this.mActivity == null) {
            return;
        }
        findViews(paramView);
        initData();
    }

    @Override
    protected void findViews(View paramView) {
        super.findViews(paramView);

        this.mClearShoppingCart = ((TextView)paramView.findViewById(R.id.clear_shopping_cart));
        this.mAllCheckBox = ((CheckBox)paramView.findViewById(R.id.all_checkBox));
        this.mClearShoppingCart.setOnClickListener(this);

        this.mAllCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
            {
                ShoppingFragment.this.checkAllGoodsInfo(paramAnonymousBoolean);
            }
        });

        swipeToLoadLayout = paramView.findViewById(R.id.swipeToLoadLayout);
        this.recyclerView = ((ListView)paramView.findViewById(R.id.swipe_target));
        this.mTotalPrice = ((TextView)paramView.findViewById(R.id.total_price));
        this.mGoPay = ((TextView)paramView.findViewById(R.id.go_pay));
        this.mGoPay.setOnClickListener(this);
        this.mOrderInfo = ((LinearLayout)paramView.findViewById(R.id.order_info));
        this.mLlCart = ((RelativeLayout)paramView.findViewById(R.id.ll_cart));
        this.check_LL = ((LinearLayout)paramView.findViewById(R.id.check_LL));
        this.layout_empty_shopcart = paramView.findViewById(R.id.layout_empty_shopcart);
        moneyLayout = paramView.findViewById(R.id.money_layout);

        this.mShoppingCartAdapter = new ShoppingCartAdapter(this.mActivity, datas, this, this);
        recyclerView.setAdapter(mShoppingCartAdapter);

        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                swipeToLoadLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeToLoadLayout.setLoadingMore(false);
                    }
                }, 3000);
            }
        });

        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeToLoadLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeToLoadLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        initDataTest();
    }

    private void initDataTest()
    {
        int i = 0;
        while (i < 4)
        {
            GoodsInfo localGoodsInfo = new GoodsInfo();
            localGoodsInfo.setId(i + "");
            localGoodsInfo.setChoosed(false);
            localGoodsInfo.setName("购物车艺术品" + i);
            localGoodsInfo.setImageUrl("https://img.zcool.cn/community/01757d5a6a7557a8012134664d0391.jpg@2o.jpg");
            localGoodsInfo.setDesc("具体描述");
            localGoodsInfo.setPrice(45.0D);
            localGoodsInfo.setPrime_price(456.0D);
            localGoodsInfo.setPostion(i);
            localGoodsInfo.setCount(100);
            localGoodsInfo.setSize("1");
            localGoodsInfo.setGoodsImg(1);
            datas.add(localGoodsInfo);
            i += 1;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            default:
                return;
            case R.id.clear_shopping_cart:
                onEditClick();
                return;
            case R.id.go_pay:
//                onGoPlayClick();
                showBottomDialog();
                return;
        }
    }

    int sizeListSelectedPosition = 0;
    int colorListSelectedPosition = 0;
    int amountInDialog = 1;
    private void showBottomDialog(){

        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(getActivity(),R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(getActivity(),R.layout.dialog_custom_layout,null);

        ListView sizeListView = view.findViewById(R.id.dialog_size_list);
        List<String> items = new ArrayList<>();
        items.add("M 四大行订单的等多久的解决");
        items.add("M 四大行订单的等多久的解决");
        items.add("M 四大行订单的等多久的解决");
        items.add("M 四大行订单的等多久的解决");
        final DialogSizeItemAdapter adapter = new DialogSizeItemAdapter(getActivity(),items);
        sizeListView.setAdapter(adapter);

        sizeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.changeSelected(position);
                sizeListSelectedPosition = position;
            }
        });
        sizeListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.changeSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        LinearLayout colorGridLayout = view.findViewById(R.id.dialog_color_list);
        List<String> colors = new ArrayList<>();
        colors.add("红色");
        colors.add("红色");
        colors.add("红色");
        colors.add("红色");
        colors.add("红色");
        colors.add("红色");

        final List<TextView> colorTextViews = new ArrayList<>(colors.size());

        for (int i = 0; i < colors.size(); i++){
            final TextView colorView = new TextView(getActivity());
            colorView.setBackground(getResources().getDrawable(R.drawable.bg_amount_layout));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,0,30,0);
            colorView.setPadding(30,4,30,4);
            colorView.setLayoutParams(lp);
            colorView.setText(colors.get(i));
            colorView.setTag(i);
            colorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //change other text status
                    for(TextView textView : colorTextViews){
                        textView.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
                        textView.setTextColor(getActivity().getResources().getColor(R.color.black));
                    }
                    colorListSelectedPosition = (int) v.getTag();
                    colorView.setBackgroundColor(getActivity().getResources().getColor(R.color.black));
                    colorView.setTextColor(getActivity().getResources().getColor(R.color.white));
                }
            });

            colorTextViews.add(colorView);
            colorGridLayout.addView(colorView);
        }


        AmountView amountView = view.findViewById(R.id.dialog_color_item_amount);
        amountView.setGoods_storage(Integer.MAX_VALUE);
        amountView.etAmount.setText("1");
        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Log.e("Smart", "onAmountChange: mount = "  + amount );
                amountInDialog = amount;
            }
        });

        Button confirmBtn = view.findViewById(R.id.shopping_dialog_comfirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("xxxx","size = " + sizeListSelectedPosition + "color" + colorListSelectedPosition + "amount" + amountInDialog  + "");
                dialog.dismiss();
            }
        });

        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,DensityUtil.dp2px(getActivity(), (float) (SysUtils.getScreenHeight(getActivity()) * 0.25)));
        dialog.show();

    }

    /**
     * 全选按钮点击
     * @param paramBoolean
     */
    private void checkAllGoodsInfo(boolean paramBoolean)
    {

//        price = 0.0;
        int i = 0;
        while (i < datas.size())
        {
            GoodsInfo goodsInfo = (GoodsInfo) datas.get(i);
//            price += goodsInfo.getPrice();
            goodsInfo.setChoosed(paramBoolean);
            i += 1;
        }

        if (paramBoolean){//全选

        }else {//全部取消
            price = 0;
        }

//        String status = mClearShoppingCart.getText().toString();
//        if (status.equalsIgnoreCase("编辑")){
//            mTotalPrice.setText("￥" + price);
//        }
        this.mShoppingCartAdapter.notifyDataSetChanged();
    }

    private void clearCart()
    {
        this.mClearShoppingCart.setVisibility(View.GONE);
        this.mLlCart.setVisibility(View.GONE);
    }

    private void delAllGoodsInfo()
    {
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        while (i < this.datas.size())
        {
            if (datas.get(i).isChoosed()){
                localArrayList.add(this.datas.get(i));
            }
            i += 1;
        }

        this.datas.removeAll(localArrayList);
        this.mShoppingCartAdapter.notifyDataSetChanged();

        if (datas.size() == 0){
            clearCart();
        }

    }

    public void onEditClick(){
        String status = mClearShoppingCart.getText().toString();
        if (status.equalsIgnoreCase("编辑")){
            mClearShoppingCart.setText("完成");
            mGoPay.setText("删除");
            moneyLayout.setVisibility(View.GONE);
        }else {
            mClearShoppingCart.setText("编辑");
            mGoPay.setText("去结算");
            moneyLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 去结算、删除按钮点击
     */
    public void onGoPlayClick(){
        String status = mClearShoppingCart.getText().toString();
        if (status.equalsIgnoreCase("完成")){
            delAllGoodsInfo();
        }else {

        }
    }

    @Override
    public void onCheckboxClick(View view, boolean isChecked) {
        int position = (int) view.getTag();
        datas.get(position).setChoosed(isChecked);

        GoodsInfo goodsInfo = datas.get(position);
        if (isChecked){
            price += goodsInfo.getPrice() * Integer.valueOf(goodsInfo.getSize());
        }else {
            if (price > 0){
                price -= goodsInfo.getPrice() * Integer.valueOf(goodsInfo.getSize());
            }
        }

        String status = mClearShoppingCart.getText().toString();
        if (status.equalsIgnoreCase("编辑")){
            mTotalPrice.setText("￥" + price);
        }
    }

    /**
     * 加 1 减 1 按钮监听
     * @param view
     * @param amount
     */
    @Override
    public void onAmountValueChangeListener(View view, int amount) {
        String status = mClearShoppingCart.getText().toString();

        if (status.equalsIgnoreCase("编辑")){
            int position = (int) view.getTag();
            datas.get(position).setSize(amount + "");

            price = 0.0;

            int i = 0;
            while (i < datas.size())
            {
                GoodsInfo goods = (GoodsInfo) datas.get(i);
                if (goods.isChoosed()){
                    price += goods.getPrice()  * Integer.valueOf(goods.getSize());
                }
                i += 1;
            }


            mTotalPrice.setText("￥" + price);
        }
    }
}
