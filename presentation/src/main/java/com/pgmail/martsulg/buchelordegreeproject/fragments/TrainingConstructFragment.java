package com.pgmail.martsulg.buchelordegreeproject.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.buchelordegreeproject.R;
import com.pgmail.martsulg.buchelordegreeproject.databinding.FragmentConstructTrainingBinding;
import com.pgmail.martsulg.buchelordegreeproject.viewModels.TrainingConstructViewModel;

import p.martsulg.data.models.TrainingsFeed;


public class TrainingConstructFragment extends MyDialogFragment {

    private static TrainingConstructFragment instance;
    private Bundle bundle = null;

    public TrainingConstructFragment getInstance(TrainingsFeed feed) {
        if (instance == null) {
            instance = new TrainingConstructFragment();
        }
        if (feed != null) {
            Bundle bundle = new Bundle();
            bundle.putString("name", feed.getTrainingName());
            bundle.putString("id", feed.getObjectId());
            bundle.putInt("weekday", feed.getWeekday());
            bundle.putLong("time", feed.getTime());
            bundle.putFloat("complexity", feed.getComplexity());
            this.setArguments(bundle);
        } else {
            this.setArguments(null);
        }

        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            bundle = this.getArguments();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TrainingConstructViewModel viewModel = new TrainingConstructViewModel(getActivity(), bundle);
        this.viewModel = viewModel;
        FragmentConstructTrainingBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_construct_training, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }
}
