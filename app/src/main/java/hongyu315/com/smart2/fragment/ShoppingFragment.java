package hongyu315.com.smart2.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superrecycleview.superlibrary.adapter.SuperBaseAdapter;
import com.superrecycleview.superlibrary.recycleview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import hongyu315.com.smart2.MainActivity;
import hongyu315.com.smart2.R;
import hongyu315.com.smart2.activity.ShoppingDetailActivity;
import hongyu315.com.smart2.adapter.ShoppingCartAdapter;
import hongyu315.com.smart2.bean.GoodsInfo;
import hongyu315.com.smart2.util.SysUtils;

public class ShoppingFragment extends BaseFragment implements View.OnClickListener,
        SuperRecyclerView.LoadingListener,
        ShoppingCartAdapter.onCheckClickListener,
        ShoppingCartAdapter.onDelClickListener,
        SuperBaseAdapter.OnItemClickListener {


    private static final int TYPE_PULLREFRESH = 0;
    private static final int TYPE_UPLOADREFRESH = 1;
    protected String TAG = "";
    public LinearLayout check_LL;
    private int count = 0;
    private List<GoodsInfo> datas = new ArrayList();
    private RelativeLayout errorWeb;
    private Handler handler = new Handler()
    {
        public void handleMessage(Message paramAnonymousMessage)
        {
            switch (paramAnonymousMessage.what)
            {
                default:
                    ShoppingFragment.this.updateloadData(0);
                    return;
            }
        }
    };
    public View layout_empty_shopcart;
    protected Activity mActivity;
    public CheckBox mAllCheckBox;
    public TextView mClearShoppingCart;
    public TextView mCollectGoods;
    public TextView mDelGoods;
    public TextView mGoPay;
    public RelativeLayout mLlCart;
    public LinearLayout mOrderInfo;
    public TextView mShareGoods;
    public LinearLayout mShareInfo;
    private ShoppingCartAdapter mShoppingCartAdapter;
    public TextView mTotalPrice;
    private int pageNo1 = 1;
    private double price = 0.0D;
    private SuperRecyclerView recyclerView;
    public View rootView;

    private void AddcheckAllGoodsInfoPrice(boolean paramBoolean) {}

    private void checkAllGoodsInfo(boolean paramBoolean)
    {
        List localList = this.datas;
        int i = 0;
        while (i < localList.size())
        {
            ((GoodsInfo)localList.get(i)).setChoosed(paramBoolean);
            i += 1;
        }
        this.datas = localList;
        mShoppingCartAdapter.mData = this.datas;
//        this.mShoppingCartAdapter.setDatas(this.datas);
        this.recyclerView.completeRefresh();
        this.mShoppingCartAdapter.notifyDataSetChanged();
    }

    private void clearCart()
    {
        this.mClearShoppingCart.setVisibility(View.GONE);
        this.mLlCart.setVisibility(View.GONE);
        this.layout_empty_shopcart.setVisibility(View.GONE);
    }

    private void delAllGoodsInfo()
    {
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        while (i < this.datas.size())
        {
            localArrayList.add(this.datas.get(i));
            i += 1;
        }
        this.datas.removeAll(localArrayList);
        this.mShoppingCartAdapter.mData = this.datas;
        this.mShoppingCartAdapter.notifyDataSetChanged();
        clearCart();
    }

    private ArrayList<GoodsInfo> initDataTest()
    {
        ArrayList localArrayList = new ArrayList();
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
            localGoodsInfo.setSize("60");
            localGoodsInfo.setGoodsImg(1);
            localArrayList.add(localGoodsInfo);
            i += 1;
        }
        return localArrayList;
    }

    private void onRefreshCart()
    {
        this.mClearShoppingCart.setVisibility(View.VISIBLE);
        this.mLlCart.setVisibility(View.VISIBLE);
        this.layout_empty_shopcart.setVisibility(View.GONE);
    }


    protected void findViews(View paramView)
    {
        this.rootView = paramView;
        this.mClearShoppingCart = ((TextView)this.rootView.findViewById(R.id.clear_shopping_cart));
        this.mAllCheckBox = ((CheckBox)this.rootView.findViewById(R.id.all_checkBox));
        this.mClearShoppingCart.setOnClickListener(this);
        this.mAllCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
            {
                ShoppingFragment.this.checkAllGoodsInfo(paramAnonymousBoolean);
                ShoppingFragment.this.AddcheckAllGoodsInfoPrice(paramAnonymousBoolean);
            }
        });
        this.recyclerView = ((SuperRecyclerView)this.rootView.findViewById(R.id.recyclerView));
        this.mTotalPrice = ((TextView)this.rootView.findViewById(R.id.total_price));
        this.mGoPay = ((TextView)this.rootView.findViewById(R.id.go_pay));
        this.mGoPay.setOnClickListener(this);
        this.mOrderInfo = ((LinearLayout)this.rootView.findViewById(R.id.order_info));
        this.mShareGoods = ((TextView)this.rootView.findViewById(R.id.share_goods));
        this.mCollectGoods = ((TextView)this.rootView.findViewById(R.id.collect_goods));
        this.mDelGoods = ((TextView)this.rootView.findViewById(R.id.del_goods));
        this.mShareInfo = ((LinearLayout)this.rootView.findViewById(R.id.share_info));
        this.mLlCart = ((RelativeLayout)this.rootView.findViewById(R.id.ll_cart));
        this.check_LL = ((LinearLayout)this.rootView.findViewById(R.id.check_LL));
        this.layout_empty_shopcart = this.rootView.findViewById(R.id.layout_empty_shopcart);
        this.layout_empty_shopcart.findViewById(R.id.no_cart).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                MainActivity.setCurrentTab(1);
            }
        });
