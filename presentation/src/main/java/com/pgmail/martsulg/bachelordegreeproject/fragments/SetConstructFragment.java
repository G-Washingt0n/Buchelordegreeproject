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
import com.pgmail.martsulg.bachelordegreeproject.databinding.FragmentConstructSetBinding;
import com.pgmail.martsulg.bachelordegreeproject.viewModels.SetConstructViewModel;

import p.martsulg.data.models.SetsFeed;


public class SetConstructFragment extends MyFragment {

    private Bundle bundle = null;
    private String currentExerciseId;


    public SetConstructFragment getInstance(FragmentManager fragmentManager, SetsFeed feed, String currentExerciseId) {
        Fragment fragment = fragmentManager
                .findFragmentByTag(SetConstructFragment.class.getName());
        SetConstructFragment setConstructFragment;
        if (fragment != null && fragment instanceof TrainingsFragment) {
            setConstructFragment = (SetConstructFragment) fragment;
        } else {
            setConstructFragment = new SetConstructFragment();

        }

        if (feed != null) {
            Bundle bundle = new Bundle();
            bundle.putString(NavigationActivity.ID, feed.getObjectId());
            bundle.putLong(NavigationActivity.TIME, feed.getReqTime());
            bundle.putLong(NavigationActivity.REST_TIME, feed.getRestTime());
            setConstructFragment.setArguments(bundle);
        } else {
            setConstructFragment.setArguments(null);
        }

        setConstructFragment.currentExerciseId = currentExerciseId;
        return setConstructFragment;
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

        SetConstructViewModel viewModel = new SetConstructViewModel(this, getActivity(), bundle, currentExerciseId);
        this.viewModel = viewModel;
        FragmentConstructSetBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_construct_set, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }
}
