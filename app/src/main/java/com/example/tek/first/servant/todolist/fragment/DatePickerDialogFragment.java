package com.example.tek.first.servant.todolist.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import com.example.tek.first.servant.todolist.helper.CommonConstants;
import com.example.tek.first.servant.todolist.model.DateModel;

import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DateModel dateSet;
    private int year;
    private int month;
    private int day;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            year = bundle.getInt(CommonConstants.YEAR_IDENTIFIER);
            month = bundle.getInt(CommonConstants.MONTH_IDENTIFIER);
            day = bundle.getInt(CommonConstants.DAY_IDENTIFIER);
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

        dateSet = new DateModel(month, day, year);

    }
}
