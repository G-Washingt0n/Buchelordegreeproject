package com.pgmail.martsulg.bachelordegreeproject.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by g_washingt0n on 15.04.2018.
 */

public class ProfileFragment extends Fragment {
    private static ProfileFragment instance;

    public ProfileFragment newInstance() {
        if (instance == null) {
            instance = new ProfileFragment();
        }
        return instance;
    }


}
