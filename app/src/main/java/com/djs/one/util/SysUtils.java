package com.djs.one.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.djs.one.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SysUtils {
    public static <T> void startActivity(Activity paramActivity, Class<T> paramClass)
    {
        paramActivity.startActivity(new Intent(paramActivity, paramClass));
        paramActivity.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    public static <T> void startActivity(Activity paramActivity, Class<T> paramClass, Bundle paramBundle)
    {
//        Intent intent = new Intent(paramActivity,paramClass);
//        intent.putExtra("address",addressList.get(position));
//        startActivity(intent);

//        paramClass = new Intent(paramActivity, paramClass);
//        if (paramBundle != null) {
//            paramClass.putExtras(paramBundle);
//        }
        paramActivity.startActivity(new Intent(paramActivity, paramClass),paramBundle);
//        paramActivity.overridePendingTransition(2131034134, 2131034135);
    }

    public static int getPixelsFromDp(Activity context,int size){

        DisplayMetrics metrics =new DisplayMetrics();

        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return(size * metrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT;

    }

    public static int getScreenWidth(Activity activity){
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Activity activity){
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static void finish(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.finish_in,R.anim.finish_to);
    }

    /**
     * 判断手机号是否符合规范
     * @param phoneNo 输入的手机号
     * @return
     */
    public static boolean isPhoneNumber(String phoneNo) {
        if (TextUtils.isEmpty(phoneNo)) {
            return false;
        }
        if (phoneNo.length() == 11) {
            for (int i = 0; i < 11; i++) {
                if (!PhoneNumberUtils.isISODigit(phoneNo.charAt(i))) {
                    return false;
                }
            }
            Pattern p = Pattern.compile("^((13[^4,\\D])" + "|(134[^9,\\D])" +
                    "|(14[5,7])" +
                    "|(15[^4,\\D])" +
                    "|(17[3,6-8])" +
                    "|(18[0-9]))\\d{8}$");
            Matcher m = p.matcher(phoneNo);
            return m.matches();
        }
        return false;
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


}
