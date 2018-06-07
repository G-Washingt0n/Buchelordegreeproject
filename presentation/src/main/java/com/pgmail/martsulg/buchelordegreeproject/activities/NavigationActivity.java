package com.pgmail.martsulg.buchelordegreeproject.activities;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.pgmail.martsulg.buchelordegreeproject.fragments.ProgressBarFragment;
import com.pgmail.martsulg.buchelordegreeproject.fragments.ProgressFragment;
import com.pgmail.martsulg.buchelordegreeproject.fragments.TrainingsFragment;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.trainings.GetUserInfoUseCase;

public class NavigationActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    public static SharedPreferences preferences;
    private final String SHARED_PREF_NAME = "sharedNavigation";
    public static final String HOURS = "hours";
    public static final String MINUTES = "minutes";
    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String WEEKDAY = "weekday";
    public static final String TIME = "time";
    public static final String COMPLEXITY = "complexity";
    public static final String NUMBER = "number";
    public static final String POSITION = "position";

    private GetUserInfoUseCase userInfoUseCase = new GetUserInfoUseCase();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        preferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        userInfoUseCase.execute(EntryActivity.preferences.getString(EntryActivity.USER_ID, null),
                new DisposableObserver<UserInfo>() {
                    @Override
                    public void onNext(UserInfo userResponse) {
                        UserInfo user = new UserInfo();
                        user.renewUserInfo(userResponse);
                        user.setToken(preferences.getString(EntryActivity.TOKEN_NAME, null));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (savedInstanceState == null) {
                            showFragment(getSupportFragmentManager(), TrainingsFragment.getInstance(getSupportFragmentManager()));
                        }
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_trainings:
                showFragment(getSupportFragmentManager(), TrainingsFragment.getInstance(getSupportFragmentManager()));
                return true;
            case R.id.navigation_progress:
                showFragment(getSupportFragmentManager(), new ProgressFragment().newInstance());
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

    public static void showProgress(FragmentManager fragmentManager) {
        ProgressBarFragment progressBar = new ProgressBarFragment().getInstance();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.navigation_container, progressBar);
        transaction.setPrimaryNavigationFragment(progressBar);
        transaction.commit();
    }

    public static void removeProgress(FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(new ProgressBarFragment().getInstance());
        transaction.commit();
    }

    public static void putExtraFragment(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.navigation_container, fragment);
        transaction.addToBackStack(null);
        transaction.setPrimaryNavigationFragment(fragment);
        transaction.commit();
    }

    public static void removeExtraFragment(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }

    public static void setPreferences(String key, int value) {
        preferences.edit()
                .putInt(key, value)
                .apply();
    }

    public static void removePreferences(String key) {
        preferences.edit()
                .remove(key)
                .apply();
    }

}
