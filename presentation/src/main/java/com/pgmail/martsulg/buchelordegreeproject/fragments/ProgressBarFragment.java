package com.pgmail.martsulg.buchelordegreeproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.buchelordegreeproject.R;

/**
 * Created by g_washingt0n on 02.05.2018.
 */

public class ProgressBarFragment extends DialogFragment {
    private static ProgressBarFragment instance;

    public ProgressBarFragment getInstance() {
        if (instance == null) {
            instance = new ProgressBarFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_progress, container, false);
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
