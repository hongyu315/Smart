package hongyu315.com.smart2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.adapter.SearchAdapter;
import hongyu315.com.smart2.bean.SearchContent;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private EditText searchEditText;
    private TextView cancalTextView;
    private GridView searchContentList;
    private SearchAdapter adapter;
    private List<SearchContent> dataList = new ArrayList<>();

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

        adapter = new SearchAdapter(this,dataList);

        searchContentList.setAdapter(adapter);
        cancalTextView.setOnClickListener(this);
    }

    protected void initData(){
        for (int i = 0; i < 10; i++) {
            SearchContent content = new SearchContent();
            content.setsContent("袜子" + i);
            dataList.add(content);
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
