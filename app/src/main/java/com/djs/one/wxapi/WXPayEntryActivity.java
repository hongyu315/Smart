package com.djs.one.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.djs.one.R;
import com.djs.one.constant.Constant;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "fate";

    private IWXAPI mWxApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);


        mWxApi = WXAPIFactory.createWXAPI(this, Constant.APP_ID);
        mWxApi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mWxApi.handleIntent(intent, this);
    }


    @Override
    public void onReq(BaseReq baseReq) {
        Log.e(TAG, "onReq" + baseReq.toString());
    }

    @Override
    public void onResp(BaseResp baseResp) {

        Log.d(TAG, "onPayFinish, errCode = " + baseResp.errCode);// 支付结果码
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                Log.e(TAG, "支付成功");
                // 还需要向商户后台请求支付结果，最终结果以后台返回结果为准
                break;
            case BaseResp.ErrCode.ERR_COMM:
                // 发生错误 可能的原因：签名错误、未注册APPID、项目设置APPID不正确、
                // 注册的APPID与设置的不匹配、其他异常等。
                Log.e(TAG, "发生错误");
                Toast.makeText(WXPayEntryActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                // -2 用户取消 发生场景：用户不支付了，点击取消，返回APP。
                Log.e(TAG, "用户取消");
                break;
            default:
                Log.e(TAG, "支付失败-其他的锅" + baseResp.errCode);
                break;
        }

        finish();
    }
}
