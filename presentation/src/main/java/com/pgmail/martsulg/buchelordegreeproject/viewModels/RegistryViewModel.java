package com.pgmail.martsulg.buchelordegreeproject.viewModels;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.pgmail.martsulg.buchelordegreeproject.activities.EntryActivity;
import com.pgmail.martsulg.buchelordegreeproject.fragments.LogInFragment;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.RegisterUser;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.entry.RegProfileUseCase;

public class RegistryViewModel {

    public ObservableField<String> name2reg = new ObservableField<>();
    public ObservableField<String> surname2reg = new ObservableField<>();
    public ObservableField<String> email2reg = new ObservableField<>();
    public ObservableField<String> password2reg = new ObservableField<>();
    public RegProfileUseCase regProfileUseCase = new RegProfileUseCase();
    private FragmentActivity activity;
    private Context context;

    public RegistryViewModel(FragmentActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }


    public void onSignUpClick() {
        EntryActivity.showProgress(activity.getSupportFragmentManager());

        RegisterUser profile = new RegisterUser();
        profile.setName(name2reg.get());
        profile.setSurname(surname2reg.get());
        profile.setEmail(email2reg.get());
        profile.setPassword(password2reg.get());
//        try {
//            File file = new File(context.getFilesDir(), "robot.jpg");
//            byte[] data = FileUtils.readFileToByteArray(file);
//            String avatar = Base64.encodeToString(data, Base64.NO_WRAP);
//            Log.e("Avatar", avatar);
//
//            profile.setAvatar(avatar);
//        } catch (Exception e) {
//            Log.e("File error", e.toString());
//        }

        regProfileUseCase.execute(profile, new DisposableObserver<UserInfo>() {

            @Override
            public void onNext(@NonNull UserInfo response) {
                Toast.makeText(context, "Registered successfully! ",
                        Toast.LENGTH_SHORT).show();
                toSigningIn();
            }

            @Override
            public void onError(@NonNull Throwable e) {
//TODO remove with error fields in TextInputFields
                Log.e("Registry Error", e.toString());
                EntryActivity.removeProgress(activity.getSupportFragmentManager());
            }

            @Override
            public void onComplete() {
                name2reg.set(null);
                surname2reg.set(null);
                email2reg.set(null);
                password2reg.set(null);
                regProfileUseCase.dispose();
                EntryActivity.removeProgress(activity.getSupportFragmentManager());
            }
        });

    }

    public void onSignInClick() {
        toSigningIn();
    }

    private void toSigningIn() {
        EntryActivity.showFragment(activity.getSupportFragmentManager(), new LogInFragment().getInstance());
    }
}
