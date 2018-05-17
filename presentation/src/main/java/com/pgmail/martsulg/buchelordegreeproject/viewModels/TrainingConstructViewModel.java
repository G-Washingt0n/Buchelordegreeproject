package com.pgmail.martsulg.buchelordegreeproject.viewModels;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.pgmail.martsulg.buchelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.buchelordegreeproject.extras.WeekdaysEnum;
import com.pgmail.martsulg.buchelordegreeproject.fragments.TimePickerFragment;
import com.pgmail.martsulg.buchelordegreeproject.fragments.TrainingsFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.TrainingsFeed;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.trainings.AddTrainingUseCase;


/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class TrainingConstructViewModel implements MyViewModel {

    private AddTrainingUseCase addUseCase = new AddTrainingUseCase();
    private FragmentActivity activity;
    private Bundle bundle;
    private int hours;
    private int minutes;

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> day = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<Float> stars = new ObservableField<>();

    public TrainingConstructViewModel(FragmentActivity activity, Bundle bundle) {
        this.activity = activity;
        this.bundle = bundle;
    }

    @Override
    public void init() {
        if (bundle != null) {
            name.set(bundle.getString(NavigationActivity.NAME));
        }
    }


    @Override
    public void resume() {
        //TODO remove this call
        setTimeField();
    }

    @Override
    public void pause() {
    }

    @Override
    public void getRequest() {
    }

    @Override
    public void delRequest() {
    }


    @Override
    public void release() {
    }

    private void setTimeField() {
        //replace with service - ?
        // add parameters
        if (bundle != null) {
            //convert to h:m using Calendar
            time.set(bundle.getString("date"));
        } else if (NavigationActivity.preferences.getInt(NavigationActivity.HOURS, 0) != 0) {
            String str;
            hours = NavigationActivity.preferences.getInt(NavigationActivity.HOURS, 0);
            minutes = NavigationActivity.preferences.getInt(NavigationActivity.MINUTES, 0);
            str = hours + ":" + minutes;
            DateFormat format = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
            try {
                Date date = format.parse(str);
                time.set(date.toString());
            } catch (ParseException e) {
                e.printStackTrace();
                time.set(str);
            }

        }
    }

    public void onDayClick() {
//        if (!comment2send.equals(null) && !title2send.equals(null))
//            addRequest();
    }

    public void onTimePickerClick() {
        DialogFragment newFragment = new TimePickerFragment().getInstance();
        newFragment.show(activity.getSupportFragmentManager(), "timePicker");
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
        TrainingsFeed feed = new TrainingsFeed();
        feed.setTrainingName(name.get());
        feed.setWeekday(WeekdaysEnum.convertDayToInt(day.get()));
        feed.setOwnerId(new UserInfo().getInstance().getOwnerId());
        feed.setComplexity(stars.get());
        feed.setTime(timeToMillis(hours, minutes));
        //взять дату из shared
//        feed.setToken(EntryActivity.preferences.getString("Token", null));
        addUseCase.execute(feed, new DisposableObserver<Void>() {
            @Override
            public void onNext(Void aVoid) {
                //clear shared

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                addUseCase.dispose();
                activity.onBackPressed();
                NavigationActivity.showFragment(activity.getSupportFragmentManager(), new TrainingsFragment().getParentFragment());
            }
        });
    }

    private long timeToMillis(int hours, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        Log.e("time in millis: ", String.valueOf(calendar.getTimeInMillis()));
        return calendar.getTimeInMillis();
    }

}
