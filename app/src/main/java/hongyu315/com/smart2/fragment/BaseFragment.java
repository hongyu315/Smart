package hongyu315.com.smart2.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment
        extends Fragment
{
    protected String TAG = "Smart";
    public final int TIMEOUT = 30000;
    protected Activity mActivity;
    protected FragmentManager mFragmentManager;

    protected void findViews(View paramView) {}

    protected void initData() {}

    public void onActivityCreated(Bundle paramBundle)
    {
        super.onActivityCreated(paramBundle);
    }

    public void onAttach(Activity paramActivity)
    {
        super.onAttach(paramActivity);
        this.mActivity = paramActivity;
    }

    public boolean onBackPressed()
    {
        return false;
    }

    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        this.TAG = getClass().getSimpleName();
        this.mFragmentManager = getChildFragmentManager();
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        return super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    }

    public void onDestroy()
    {
        this.mActivity = null;
        super.onDestroy();
    }

    public void onDestroyView()
    {
        super.onDestroyView();
    }

    public void onDetach()
    {
        super.onDetach();
    }

    public void onPause()
    {
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();
    }

    public void onSaveInstanceState(Bundle paramBundle)
    {
        super.onSaveInstanceState(paramBundle);
    }

    public void onStop()
    {
        super.onStop();
    }

    public void onViewCreated(View paramView, Bundle paramBundle)
    {
        super.onViewCreated(paramView, paramBundle);
    }
}
