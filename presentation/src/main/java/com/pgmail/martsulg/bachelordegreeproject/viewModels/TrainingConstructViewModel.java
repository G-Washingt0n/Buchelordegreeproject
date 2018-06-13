package com.pgmail.martsulg.bachelordegreeproject.viewModels;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.pgmail.martsulg.bachelordegreeproject.R;
import com.pgmail.martsulg.bachelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.bachelordegreeproject.extras.CustomDateUtils;
import com.pgmail.martsulg.bachelordegreeproject.extras.TimePickerListener;
import com.pgmail.martsulg.bachelordegreeproject.extras.WeekdaysEnum;
import com.pgmail.martsulg.bachelordegreeproject.fragments.TimePickerFragment;
import com.pgmail.martsulg.bachelordegreeproject.fragments.TrainingConstructFragment;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.TrainingsFeed;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.trainings.AddTrainingUseCase;
import p.martsulg.domain.trainings.UpdateTrainingUseCase;


/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class TrainingConstructViewModel implements MyViewModel, TimePickerListener {

    private AddTrainingUseCase addUseCase = new AddTrainingUseCase();
    private UpdateTrainingUseCase updateUseCase = new UpdateTrainingUseCase();
    private FragmentActivity activity;
    private Bundle bundle;
    private int hours;
    private int minutes;

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<Float> stars = new ObservableField<>();
    private String day;
    private Spinner spinner;
    private String objectId;
    private TrainingConstructFragment trainingConstructFragment;

    public TrainingConstructViewModel(TrainingConstructFragment trainingConstructFragment, FragmentActivity activity, Bundle bundle) {
        this.activity = activity;
        this.bundle = bundle;
        this.trainingConstructFragment = trainingConstructFragment;
    }

    @Override
    public void onTimePicked(int h, int m) {
        this.hours = h;
        this.minutes = m;
        time.set(CustomDateUtils.timeToFormattedStr(h, m));
    }

    @Override
    public void resume() {
        int spinnerSelection = 0;
        if (bundle != null) {
            name.set(bundle.getString(NavigationActivity.NAME));
            spinnerSelection = bundle.getInt(NavigationActivity.WEEKDAY);
            time.set(CustomDateUtils.millisToTime(bundle.getLong(NavigationActivity.TIME)));
            stars.set(bundle.getFloat(NavigationActivity.COMPLEXITY));
            objectId = bundle.getString(NavigationActivity.ID);
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
    public void delRequest(String objectId, int position) {
    }


    @Override
    public void release() {
    }

    public void onTimePickerClick() {
        DialogFragment newFragment = TimePickerFragment.getInstance(this);
        newFragment.show(trainingConstructFragment.getFragmentManager(), "timePicker");
    }

    public void onApplyClick() {
        if (bundle != null) {
            updateRequest();
        } else {
            addRequest();
        }
    }

    private void updateRequest() {
        if (name.get() != null && !day.equals("- Select your training day -") && stars.get() != null && time.get() != null) {
            TrainingsFeed feed = new TrainingsFeed();
            feed.setTrainingName(name.get());
            feed.setWeekday(WeekdaysEnum.convertDayToInt(day));
            feed.setOwnerId(new UserInfo().getInstance().getOwnerId());
            feed.setObjectId(objectId);
            feed.setComplexity(stars.get());
            feed.setTime(CustomDateUtils.timeToMillis(hours, minutes));

            updateUseCase.execute(feed, new DisposableObserver<TrainingsFeed>() {
                @Override
                public void onNext(TrainingsFeed feed) {
                    onCancelClick();
                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(activity, R.string.error_updating_data, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onComplete() {
                    updateUseCase.dispose();
                }
            });
        } else {
            Toast.makeText(activity, R.string.fields_cannot_be_empty, Toast.LENGTH_SHORT).show();
        }
    }

    public void onCancelClick() {
        this.release();
        activity.onBackPressed();
    }

    @Override
    public void addRequest() {
        if (name.get() != null && !day.equals("- Select your training day -") && stars.get() != null && time.get() != null) {
            TrainingsFeed feed = new TrainingsFeed();
            feed.setTrainingName(name.get());
            feed.setWeekday(WeekdaysEnum.convertDayToInt(day));
            feed.setOwnerId(new UserInfo().getInstance().getOwnerId());
            feed.setComplexity(stars.get());
            feed.setTime(CustomDateUtils.timeToMillis(hours, minutes));

            addUseCase.execute(feed, new DisposableObserver<TrainingsFeed>() {
                @Override
                public void onNext(TrainingsFeed feed) {
                    onCancelClick();
                }

                @Override
                public void onError(Throwable e) {

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


}
