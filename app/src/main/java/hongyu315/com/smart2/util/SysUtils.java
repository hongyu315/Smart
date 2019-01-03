package hongyu315.com.smart2.util;

import android.app.Activity;
import android.content.Intent;

public class SysUtils {
    public static <T> void startActivity(Activity paramActivity, Class<T> paramClass)
    {
        paramActivity.startActivity(new Intent(paramActivity, paramClass));
//        paramActivity.overridePendingTransition(2131034134, 2131034135);
    }

//    public static <T> void startActivity(Activity paramActivity, Class<T> paramClass, Bundle paramBundle)
//    {
//        paramClass = new Intent(paramActivity, paramClass);
//        if (paramBundle != null) {
//            paramClass.putExtras(paramBundle);
//        }
//        paramActivity.startActivity(paramClass);
//        paramActivity.overridePendingTransition(2131034134, 2131034135);
//    }
}
