package com.pgmail.martsulg.buchelordegreeproject.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.pgmail.martsulg.buchelordegreeproject.R;
import com.pgmail.martsulg.buchelordegreeproject.fragments.ProfileFragment;
import com.pgmail.martsulg.buchelordegreeproject.fragments.ResultsFragment;
import com.pgmail.martsulg.buchelordegreeproject.fragments.TrainingsFragment;

public class NavigationActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_trainings:
                showFragment(getSupportFragmentManager(), new TrainingsFragment().newInstance());
                return true;
            case R.id.navigation_results:
                showFragment(getSupportFragmentManager(), new ResultsFragment().newInstance());
                return true;
            case R.id.navigation_profile:
                showFragment(getSupportFragmentManager(), new ProfileFragment().newInstance());
                return true;
        }
        return false;
    }


    public static void showFragment(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.navigation_container, fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
