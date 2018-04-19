package com.pgmail.martsulg.buchelordegreeproject.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by g_washingt0n on 15.04.2018.
 */

public class TrainingsFragment extends Fragment {
    private static TrainingsFragment instance;

    public TrainingsFragment newInstance() {
        if (instance == null) {
            instance = new TrainingsFragment();
        }
        return instance;
    }
}
