package com.pgmail.martsulg.bachelordegreeproject.viewModels;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.pgmail.martsulg.bachelordegreeproject.R;
import com.pgmail.martsulg.bachelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.bachelordegreeproject.extras.CustomDateUtils;
import com.pgmail.martsulg.bachelordegreeproject.extras.TimePickerListener;
import com.pgmail.martsulg.bachelordegreeproject.fragments.SetConstructFragment;
import com.pgmail.martsulg.bachelordegreeproject.fragments.TimePickerFragment;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.Relation;
import p.martsulg.data.models.RequestRelation;
import p.martsulg.data.models.SetsFeed;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.sets.AddSetUseCase;
import p.martsulg.domain.sets.SetRelationUseCase;


/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class SetConstructViewModel implements MyViewModel, TimePickerListener {

    private AddSetUseCase addUseCase = new AddSetUseCase();
    private SetRelationUseCase setRelationUseCase = new SetRelationUseCase();
    private RequestRelation requestRelation = new RequestRelation();
    private FragmentActivity activity;
    private Bundle bundle;
    private int hours;
    private int minutes;

    public ObservableField<String> order = new ObservableField<>();
    public ObservableField<String> repeats = new ObservableField<>();
    public ObservableField<String> weight = new ObservableField<>();
    public ObservableField<String>  reqTime = new ObservableField<>();
    public ObservableField<String>  restTime = new ObservableField<>();
    private String objectId;
    private SetConstructFragment setConstructFragment;
    private String currentExerciseId;

    public SetConstructViewModel(SetConstructFragment setConstructFragment, FragmentActivity activity,
                                 Bundle bundle,String currentExerciseId) {
        this.activity = activity;
        this.bundle = bundle;
        this.setConstructFragment = setConstructFragment;
        this.currentExerciseId = currentExerciseId;
    }

    @Override
    public void onTimePicked(int h, int m) {
        this.hours = h;
        this.minutes = m;
        reqTime.set(CustomDateUtils.timeToFormattedStr(h, m));
    }

    @Override
    public void resume() {
        if (bundle != null) {
//            name.set(bundle.getString(NavigationActivity.NAME));
//            spinnerSelection = bundle.getInt(NavigationActivity.WEEKDAY);
//            time.set(CustomDateUtils.millisToTime(bundle.getLong(NavigationActivity.TIME)));
//            stars.set(bundle.getFloat(NavigationActivity.COMPLEXITY));
//            objectId = bundle.getString(NavigationActivity.ID);
        }


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

    public void onTimePickerClick1() {
        DialogFragment newFragment = TimePickerFragment.getInstance(this);
        newFragment.show(setConstructFragment.getFragmentManager(), "timePicker1");
    }

    public void onTimePickerClick2() {
        DialogFragment newFragment = TimePickerFragment.getInstance(this);
        newFragment.show(setConstructFragment.getFragmentManager(), "timePicker2");
    }

    public void onApplyClick() {
        if (bundle != null) {
            updateRequest();
        } else {
            addRequest();
        }
    }

    private void updateRequest() {
//        if (name.get() != null && !day.equals("- Select your training day -") && stars.get() != null && time.get() != null) {
//            TrainingsFeed feed = new TrainingsFeed();
//            feed.setTrainingName(name.get());
//            feed.setWeekday(WeekdaysEnum.convertDayToInt(day));
//            feed.setOwnerId(new UserInfo().getInstance().getOwnerId());
//            feed.setObjectId(objectId);
//            feed.setComplexity(stars.get());
//            feed.setTime(CustomDateUtils.timeToMillis(hours, minutes));
//
//            updateUseCase.execute(feed, new DisposableObserver<TrainingsFeed>() {
//                @Override
//                public void onNext(TrainingsFeed feed) {
//                    onCancelClick();
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    Toast.makeText(activity, R.string.error_updating_data, Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onComplete() {
//                    updateUseCase.dispose();
//                }
//            });
//        } else {
//            Toast.makeText(activity, R.string.fields_cannot_be_empty, Toast.LENGTH_SHORT).show();
//        }
    }

    public void onCancelClick() {
        this.release();
        activity.onBackPressed();
    }

    @Override
    public void addRequest() {
        NavigationActivity.showProgress(activity.getSupportFragmentManager());

        if (order.get() != null && repeats!=null && weight.get() != null && reqTime.get() != null && restTime!=null) {
            SetsFeed feed = new SetsFeed();
            feed.setSetNumber(Integer.valueOf(order.get()));
            feed.setRepsNum(Integer.valueOf(repeats.get()));
            feed.setOwnerId(new UserInfo().getInstance().getOwnerId());
            feed.setRepWeight(Integer.valueOf(weight.get()));
            feed.setReqTime(CustomDateUtils.timeToMillis(hours, minutes));
            feed.setRestTime(CustomDateUtils.timeToMillis(hours, minutes));


            addUseCase.execute(feed, new DisposableObserver<SetsFeed>() {
                @Override
                public void onNext(SetsFeed feed) {
                    Relation relation = new Relation();
                    relation.setObjectId(feed.getObjectId());
                    requestRelation.setObjectId(currentExerciseId);
                    requestRelation.setmRelation(relation);
                    newRelation();
                    onCancelClick();
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
        } else {
            Toast.makeText(activity, R.string.fields_cannot_be_empty, Toast.LENGTH_SHORT).show();
        }
    }

    private void newRelation(){
        setRelationUseCase.execute(requestRelation, new DisposableObserver<Integer>() {
            @Override
            public void onNext(@io.reactivex.annotations.NonNull Integer response) {
                NavigationActivity.removeProgress(activity.getSupportFragmentManager());
                if(response == 1){
                    onCancelClick();
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
                setRelationUseCase.dispose();
            }
        });
    }


}
