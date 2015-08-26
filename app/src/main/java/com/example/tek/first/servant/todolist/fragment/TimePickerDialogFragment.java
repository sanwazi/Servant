package com.example.tek.first.servant.todolist.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;


import com.example.tek.first.servant.todolist.helper.CommonConstants;
import com.example.tek.first.servant.todolist.model.TimeModel;

import java.util.Calendar;

public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private static String LOG_TAG = TimePickerDialogFragment.class.getSimpleName();

    private Bundle bundle;
    private TimeModel timeModel;
    private int hour;
    private int minute;

    private Handler timerPickerHandler;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        bundle = getArguments();
        if (bundle != null) {
            hour = bundle.getInt(CommonConstants.HOUR_IDENTIFIER);
            minute = bundle.getInt(CommonConstants.MINUTE_IDENTIFIER);
        } else {
            final Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
        }
        timerPickerHandler = new Handler();
        Log.v(LOG_TAG, "onCreateDialog() method, TimePickerDialogFragment executed");

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        hour = hourOfDay;
        this.minute = minute;

        timeModel = new TimeModel(hour, minute);
        Log.v(LOG_TAG, "onTimeSet() method executed");

        Bundle timeSetBundle = new Bundle();

        timeSetBundle.putParcelable(CommonConstants.TIME_SET_IDENTIFIER, timeModel);

        Message message = new Message();
        message.setData(timeSetBundle);

        timerPickerHandler.sendMessage(message);
    }
}
