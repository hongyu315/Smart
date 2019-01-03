package hongyu315.com.smart2.util;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import hongyu315.com.smart2.R;

public class ToastUtils
{
    private static Handler mHandler = new Handler();
    private static Toast mToast;
    private static Runnable r = new Runnable()
    {
        public void run()
        {
            ToastUtils.mToast.cancel();
//            ToastUtil.mToast.cancel();
//            ToastUtil.access$002(null);
        }
    };

    public static void showToast(Context paramContext, String paramString)
    {
        View localView = ((LayoutInflater)paramContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.toast_util, null);
        ((TextView)localView.findViewById(R.id.toast_message)).setText(paramString);
        mHandler.removeCallbacks(r);
        if (mToast == null)
        {
            mToast = new Toast(paramContext);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setGravity(80, 0, 150);
            mToast.setView(localView);
        }
        mHandler.postDelayed(r, 1000L);
        mToast.show();
    }
}
