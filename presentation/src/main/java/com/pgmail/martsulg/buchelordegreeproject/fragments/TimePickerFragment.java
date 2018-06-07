package com.pgmail.martsulg.buchelordegreeproject.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.TimePicker;

import com.pgmail.martsulg.buchelordegreeproject.R;
import com.pgmail.martsulg.buchelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.buchelordegreeproject.extras.TimePickerListener;

import java.util.Calendar;

/**
 * Created by g_washingt0n on 04.05.2018.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private TimePickerListener listener;

    public static TimePickerFragment getInstance(/*TimePickerListener trainingConstructFragment*/) {
        TimePickerFragment fragment = new TimePickerFragment();
        return fragment;
    }

    public void setListener(int h, int m) {
        listener.onTimePicked(h, m);
        ;
    }

    //    private OnTimePickedListener listener;
//    public interface OnTimePickedListener {
//        public void onTimePicked(int h, int m);
//    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TimePickerListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // устанавливаем текущее время для TimePicker
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        Dialog picker = new TimePickerDialog(getActivity(), this, hour, minute, true);
//        picker.setTitle(getResources().getString(R.string.setTrainingTime));

        return picker;
    }

    @Override
    public void onStart() {
        super.onStart();
        // добавляем кастомный текст для кнопки
        Button nButton = ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_POSITIVE);
        nButton.setText(getResources().getString(R.string.setTime));

    }

    @Override
    public void onTimeSet(TimePicker view, int hours, int minute) {

        NavigationActivity.setPreferences(NavigationActivity.HOURS, hours);
        NavigationActivity.setPreferences(NavigationActivity.MINUTES, minute);
//        date.setText(hours + minute);

        listener.onTimePicked(hours, minute);
        ;

//        setListener(hours,minute);
    }
}
