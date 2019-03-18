package com.com.one.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.com.one.util.SysUtils;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    protected void findViews(){

    }

    protected void initData(){

    }

    protected void mFinish(){
        finish();
        SysUtils.finish(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mFinish();
    }
}
