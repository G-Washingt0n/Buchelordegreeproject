package com.pgmail.martsulg.buchelordegreeproject.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by g_washingt0n on 15.04.2018.
 */

public class ProgressFragment extends Fragment {
    private static ProgressFragment instance;

    public ProgressFragment newInstance() {
        if (instance == null) {
            instance = new ProgressFragment();
        }
        return instance;
    }


}
