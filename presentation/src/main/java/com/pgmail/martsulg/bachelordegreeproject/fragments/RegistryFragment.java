package com.pgmail.martsulg.bachelordegreeproject.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.bachelordegreeproject.R;
import com.pgmail.martsulg.bachelordegreeproject.databinding.FragmentRegistryBinding;
import com.pgmail.martsulg.bachelordegreeproject.viewModels.RegistryViewModel;


public class RegistryFragment extends Fragment {
    private static RegistryFragment instance;

    public RegistryFragment getInstance() {
        if (instance == null) {
            instance = new RegistryFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RegistryViewModel viewModel = new RegistryViewModel(getActivity(), getContext());
        FragmentRegistryBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_registry, container, false);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

}
