package com.djs.one.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.djs.one.R;
import com.djs.one.adapter.ProductAdapter;
import com.djs.one.bean.Product;
import com.djs.one.bean.SearchContent;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private EditText searchEditText;
    private TextView cancalTextView;
    private GridView searchContentList;
    private ProductAdapter adapter;
    private List<Product> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        initData();
    }

    protected void initView(){
        searchEditText = findViewById(R.id.search_edit_text);
        cancalTextView = findViewById(R.id.search_cancal);
        searchContentList = findViewById(R.id.search_content_list);

        adapter = new ProductAdapter(this,dataList);

        searchContentList.setAdapter(adapter);
        cancalTextView.setOnClickListener(this);
    }

    protected void initData(){
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setThumb_url("http://img3.imgtn.bdimg.com/it/u=2949159174,2649619291&fm=11&gp=0.jpg");            SearchContent content = new SearchContent();
            dataList.add(product);
        }

        adapter.notifyDataSetChanged();
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
