package com.pgmail.martsulg.bachelordegreeproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.pgmail.martsulg.bachelordegreeproject.R;
import com.pgmail.martsulg.bachelordegreeproject.fragments.LogInFragment;
import com.pgmail.martsulg.bachelordegreeproject.fragments.ProgressBarFragment;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.domain.entry.ValidTokenUseCase;

public class EntryActivity extends FragmentActivity {
    public static SharedPreferences preferences;

    private final String SHARED_PREF_NAME = "sharedEntry";
    public static final String TOKEN_NAME = "Token";
    public static final String USER_ID = "ID";

    ValidTokenUseCase tokenUseCase = new ValidTokenUseCase();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        preferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        showProgress(getSupportFragmentManager());
        tokenUseCase.execute(preferences.getString(TOKEN_NAME, null), new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean response) {
                if (response) {
                    Intent intent = new Intent(EntryActivity.this, NavigationActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                tokenUseCase.dispose();
                removeProgress(getSupportFragmentManager());
            }
        });

        showFragment(getSupportFragmentManager(), new LogInFragment().getInstance());
    }

    public static void setPreferences(String key, String value) {
        preferences.edit()
                .putString(key, value)
                .apply();
    }

    public static void showFragment(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.entryContainer, fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void showProgress(FragmentManager fragmentManager) {
        ProgressBarFragment progressBar = new ProgressBarFragment().getInstance();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.entryContainer, progressBar);
        transaction.setPrimaryNavigationFragment(progressBar);
        transaction.commit();
    }

    public static void removeProgress(FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(new ProgressBarFragment().getInstance());
        transaction.commit();
    }
}
