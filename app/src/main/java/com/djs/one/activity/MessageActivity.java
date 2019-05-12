package com.djs.one.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.djs.one.bean.MessageBean;
import com.djs.one.manager.TokenManager;
import com.djs.one.util.ShoppingUtils;
import com.djs.one.util.ToastUtils;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import com.djs.one.R;
import com.djs.one.adapter.MessageAdapter;
import com.djs.one.bean.Message;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageActivity extends BaseActivity implements View.OnClickListener {

    private TitleBar titleBarView;

    private ListView messageList;
    private TextView noMessageTipsView;
    private MessageAdapter adapter;
    private List<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        findViews();
        initData();
    }

    @Override
    protected void findViews() {
        super.findViews();
        titleBarView = findViewById(R.id.message_list_activity_title_bar);
        noMessageTipsView = findViewById(R.id.no_msg_tips);
        titleBarView.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                mFinish();
            }

            @Override
            public void onTitleClick(View v) {
            }

            @Override
            public void onRightClick(View v) {
            }
        });

        messageList = findViewById(R.id.message_list);

        adapter = new MessageAdapter(this,messages);

        messageList.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();
        Call<MessageBean> messageCall =  ShoppingUtils.getApi().getMessegeList(TokenManager.getInstance().getLoginToken().getData().getToken(),
                "200","1");
        Log.d("token", "token:" + TokenManager.getInstance().getLoginToken().getData().getToken());
        messageCall.enqueue(new Callback<MessageBean>() {
            @Override
            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                MessageBean  messageBean = response.body();
                messages = messageBean.getData().getList();
                if (messages == null || messages.size() == 0) {
                    noMessageTipsView.setVisibility(View.VISIBLE);
                } else {
                    noMessageTipsView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<MessageBean> call, Throwable t) {
                ToastUtils.showToast(MessageActivity.this, t.getLocalizedMessage());
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_title_bar_menu:
                mFinish();
                break;
            default:
                break;
        }
    }
}
