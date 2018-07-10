package com.pgmail.martsulg.bachelordegreeproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.bachelordegreeproject.R;
import com.pgmail.martsulg.bachelordegreeproject.extras.TimerView;

import java.util.ArrayList;

import p.martsulg.data.models.ExercisesFeed;

/**
 * Created by g_washingt0n on 15.04.2018.
 */

public class TimerFragment extends Fragment {

    private ArrayList<Integer> times;

    public static TimerFragment getInstance(FragmentManager fragmentManager, ExercisesFeed feed) {
        Fragment fragment = fragmentManager
                .findFragmentByTag(TimerFragment.class.getName());
        TimerFragment timerFragment;
        if (fragment != null && fragment instanceof TimerFragment) {
            timerFragment = (TimerFragment) fragment;
        } else {
            timerFragment = new TimerFragment();

        }
        Bundle bundle = new Bundle();
//        ArrayList<Integer> times = new ArrayList<>();
//        int size =feed.getSets().size();
//        for(int i=0;i<size;i++){
//            times.add(Integer.valueOf(String.valueOf(feed.getSets().get(i).getReqTime())));
//            times.add(Integer.valueOf(String.valueOf(feed.getSets().get(i).getRestTime())));
//        }
//        bundle.putIntegerArrayList(NavigationActivity.NUMBER, times);
//        timerFragment.setArguments(bundle);

        return timerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle bundle = this.getArguments();
//        this.times = bundle.getIntegerArrayList(NavigationActivity.NUMBER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timer, container, false);

//        SetsViewModel setsViewModel = new SetsViewModel(getActivity(), exerciseId);
//        this.viewModel = setsViewModel;
//        FragmentSetsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sets, container, false);
//        binding.setViewModel(setsViewModel);
//        binding.setsRecyclerView.setAdapter(setsViewModel.adapter);
//        binding.setsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        return binding.getRoot();
    }

    private Thread thread;

    @Override
    public void onResume() {
        super.onResume();
        thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (getActivity() == null)
                    return;

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TimerView.seconds++;
                    }
                });
            }
        });
        thread.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        thread.interrupt();
    }

}
