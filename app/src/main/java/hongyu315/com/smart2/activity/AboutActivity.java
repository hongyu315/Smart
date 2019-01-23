package hongyu315.com.smart2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import hongyu315.com.smart2.R;

public class AboutActivity extends BaseActivity {

    private TitleBar titleBar;
    private TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        findViews();
        initData();
    }

    @Override
    protected void findViews() {
        super.findViews();

        titleBar = findViewById(R.id.about_title_bar);
        version = findViewById(R.id.about_version);

        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
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
    }

    @Override
    protected void initData() {
        super.initData();

        version.setText("当前版本：" + getResources().getString(R.string.version));
    }

    public void onAuthNoticeLayoutClick(View view){

    }

    public void onPrivateLicenceLayoutClick(View view){

    }
}
