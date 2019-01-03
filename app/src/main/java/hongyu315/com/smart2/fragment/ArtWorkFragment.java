package hongyu315.com.smart2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hongyu315.com.smart2.R;

public class ArtWorkFragment extends BaseFragment {

    public ArtWorkFragment() {
    }
    public static ArtWorkFragment newInstance() {
        return new ArtWorkFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_art_work, container, false);
    }

}
