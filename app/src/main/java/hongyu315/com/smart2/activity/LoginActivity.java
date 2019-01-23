package hongyu315.com.smart2.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.util.SysUtils;
import hongyu315.com.smart2.util.ToastUtils;

public class LoginActivity extends BaseActivity {

    private EditText phoneEditText;
    private EditText imageCodeEditText;
    private EditText msgCodeEditText;
    private Button getMsgCodeButton;
    private CheckBox licenceCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
    }

    @Override
    protected void findViews() {
        super.findViews();
        phoneEditText = findViewById(R.id.phone_edit_text);
        imageCodeEditText = findViewById(R.id.image_verifier_edit_text);
        msgCodeEditText = findViewById(R.id.msg_verifier_edit_text);
        getMsgCodeButton = findViewById(R.id.get_verifier_code_btn);
        licenceCheckBox = findViewById(R.id.licence_checkbox);

    }

    public void onLoginBtnClick(View view){
        String phoneStr = phoneEditText.getText().toString();
        String imageCodeStr = imageCodeEditText.getText().toString();
        String msgCodeStr = msgCodeEditText.getText().toString();

        if (TextUtils.isEmpty(phoneStr)){
            ToastUtils.showToast(LoginActivity.this,"请输入手机号");
            return;
        }

        if (SysUtils.isPhoneNumber(phoneStr)){
            ToastUtils.showToast(LoginActivity.this,"请输入正确的手机号");
            return;
        }

        if (TextUtils.isEmpty(imageCodeStr)){
            ToastUtils.showToast(LoginActivity.this,"请输入图形验证码");
            return;
        }


        if (TextUtils.isEmpty(msgCodeStr)){
            ToastUtils.showToast(LoginActivity.this,"请输入短信验证码");
            return;
        }
    }

    public void onLoginCloseClick(View view){
        mFinish();
    }
}
