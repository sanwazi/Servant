package com.example.tek.first.servant.todolist.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.tek.first.servant.R;

/**
 * Created by Leon on 8/26/2015.
 */
public class DatePickerFragment extends Fragment {

    private DatePicker datePicker;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.todolist_datepicker, null);

        datePicker = (DatePicker) rootView.findViewById(R.id.todolist_datepicker);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
