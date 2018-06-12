package com.pgmail.martsulg.bachelordegreeproject.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.bachelordegreeproject.R;
import com.pgmail.martsulg.bachelordegreeproject.databinding.FragmentLoginBinding;
import com.pgmail.martsulg.bachelordegreeproject.viewModels.LogInViewModel;


public class LogInFragment extends Fragment {
    private static LogInFragment instance;

    public LogInFragment getInstance() {
        if (instance == null) {
            instance = new LogInFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LogInViewModel viewModel = new LogInViewModel(getActivity());
        FragmentLoginBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }
}
