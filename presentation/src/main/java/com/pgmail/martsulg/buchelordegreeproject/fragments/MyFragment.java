package com.pgmail.martsulg.buchelordegreeproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.buchelordegreeproject.viewModels.MyViewModel;

/**
 * Created by g_washingt0n on 08.02.2018.
 */

public abstract class MyFragment extends Fragment {
    public MyViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel.init();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.release();
    }


}
