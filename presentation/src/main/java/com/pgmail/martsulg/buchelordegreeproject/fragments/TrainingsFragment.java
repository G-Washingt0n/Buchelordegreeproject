package com.pgmail.martsulg.buchelordegreeproject.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.buchelordegreeproject.R;
import com.pgmail.martsulg.buchelordegreeproject.databinding.FragmentTrainingsBinding;
import com.pgmail.martsulg.buchelordegreeproject.viewModels.TrainingsViewModel;

/**
 * Created by g_washingt0n on 15.04.2018.
 */

public class TrainingsFragment extends MyFragment {

    public static TrainingsFragment getInstance(FragmentManager fragmentManager) {
        Fragment fragment = fragmentManager
                .findFragmentByTag(TrainingsFragment.class.getName());
        TrainingsFragment trainingsFragment;
        if (fragment != null && fragment instanceof TrainingsFragment) {
            trainingsFragment = (TrainingsFragment) fragment;
        } else {
            trainingsFragment = new TrainingsFragment();

        }
        return trainingsFragment;
    }

    private LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TrainingsViewModel trainingsViewModel = new TrainingsViewModel(getActivity());
        this.viewModel = trainingsViewModel;
        FragmentTrainingsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trainings, container, false);
        binding.setViewModel(trainingsViewModel);
        binding.trainingsRecyclerView.setAdapter(trainingsViewModel.adapter);
        binding.trainingsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }

}
