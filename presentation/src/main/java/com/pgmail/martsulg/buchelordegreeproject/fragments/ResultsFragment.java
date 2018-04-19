package com.pgmail.martsulg.buchelordegreeproject.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by g_washingt0n on 15.04.2018.
 */

public class ResultsFragment extends Fragment {
    private static ResultsFragment instance;

    public ResultsFragment newInstance() {
        if (instance == null) {
            instance = new ResultsFragment();
        }
        return instance;
    }


}
