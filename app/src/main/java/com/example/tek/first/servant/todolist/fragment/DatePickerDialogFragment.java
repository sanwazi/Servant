package com.example.tek.first.servant.todolist.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.DatePicker;

import com.example.tek.first.servant.todolist.helper.GeneralConstants;
import com.example.tek.first.servant.todolist.model.DateModel;

import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DateModel dateSelected;
    private int year;
    private int month;
    private int day;

    public interface DatePickerDialogListener {
        void onDateSelected(DateModel dateSelected);
    }

    DatePickerDialogListener datePickerDialogListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            datePickerDialogListener = (DatePickerDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement DatePickerDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            year = bundle.getInt(GeneralConstants.YEAR_IDENTIFIER);
            month = bundle.getInt(GeneralConstants.MONTH_IDENTIFIER);
            day = bundle.getInt(GeneralConstants.DAY_IDENTIFIER);
        } else {
            final Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
        }
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        dateSelected = new DateModel(monthOfYear, dayOfMonth, year);
        datePickerDialogListener.onDateSelected(dateSelected);
    }
}