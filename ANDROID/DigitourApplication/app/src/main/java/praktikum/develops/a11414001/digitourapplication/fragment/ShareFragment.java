package praktikum.develops.a11414001.digitourapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import praktikum.develops.a11414001.digitourapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment {


    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share, container, false);
    }

}