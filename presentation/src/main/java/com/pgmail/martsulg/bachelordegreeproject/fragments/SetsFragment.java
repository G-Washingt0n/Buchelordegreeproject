package com.pgmail.martsulg.bachelordegreeproject.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.bachelordegreeproject.R;
import com.pgmail.martsulg.bachelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.bachelordegreeproject.databinding.FragmentExercisesBinding;
import com.pgmail.martsulg.bachelordegreeproject.viewModels.ExercisesViewModel;

import p.martsulg.data.models.ExercisesFeed;

/**
 * Created by g_washingt0n on 15.04.2018.
 */

public class SetsFragment extends MyFragment {

    private String trainingId;

    public static SetsFragment getInstance(FragmentManager fragmentManager, ExercisesFeed feed) {
        Fragment fragment = fragmentManager
                .findFragmentByTag(SetsFragment.class.getName());
        SetsFragment exercisesFragment;
        if (fragment != null && fragment instanceof SetsFragment) {
            exercisesFragment = (SetsFragment) fragment;
        } else {
            exercisesFragment = new SetsFragment();

        }
        Bundle bundle = new Bundle();
        bundle.putString(NavigationActivity.ID, feed.getObjectId());
        exercisesFragment.setArguments(bundle);

        return exercisesFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        trainingId = bundle.getString(NavigationActivity.ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ExercisesViewModel exercisesViewModel = new ExercisesViewModel(getActivity(), trainingId);
        this.viewModel = exercisesViewModel;
        FragmentExercisesBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exercises, container, false);
        binding.setViewModel(exercisesViewModel);
        binding.exercisesRecyclerView.setAdapter(exercisesViewModel.adapter);
        binding.exercisesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }

}
