package com.pgmail.martsulg.buchelordegreeproject.viewModels;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.pgmail.martsulg.buchelordegreeproject.R;
import com.pgmail.martsulg.buchelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.buchelordegreeproject.extras.CustomDateUtils;
import com.pgmail.martsulg.buchelordegreeproject.extras.TimePickerListener;
import com.pgmail.martsulg.buchelordegreeproject.extras.WeekdaysEnum;
import com.pgmail.martsulg.buchelordegreeproject.fragments.TimePickerFragment;
import com.pgmail.martsulg.buchelordegreeproject.fragments.TrainingConstructFragment;
import com.pgmail.martsulg.buchelordegreeproject.fragments.TrainingsFragment;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.TrainingsFeed;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.trainings.AddTrainingUseCase;



/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class TrainingConstructViewModel implements MyViewModel, TimePickerListener {

    private AddTrainingUseCase addUseCase = new AddTrainingUseCase();
    private FragmentActivity activity;
    private Bundle bundle;
    private int hours;
    private int minutes;

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<Float> stars = new ObservableField<>();
    private String day;
    private Spinner spinner;
    private TrainingConstructFragment trainingConstructFragment;

    public TrainingConstructViewModel(TrainingConstructFragment trainingConstructFragment, FragmentActivity activity, Bundle bundle) {
        this.activity = activity;
        this.bundle = bundle;
        this.trainingConstructFragment = trainingConstructFragment;
    }

    @Override
    public void onTimePicked(int h, int m) {
        setTimeField(h, m);
    }

    @Override
    public void resume() {
        int spinnerSelection = 0;
        if (bundle != null) {
            name.set(bundle.getString(NavigationActivity.NAME));
            spinnerSelection = bundle.getInt(NavigationActivity.WEEKDAY);
            time.set(CustomDateUtils.millisToTime(bundle.getLong(NavigationActivity.TIME)));
            stars.set(bundle.getFloat(NavigationActivity.COMPLEXITY));
        }

        spinner = activity.findViewById(R.id.construct_spinner);
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(activity, R.array.weekdays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(spinnerSelection);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                day = (String) parent.getItemAtPosition(selectedItemPosition);

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


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
    public void delRequest() {
    }


    @Override
    public void release() {
    }

    private void setTimeField(int h, int m) {
        //replace with service - ?
        // add parameters

        time.set(CustomDateUtils.timeToFormattedStr(h, m));

//
//        if (bundle != null) {
//            time.set(bundle.getString("date"));
//        } else if (NavigationActivity.preferences.getInt(NavigationActivity.HOURS, 0) != 0) {
//            String str;
//            hours = NavigationActivity.preferences.getInt(NavigationActivity.HOURS, 0);
//            minutes = NavigationActivity.preferences.getInt(NavigationActivity.MINUTES, 0);
////            str = String.format("%02d:%02d", hours, minutes);
//            time.set(CustomDateUtils.timeToFormattedStr(h,m));
////            str = hours + ":" + minutes;
////            DateFormat format = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
////            try {
////                Date date = format.parse(str);
////                time.set(date.toString());
////            } catch (ParseException e) {
////                e.printStackTrace();
////                time.set(str);
////            }
//        }
    }

    public void onTimePickerClick() {
        DialogFragment newFragment = TimePickerFragment.getInstance();//trainingConstructFragment);

        newFragment.show(trainingConstructFragment.getFragmentManager(), "timePicker");
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
        feed.setWeekday(WeekdaysEnum.convertDayToInt(day));
        feed.setOwnerId(new UserInfo().getInstance().getOwnerId());
        feed.setComplexity(stars.get());
        feed.setTime(CustomDateUtils.timeToMillis(hours, minutes));
        //взять дату из shared
//        feed.setToken(EntryActivity.preferences.getString("Token", null));
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
                activity.onBackPressed();
                NavigationActivity.showFragment(activity.getSupportFragmentManager(), new TrainingsFragment().getParentFragment());
            }
        });
    }


}
