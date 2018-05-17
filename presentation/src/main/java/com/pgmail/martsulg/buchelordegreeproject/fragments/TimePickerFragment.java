package com.pgmail.martsulg.buchelordegreeproject.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.pgmail.martsulg.buchelordegreeproject.R;
import com.pgmail.martsulg.buchelordegreeproject.activities.NavigationActivity;

import java.util.Calendar;

/**
 * Created by g_washingt0n on 04.05.2018.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private TextView date;

    public TimePickerFragment getInstance() {

        if (date != null) {
            Bundle bundle = new Bundle();
            bundle.putString("time", date.toString());//.get()/*.toString()*/);
            this.setArguments(bundle);
            this.date = date;
        } else {
            this.setArguments(null);
        }
        return new TimePickerFragment();
    }

    private String time;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            Bundle bundle = this.getArguments();
            time = bundle.getString("time");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // устанавливаем текущее время для TimePicker
        Calendar calendar = Calendar.getInstance();
        if (time != null) {
            calendar.setTimeInMillis(Long.valueOf(time));
        }
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        Dialog picker = new TimePickerDialog(getActivity(), this, hour, minute, true);
        picker.setTitle(getResources().getString(R.string.setTrainingTime));

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
        // Выводим выбранное время
//        TextView picker =
//        picker.setText(hours);
        NavigationActivity.setPreferences(NavigationActivity.HOURS, hours);
        NavigationActivity.setPreferences(NavigationActivity.MINUTES, minute);
//        date.setText(hours + minute);
    }
}
