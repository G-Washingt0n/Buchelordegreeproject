package com.pgmail.martsulg.bachelordegreeproject.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.bachelordegreeproject.R;
import com.pgmail.martsulg.bachelordegreeproject.databinding.FragmentProfileBinding;
import com.pgmail.martsulg.bachelordegreeproject.viewModels.ProfileViewModel;

/**
 * Created by g_washingt0n on 15.04.2018.
 */

public class ProfileFragment extends Fragment {
    private static ProfileFragment instance;

    public ProfileFragment newInstance() {
        if (instance == null) {
            instance = new ProfileFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ProfileViewModel viewModel = new ProfileViewModel(getActivity());
        FragmentProfileBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }
}
