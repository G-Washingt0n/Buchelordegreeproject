package com.pgmail.martsulg.buchelordegreeproject.viewModels;


import android.content.Intent;
import android.databinding.ObservableField;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.pgmail.martsulg.buchelordegreeproject.activities.EntryActivity;
import com.pgmail.martsulg.buchelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.buchelordegreeproject.fragments.RegistryFragment;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.LogInUser;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.entry.LogProfileUseCase;

public class LogInViewModel {

    public ObservableField<String> email2send = new ObservableField<>();
    public ObservableField<String> password2send = new ObservableField<>();

    FragmentActivity activity;
    Intent intent;

    public LogInViewModel(FragmentActivity activity) {
        this.activity = activity;
        intent = new Intent(activity, NavigationActivity.class);
    }

    public LogProfileUseCase logProfileUseCase = new LogProfileUseCase();


    public void onSignInClick() {
//        try {

        LogInUser user = new LogInUser();
        user.setEmail(email2send.get());
        user.setPassword(password2send.get());

        logProfileUseCase.execute(user, new DisposableObserver<UserInfo>() {


            @Override
            public void onNext(@NonNull UserInfo response) {
//                    if(response.getToken()!=null)
//                        EntryActivity.setPreferences("Token", response.getToken());
//                    intent.putExtra("Token",EntryActivity.preferences.getString("Token", null));
//                    activity.startActivity(intent);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("Signing in Error", e.toString());
            }

            @Override
            public void onComplete() {
                logProfileUseCase.dispose();
            }
        });
//        } catch (Exception e) {
//            Log.e("Error", e.toString());
//
//        }
    }

    public void onSignUpClick() {
        EntryActivity.showFragment(activity.getSupportFragmentManager(), new RegistryFragment().getInstance());
    }

}
