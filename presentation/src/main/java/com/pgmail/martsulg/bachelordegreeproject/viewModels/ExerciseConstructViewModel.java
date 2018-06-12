package com.pgmail.martsulg.bachelordegreeproject.viewModels;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.pgmail.martsulg.bachelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.bachelordegreeproject.fragments.ExerciseConstructFragment;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.ExercisesFeed;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.trainings.AddExerciseUseCase;


/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class ExerciseConstructViewModel implements MyViewModel {

    private AddExerciseUseCase addUseCase = new AddExerciseUseCase();
    private FragmentActivity activity;
    private Bundle bundle;

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> sets = new ObservableField<>();
    public ObservableField<Boolean> repeatable = new ObservableField<>();
    private ExerciseConstructFragment exerciseConstructFragment;

    public ExerciseConstructViewModel(ExerciseConstructFragment exerciseConstructFragment, FragmentActivity activity, Bundle bundle) {
        this.activity = activity;
        this.bundle = bundle;
        this.exerciseConstructFragment = exerciseConstructFragment;
    }

    @Override
    public void resume() {
        //TODO remove with checkbox
        int spinnerSelection = 0;
        if (bundle != null) {

            //TODO remove with another fields
            name.set(bundle.getString(NavigationActivity.NAME));
            spinnerSelection = bundle.getInt(NavigationActivity.WEEKDAY);

        }


        //TODO remove this call
//        setTimeField(13,13);
    }

    @Override
    public void init() {

    }

    @Override
    public void pause() {
    }

    @Override
    public void getRequest() {
    }

    @Override
    public void delRequest(String objectId, int position) {
    }


    @Override
    public void release() {
    }

    public void onApplyClick() {
        addRequest();
    }

    public void onCancelClick() {
        this.release();
        activity.onBackPressed();
    }

    @Override
    public void addRequest() {
        //TODO ExercisesFeed


        ExercisesFeed feed = new ExercisesFeed();
        feed.setExerciseName(name.get());
        feed.setSetsNum(Integer.valueOf(sets.get()));
        feed.setOwnerId(new UserInfo().getInstance().getOwnerId());
        feed.setRepeatable(repeatable.get());


        addUseCase.execute(feed, new DisposableObserver<Void>() {
            @Override
            public void onNext(Void aVoid) {
                //clear shared
//                NavigationActivity.removePreferences(NavigationActivity.HOURS);
//                NavigationActivity.removePreferences(NavigationActivity.MINUTES);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                addUseCase.dispose();
            }
        });
        activity.onBackPressed();
    }


}
