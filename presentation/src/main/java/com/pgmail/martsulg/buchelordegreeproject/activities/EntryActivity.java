package com.pgmail.martsulg.buchelordegreeproject.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.pgmail.martsulg.buchelordegreeproject.R;
import com.pgmail.martsulg.buchelordegreeproject.fragments.LogInFragment;

public class EntryActivity extends FragmentActivity {
    public static SharedPreferences preferences;

    public static final String SHARED_PREF_NAME = "sharedname";
    public static final String TOKEN_NAME = "Token";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        preferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);


        if (savedInstanceState == null) {
//TODO - add in release
//            try { //автологин
//                if (!preferences.getString(TOKEN_NAME, null).isEmpty()) {
//                    Intent intent = new Intent(EntryActivity.this, NavigationActivity.class);
//                    startActivity(intent);
//                }
//            } catch (Exception e) {
//
//            }

            showFragment(getSupportFragmentManager(), new LogInFragment().getInstance());
        }

//        findViewById(R.id.goToSignIn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showFragment(getSupportFragmentManager(), new LogInFragment());
//            }
//        });
//
//        findViewById(R.id.goToSignUp).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showFragment(getSupportFragmentManager(), new RegistryFragment());
//            }
//        });

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
}