//        paramView = new GridLayoutManager(this.mActivity, 1);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this.mActivity, 1));
        this.recyclerView.setRefreshEnabled(true);
        this.recyclerView.setLoadMoreEnabled(false);
        this.recyclerView.setLoadingListener(this);
        this.recyclerView.setRefreshProgressStyle(22);
        this.recyclerView.setArrowImageView(2130903052);
        this.mShoppingCartAdapter = new ShoppingCartAdapter(this.mActivity);
        this.mShoppingCartAdapter.setOnCheckClickListener(this);
        this.mShoppingCartAdapter.setOnDelClickListener(this);
        this.recyclerView.setAdapter(this.mShoppingCartAdapter);
        this.mShoppingCartAdapter.setOnItemClickListener(this);
        this.recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener()
        {
            public void onScrollStateChanged(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt)
            {
                super.onScrollStateChanged(paramAnonymousRecyclerView, paramAnonymousInt);
                if (paramAnonymousInt == 0)
                {
//                    GlideImageloader.GlidedResumeRequests(ShoppingFragment.this.mActivity);
                    return;
                }
//                GlideImageloader.GlidedPauseRequests(ShoppingFragment.this.mActivity);
            }

            public void onScrolled(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt1, int paramAnonymousInt2)
            {
                super.onScrolled(paramAnonymousRecyclerView, paramAnonymousInt1, paramAnonymousInt2);
            }
        });
    }

    protected void initData()
    {
        this.recyclerView.setRefreshing(true);
        this.handler.sendEmptyMessageDelayed(0, 1000L);
    }

    public void updateloadData(int paramInt)
    {
        initDataTest();
        if (this.datas.size() > 0)
        {
            onRefreshCart();
//            if (paramInt != 0) {
//                break label66;
//            }
            this.datas = initDataTest();
            this.mShoppingCartAdapter.mData = this.datas;
            this.recyclerView.completeRefresh();
            this.mShoppingCartAdapter.notifyDataSetChanged();
        }
//        label66:
        while (paramInt != 1)
        {
            return;
        }
        this.datas.addAll(initDataTest());
        this.mShoppingCartAdapter.mData = this.datas;
        this.recyclerView.completeLoadMore();
    }

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
    public void onClick(View v) {
        switch (v.getId())
        {
            default:
                return;
            case R.id.clear_shopping_cart:
                delAllGoodsInfo();
                return;
        }
//        if (this.count > 0)
//        {
//            v = new Bundle();
//            SysUtils.startActivity(this.mActivity, SureOrderActivity.class, paramView);
//            return;
//        }
//        clearCart();
    }

    @Override
    public void onRefresh() {
        this.handler.sendEmptyMessageDelayed(0, 1000L);
    }

    @Override
    public void onLoadMore() {
        this.handler.sendEmptyMessageDelayed(0, 1000L);
    }

    @Override
    public void onCheckClickListener(int paramInt, boolean paramBoolean) {
        List localList = this.datas;
        if (paramBoolean)
        {
            int i = 0;
            while (i < localList.size())
            {
                if (((GoodsInfo)this.datas.get(paramInt)).getId() == ((GoodsInfo)localList.get(i)).getId())
                {
                    double d = this.price;
                    this.price = (((GoodsInfo)this.datas.get(paramInt)).getPrice() + d);
                    this.count += 1;
                }
                i += 1;
            }
        }
        int i = 0;
        while (i < localList.size())
        {
            if (((GoodsInfo)this.datas.get(paramInt)).getId() == ((GoodsInfo)localList.get(i)).getId())
            {
                this.price -= ((GoodsInfo)this.datas.get(paramInt)).getPrice();
                this.count -= 1;
            }
            i += 1;
        }
        this.mTotalPrice.setText("¥ " + this.price + "元");
        this.mGoPay.setText(" 结算 " + this.count + " ）");
    }

    @Override
    public void onDelClickListener(int paramInt) {
//        Activity localActivity = this.mActivity;
//        AlertView.Style localStyle = AlertView.Style.Alert;
//        OnItemClickListener local5 = new OnItemClickListener()
//        {
//            public void onItemClick(Object paramAnonymousObject, int paramAnonymousInt)
//            {
//                if (paramAnonymousInt != -1)
//                {
//                    paramAnonymousObject = ShoppingFragment.this.datas;
//                    paramAnonymousInt = 0;
//                    while (paramAnonymousInt < ((List)paramAnonymousObject).size())
//                    {
//                        if (((GoodsInfo)ShoppingFragment.this.datas.get(paramInt)).getId() == ((GoodsInfo)((List)paramAnonymousObject).get(paramAnonymousInt)).getId()) {
//                            ((List)paramAnonymousObject).remove(paramAnonymousInt);
//                        }
//                        paramAnonymousInt += 1;
//                    }
//                    if (ShoppingFragment.this.datas.size() > 0)
//                    {
//                        ShoppingFragment.access$202(ShoppingFragment.this, (List)paramAnonymousObject);
//                        ShoppingFragment.this.mShoppingCartAdapter.setDatas(ShoppingFragment.this.datas);
//                        ShoppingFragment.this.recyclerView.completeRefresh();
//                        ShoppingFragment.this.mShoppingCartAdapter.notifyDataSetChanged();
//                    }
//                }
//                else
//                {
//                    return;
//                }
//                ShoppingFragment.this.clearCart();
//            }
//        };
//        new AlertView("������", "������������������������", "������", new String[] { "������" }, null, localActivity, localStyle, local5).setCancelable(true).show();
    }

    @Override
    public void onItemClick(View view, Object item, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("", "");
        SysUtils.startActivity(this.mActivity, ShoppingDetailActivity.class, bundle);
    }
}
