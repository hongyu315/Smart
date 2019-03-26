package com.djs.one;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.danikula.videocache.HttpProxyCacheServer;
import com.djs.one.util.MyFileNameGenerator;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

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

        // 在此处调用基础组件包提供的初始化函数 相应信息可在应用管理 -> 应用信息 中找到 http://message.umeng.com/list/apps
// 参数一：当前上下文context；
// 参数二：应用申请的Appkey（需替换）；
// 参数三：渠道名称；
// 参数四：设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机；
// 参数五：Push推送业务的secret 填充Umeng Message Secret对应信息（需替换）
        UMConfigure.init(this, "5c8ef97261f564f490000a1c", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "70d6885b210ad186c508eee7fa687019");


//获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Log.i("xxx","注册成功：deviceToken：-------->  " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("xxx","注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });

        UmengMessageHandler messageHandler = new UmengMessageHandler() {

            /**
             * 自定义通知栏样式的回调方法
             */
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                switch (msg.builder_id) {
                    case 0:

                        /**
                         *  创建通知栏管理工具
                         */

                        NotificationManager notificationManager = (NotificationManager) getSystemService
                                (NOTIFICATION_SERVICE);

                        /**
                         *  实例化通知栏构造器
                         */

                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);

                        /**
                         *  设置Builder
                         */
                        //设置标题
                        mBuilder.setContentTitle(msg.title)
                                //设置内容
                                .setContentText(msg.text)
                                //设置大图标
                                .setLargeIcon(getLargeIcon(context, msg))
                                //设置小图标
                                .setSmallIcon(R.mipmap.user_default_icon)
                                //设置通知时间
                                .setWhen(System.currentTimeMillis())
                                //首次进入时显示效果
                                .setTicker(msg.ticker)
                                //设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声音
                                .setDefaults(Notification.DEFAULT_SOUND);
                        //发送通知请求
                        notificationManager.notify(10, mBuilder.build());

//                        Notification.Builder builder = new Notification.Builder(context);
//                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
//                                R.layout.notification_view);
//                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
//                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
//                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
//                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
//                                getSmallIconId(context, msg));
//                        builder.setContent(myNotificationView)
//                                .setSmallIcon(getSmallIconId(context, msg))
//                                .setTicker(msg.ticker)
//                                .setAutoCancel(true);

//                        return builder.getNotification();
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler(){

            @Override
            public void dealWithCustomAction(Context context, UMessage msg){
                Log.e("xxx","click");
            }

        };

        mPushAgent.setNotificationClickHandler(notificationClickHandler);
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
