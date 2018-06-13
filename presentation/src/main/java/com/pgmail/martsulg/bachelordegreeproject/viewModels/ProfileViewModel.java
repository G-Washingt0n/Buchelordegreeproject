package com.pgmail.martsulg.bachelordegreeproject.viewModels;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.pgmail.martsulg.bachelordegreeproject.R;
import com.pgmail.martsulg.bachelordegreeproject.activities.EntryActivity;
import com.pgmail.martsulg.bachelordegreeproject.activities.NavigationActivity;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.entry.LogOutUseCase;
import p.martsulg.domain.entry.ValidTokenUseCase;
import p.martsulg.domain.user.UpdateUserInfoUseCase;
import retrofit2.Response;

import static com.pgmail.martsulg.bachelordegreeproject.activities.EntryActivity.SHARED_PREF_NAME;
import static com.pgmail.martsulg.bachelordegreeproject.activities.EntryActivity.TOKEN_NAME;

public class ProfileViewModel {

    public ObservableField<String> surname = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> age = new ObservableField<>();

    private UserInfo userInfo = new UserInfo().getInstance();
    private static SharedPreferences preferences;
    FragmentActivity activity;
    Intent intent;

    private UpdateUserInfoUseCase updateUserInfoUseCase = new UpdateUserInfoUseCase();
    private LogOutUseCase logOutUseCase = new LogOutUseCase();
    private ValidTokenUseCase validTokenUseCase = new ValidTokenUseCase();

    public ProfileViewModel(FragmentActivity activity) {
        this.activity = activity;
        intent = new Intent(activity, NavigationActivity.class);
        setUser();
    }

    private void setUser(){
        surname.set(userInfo.getSurname());
        name.set(userInfo.getName());
        email.set(userInfo.getEmail());
        age.set(String.valueOf(userInfo.getAge()));
    }

    private void getUser(){
        userInfo.setName(name.get());
        userInfo.setSurname(surname.get());
        userInfo.setEmail(email.get());
        userInfo.setAge(Integer.valueOf(age.get()));
    }

    public void updateUser(){
        NavigationActivity.showProgress(activity.getSupportFragmentManager());
        getUser();
        updateUserInfoUseCase.execute(userInfo, new DisposableObserver<UserInfo>() {

            @Override
            public void onNext(@NonNull UserInfo response) {
                setUser();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                //TODO remove with error fields in TextInputFields
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
                NavigationActivity.removeProgress(activity.getSupportFragmentManager());
            }

            @Override
            public void onComplete() {
                updateUserInfoUseCase.dispose();
                NavigationActivity.removeProgress(activity.getSupportFragmentManager());
            }
        });
    }

    public void createDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage(activity.getResources().getString(R.string.log_out_dialog));
        dialog.setPositiveButton(activity.getResources().getString(R.string.yes), (dialog1, arg1) -> logOut());
        dialog.setNegativeButton(activity.getResources().getString(R.string.no), (dialog2, arg1) -> dialog2.dismiss());
        dialog.show();
    }

    private void logOut(){
        NavigationActivity.showProgress(activity.getSupportFragmentManager());
        preferences = activity.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(preferences != null) {
            String token = preferences.getString(TOKEN_NAME, null);
            logOutUseCase.execute(token, new DisposableObserver<Response<Void>>() {
                @Override
                public void onNext(@io.reactivex.annotations.NonNull Response<Void> response) {
                    validate(token);
                }

                @Override
                public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                    NavigationActivity.removeProgress(activity.getSupportFragmentManager());
                    //TODO remove with error fields in TextInputFields
                    Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onComplete() {
                    NavigationActivity.removeProgress(activity.getSupportFragmentManager());
                    logOutUseCase.dispose();
                }
            });
        }
    }

    private void validate(final String token){
        validTokenUseCase.execute(token, new DisposableObserver<Boolean>() {
            @Override
            public void onNext(@io.reactivex.annotations.NonNull Boolean isValidate) {
                NavigationActivity.removeProgress(activity.getSupportFragmentManager());
                if(!isValidate){
                    Intent intent = new Intent(activity, EntryActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                    activity.finish();
                }else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                    dialog.setMessage(activity.getResources().getString(R.string.oops));
                    dialog.show();
                }
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                NavigationActivity.removeProgress(activity.getSupportFragmentManager());
                //TODO remove with error fields in TextInputFields
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                NavigationActivity.removeProgress(activity.getSupportFragmentManager());
                validTokenUseCase.dispose();
            }
        });
    }
}
