package com.pgmail.martsulg.bachelordegreeproject.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.bachelordegreeproject.R;
import com.pgmail.martsulg.bachelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.bachelordegreeproject.databinding.FragmentConstructExerciseBinding;
import com.pgmail.martsulg.bachelordegreeproject.viewModels.ExerciseConstructViewModel;

import p.martsulg.data.models.ExercisesFeed;


public class ExerciseConstructFragment extends MyFragment {

    private Bundle bundle = null;

    public ExerciseConstructFragment getInstance(FragmentManager fragmentManager, ExercisesFeed feed) {
        Fragment fragment = fragmentManager
                .findFragmentByTag(TrainingConstructFragment.class.getName());
        ExerciseConstructFragment exerciseConstructFragment;
        if (fragment != null && fragment instanceof TrainingsFragment) {
            exerciseConstructFragment = (ExerciseConstructFragment) fragment;
        } else {
            exerciseConstructFragment = new ExerciseConstructFragment();

        }
        if (feed != null) {

            //TODO remove with new fields
            Bundle bundle = new Bundle();
            bundle.putString(NavigationActivity.NAME, feed.getExerciseName());
            bundle.putString(NavigationActivity.ID, feed.getObjectId());
            bundle.putInt(NavigationActivity.NUMBER, feed.getSetsNum());
            bundle.putInt(NavigationActivity.POSITION, feed.getQueuePos());
            exerciseConstructFragment.setArguments(bundle);
        } else {
            exerciseConstructFragment.setArguments(null);
        }

        return exerciseConstructFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            bundle = this.getArguments();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ExerciseConstructViewModel viewModel = new ExerciseConstructViewModel(this, getActivity(), bundle);
        this.viewModel = viewModel;
        FragmentConstructExerciseBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_construct_exercise, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }
}
