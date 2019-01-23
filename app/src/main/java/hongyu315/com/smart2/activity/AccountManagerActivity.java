package hongyu315.com.smart2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.manager.CacheManager;
import hongyu315.com.smart2.manager.UserManager;
import hongyu315.com.smart2.util.SysUtils;

public class AccountManagerActivity extends BaseActivity implements OnTitleBarListener {

    private TitleBar mTitleBar;
    private ImageView userIcon;
    private TextView userName;
    private TextView version;
    private TextView cacheSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manager);

        findViews();
        initData();
    }

    @Override
    protected void findViews() {
        super.findViews();

        mTitleBar = findViewById(R.id.account_manager_title_bar);

        userIcon = findViewById(R.id.user_icon);
        userName = findViewById(R.id.user_name);
        version = findViewById(R.id.version);
        cacheSize = findViewById(R.id.cache_size);

        mTitleBar.setOnTitleBarListener(this);
    }

    @Override
    protected void initData() {
        super.initData();

        try {
            version.setText(getResources().getString(R.string.version));
            cacheSize.setText(CacheManager.getInstance().getTotalCacheSize(AccountManagerActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onUserInfoLayoutClick(View view){
        SysUtils.startActivity(this,UserInfoActivity.class);
    }

    public void onAddressManagerLayoutClick(View view){
        SysUtils.startActivity(this,AddressManagerActivity.class);
    }

    public void onAboutLayoutClick(View view){
        SysUtils.startActivity(this,AboutActivity.class);
    }

    public void onClearCacheLayoutClick(View view){
        try {
            CacheManager.getInstance().clearAllCache(this);
            cacheSize.setText(CacheManager.getInstance().getTotalCacheSize(AccountManagerActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onLogoutButtonClick(View view){
        UserManager.getInstance().logout();
    }

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


}
