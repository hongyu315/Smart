package hongyu315.com.smart2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.adapter.MessageAdapter;
import hongyu315.com.smart2.bean.Message;
import hongyu315.com.smart2.constant.Constant;

public class MessageActivity extends BaseActivity implements View.OnClickListener {

    private TitleBar titleBarView;

    private ListView messageList;
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

        for (int i = 0; i < 5; i++) {
            String url = "http://pic.downcc.com/upload/2018-3/2018316144426875970.jpg";
            String status = "已签收";
            String name = "商品名字" + i;
            String time = "2019-01-07";
            String num = "10099939909999999";
            Message msg = new Message();
            msg.iconUrl = url;
            msg.orderStatus = status;
            msg.orderName = name;
            msg.orderTime = time;
            msg.orderNum = num;
            msg.setType(Constant.MESSAGE);
            messages.add(msg);
        }

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
