package com.djs.one.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.djs.one.R;
import com.djs.one.adapter.SearchAdapter;
import com.djs.one.api.API;
import com.djs.one.api.URL;
import com.djs.one.bean.KeywordsBean;
import com.djs.one.constant.Constant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.djs.one.constant.Constant.From_Order;
import static com.djs.one.constant.Constant.From_Product;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private EditText searchEditText;
    private TextView cancalTextView;
    private GridView searchContentList;
    private SearchAdapter adapter;
    private List<String> dataList = new ArrayList<>();
    private int fromWhichPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        fromWhichPage = getIntent().getIntExtra("index",From_Product);

        initView();
        initData();
    }

    protected void initView(){
        searchEditText = findViewById(R.id.search_edit_text);
        cancalTextView = findViewById(R.id.search_cancal);
        searchContentList = findViewById(R.id.search_content_list);

        adapter = new SearchAdapter(this,dataList);

        searchContentList.setAdapter(adapter);
        cancalTextView.setOnClickListener(this);
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    launchSearchResultActivity(searchEditText.getText().toString().trim());
                }

                return false;
            }
        });

        searchContentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                launchSearchResultActivity(dataList.get(i));
            }
        });
    }

    private void launchSearchResultActivity(String keywords){
        Intent intent = new Intent();
        if (fromWhichPage == From_Order){
            intent.setClassName(getPackageName(),"com.djs.one.activity.MyOrderSearchResultActivity");
        }else if (fromWhichPage == From_Product){
            intent.setClassName(getPackageName(),"com.djs.one.activity.ProductSearchActivity");
        }
        intent.putExtra("key",keywords);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    protected void initData(){
        getSearchKeyworkds();
    }

    private void getSearchKeyworkds(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<KeywordsBean> products = api.keywords();
        products.enqueue(new Callback<KeywordsBean>() {
            @Override
            public void onResponse(Call<KeywordsBean> call, Response<KeywordsBean> response) {

                try {
                    KeywordsBean productBean = response.body();
                    if (Constant.SUCCESSFUL == productBean.getCode()){
                        if (productBean.getData() == null) return;
                        if (productBean.getData().size() > 0){
                            dataList = productBean.getData();

                            adapter = new SearchAdapter(SearchActivity.this,dataList);
                            searchContentList.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<KeywordsBean> call, Throwable t) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_cancal:
                mFinish();
                return;
            default:
                return;
        }
    }
}
