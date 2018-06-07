package com.pgmail.martsulg.buchelordegreeproject.viewModels;


import android.content.Intent;
import android.databinding.ObservableField;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.pgmail.martsulg.buchelordegreeproject.activities.EntryActivity;
import com.pgmail.martsulg.buchelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.buchelordegreeproject.fragments.RegistryFragment;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.LogInUser;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.entry.LogProfileUseCase;
import p.martsulg.domain.entry.ValidTokenUseCase;

import static com.pgmail.martsulg.buchelordegreeproject.activities.EntryActivity.TOKEN_NAME;

public class LogInViewModel {

    public ObservableField<String> email2send = new ObservableField<>();
    public ObservableField<String> password2send = new ObservableField<>();

    FragmentActivity activity;
    Intent intent;

    public LogInViewModel(FragmentActivity activity) {
        this.activity = activity;
        intent = new Intent(activity, NavigationActivity.class);
        isUserSignedIn();
    }

    private ValidTokenUseCase tokenUseCase = new ValidTokenUseCase();
    private LogProfileUseCase logProfileUseCase = new LogProfileUseCase();


    public void onSignInClick() {
        EntryActivity.showProgress(activity.getSupportFragmentManager());

        LogInUser user = new LogInUser();
        user.setEmail(email2send.get());
        user.setPassword(password2send.get());

        logProfileUseCase.execute(user, new DisposableObserver<UserInfo>() {

            @Override
            public void onNext(@NonNull UserInfo response) {
                if (response != null) {
                    EntryActivity.setPreferences(TOKEN_NAME, response.getToken());
                    EntryActivity.setPreferences(EntryActivity.USER_ID, response.getObjectId());
                    //intent.putExtra("Token",EntryActivity.preferences.getString("Token", null));
//                    UserInfo user = new UserInfo().getInstance();
//                    user.setAge(response.getAge());
//                    user.setOwnerId(response.getOwnerId());

                    activity.startActivity(intent);
                    activity.finish();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                //TODO remove with error fields in TextInputFields
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT);
                EntryActivity.removeProgress(activity.getSupportFragmentManager());
            }

            @Override
            public void onComplete() {
                logProfileUseCase.dispose();
                EntryActivity.removeProgress(activity.getSupportFragmentManager());
            }
        });
    }

    public void onSignUpClick() {
        EntryActivity.showFragment(activity.getSupportFragmentManager(), new RegistryFragment().getInstance());
    }

    private void isUserSignedIn() {
        EntryActivity.showProgress(activity.getSupportFragmentManager());
        tokenUseCase.execute(EntryActivity.preferences.getString(TOKEN_NAME, null), new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean response) {
                if (response) {
                    activity.startActivity(intent);
                    activity.finish();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                tokenUseCase.dispose();
//                EntryActivity.removeProgress(activity.getSupportFragmentManager());
            }
        });
        EntryActivity.removeProgress(activity.getSupportFragmentManager());

    }
}
