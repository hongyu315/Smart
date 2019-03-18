package com.com.one;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;

import com.com.one.util.MyFileNameGenerator;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/1/23<p>
 * <p>更改时间：2019/1/23<p>
 * <p>版本号：1<p>
 */
public class ForOneApplication extends Application {

    private HttpProxyCacheServer proxy;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static HttpProxyCacheServer getProxy(Context context) {
        ForOneApplication app = (ForOneApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        HttpProxyCacheServer proxy = new HttpProxyCacheServer.Builder(this)
                .fileNameGenerator(new MyFileNameGenerator())
                .build();
        return proxy;
    }
}
