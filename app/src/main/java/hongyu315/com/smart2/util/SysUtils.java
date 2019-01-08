package hongyu315.com.smart2.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;

import hongyu315.com.smart2.R;

public class SysUtils {
    public static <T> void startActivity(Activity paramActivity, Class<T> paramClass)
    {
        paramActivity.startActivity(new Intent(paramActivity, paramClass));
        paramActivity.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    public static <T> void startActivity(Activity paramActivity, Class<T> paramClass, Bundle paramBundle)
    {
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
}
