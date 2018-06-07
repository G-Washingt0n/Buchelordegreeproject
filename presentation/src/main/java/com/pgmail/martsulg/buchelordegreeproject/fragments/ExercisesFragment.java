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
import com.pgmail.martsulg.buchelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.buchelordegreeproject.databinding.FragmentExercisesBinding;
import com.pgmail.martsulg.buchelordegreeproject.viewModels.ExercisesViewModel;

import p.martsulg.data.models.TrainingsFeed;

/**
 * Created by g_washingt0n on 15.04.2018.
 */

public class ExercisesFragment extends MyFragment {

    private String trainingId;

    public static ExercisesFragment getInstance(FragmentManager fragmentManager, TrainingsFeed feed) {
        Fragment fragment = fragmentManager
                .findFragmentByTag(ExercisesFragment.class.getName());
        ExercisesFragment exercisesFragment;
        if (fragment != null && fragment instanceof ExercisesFragment) {
            exercisesFragment = (ExercisesFragment) fragment;
        } else {
            exercisesFragment = new ExercisesFragment();

        }
        Bundle bundle = new Bundle();
        bundle.putString(NavigationActivity.ID, feed.getObjectId());
        exercisesFragment.setArguments(bundle);

        return exercisesFragment;
    }

    private LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

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
        binding.exercisesRecyclerView.setLayoutManager(layoutManager);

        return binding.getRoot();
    }

}
