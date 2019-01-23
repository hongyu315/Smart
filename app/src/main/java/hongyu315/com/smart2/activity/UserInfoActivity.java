package hongyu315.com.smart2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.bean.User;

public class UserInfoActivity extends BaseActivity {

    private TitleBar mTitleBar;
    private ImageView userIcon;
    private TextView userNick;
    private TextView userSex;
    private TextView userBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        findViews();
        initData();
    }

    @Override
    protected void findViews() {
        super.findViews();

        mTitleBar = findViewById(R.id.user_info_title_bar);
        userIcon = findViewById(R.id.user_icon);
        userNick = findViewById(R.id.user_nick_name);
        userSex = findViewById(R.id.user_sex);
        userBirthday = findViewById(R.id.user_birthday);

        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
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

        try {
            userNick.setText(User.getInstance().getUserNick());
            userSex.setText(User.getInstance().getUserSex());
            userBirthday.setText(User.getInstance().getUserBirthday());
        }catch (Exception e){
        }
    }

    public void onUserIconLayoutClick(View view){

    }

    public void onUserNickLayoutClick(View view){

    }

    public void onUserSexLayoutClick(View view){

    }

    public void onUserBirthDayLayoutClick(View view){

    }
}
