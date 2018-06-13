package com.pgmail.martsulg.bachelordegreeproject.viewModels;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.pgmail.martsulg.bachelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.bachelordegreeproject.fragments.ExerciseConstructFragment;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.ExercisesFeed;
import p.martsulg.data.models.Relation;
import p.martsulg.data.models.RequestRelation;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.exercises.AddExerciseUseCase;
import p.martsulg.domain.exercises.ExerciseRelationUseCase;


/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class ExerciseConstructViewModel implements MyViewModel {

    private AddExerciseUseCase addUseCase = new AddExerciseUseCase();
    private ExerciseRelationUseCase exerciseRelationUseCase = new ExerciseRelationUseCase();
    private RequestRelation requestRelation = new RequestRelation();
    private FragmentActivity activity;
    private Bundle bundle;
    private String currentTrainingId;

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> sets = new ObservableField<>();
    public ObservableField<Boolean> repeatable = new ObservableField<>();
    private ExerciseConstructFragment exerciseConstructFragment;

    public ExerciseConstructViewModel(ExerciseConstructFragment exerciseConstructFragment, FragmentActivity activity,
                                      Bundle bundle, String currentTrainingId) {
        this.activity = activity;
        this.bundle = bundle;
        this.exerciseConstructFragment = exerciseConstructFragment;
        this.currentTrainingId = currentTrainingId;
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
        NavigationActivity.showProgress(activity.getSupportFragmentManager());
        ExercisesFeed feed = new ExercisesFeed();
        feed.setExerciseName(name.get());
        feed.setSetsNum(Integer.valueOf(sets.get()));
        feed.setOwnerId(new UserInfo().getInstance().getOwnerId());
//        feed.setRepeatable(repeatable.get());


        addUseCase.execute(feed, new DisposableObserver<ExercisesFeed>() {
            @Override
            public void onNext(ExercisesFeed exercisesFeed) {
                Relation relation = new Relation();
                relation.setObjectId(exercisesFeed.getObjectId());
                requestRelation.setObjectId(currentTrainingId);
                requestRelation.setmRelation(relation);
                newRelation();
            }

            @Override
            public void onError(Throwable e) {
                NavigationActivity.removeProgress(activity.getSupportFragmentManager());
                //TODO remove with error fields in TextInputFields
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                addUseCase.dispose();
            }
        });

    }

    private void newRelation(){
        exerciseRelationUseCase.execute(requestRelation, new DisposableObserver<Integer>() {
            @Override
            public void onNext(@io.reactivex.annotations.NonNull Integer response) {
                NavigationActivity.removeProgress(activity.getSupportFragmentManager());
                if(response == 1){
                    activity.onBackPressed();
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
                exerciseRelationUseCase.dispose();
            }
        });
    }


}
