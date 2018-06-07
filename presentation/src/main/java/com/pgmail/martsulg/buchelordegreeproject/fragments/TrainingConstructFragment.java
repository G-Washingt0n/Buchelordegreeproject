package com.pgmail.martsulg.buchelordegreeproject.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.buchelordegreeproject.R;
import com.pgmail.martsulg.buchelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.buchelordegreeproject.databinding.FragmentConstructTrainingBinding;
import com.pgmail.martsulg.buchelordegreeproject.viewModels.TrainingConstructViewModel;

import p.martsulg.data.models.TrainingsFeed;


public class TrainingConstructFragment extends MyFragment {

    private Bundle bundle = null;

    public TrainingConstructFragment getInstance(FragmentManager fragmentManager, TrainingsFeed feed) {
        Fragment fragment = fragmentManager
                .findFragmentByTag(TrainingsFragment.class.getName());
        TrainingConstructFragment trainingConstructFragment;
        if (fragment != null && fragment instanceof TrainingsFragment) {
            trainingConstructFragment = (TrainingConstructFragment) fragment;
        } else {
            trainingConstructFragment = new TrainingConstructFragment();

        }

        if (feed != null) {
            Bundle bundle = new Bundle();
            bundle.putString(NavigationActivity.NAME, feed.getTrainingName());
            bundle.putString(NavigationActivity.ID, feed.getObjectId());
            bundle.putInt(NavigationActivity.WEEKDAY, feed.getWeekday());
            bundle.putLong(NavigationActivity.TIME, feed.getTime());
            bundle.putFloat(NavigationActivity.COMPLEXITY, feed.getComplexity());
            trainingConstructFragment.setArguments(bundle);
        } else {
            trainingConstructFragment.setArguments(null);
        }

        return trainingConstructFragment;
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

        TrainingConstructViewModel viewModel = new TrainingConstructViewModel(this, getActivity(), bundle);
        this.viewModel = viewModel;
        FragmentConstructTrainingBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_construct_training, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }
}
