package com.example.tek.first.servant.todolist.fragment;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tek.first.servant.R;
import com.example.tek.first.servant.todolist.activity.ToDoListMainActivity;
import com.example.tek.first.servant.todolist.helper.CommonConstants;
import com.example.tek.first.servant.todolist.model.DateModel;
import com.example.tek.first.servant.todolist.model.TimeModel;

public class DetailedToDoListItemDialog extends DialogFragment {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerPriority;
    private Button btnDatePicker;
    private Button btnTimePicker;
    private Button btnConfirm;

    private Bundle bundle;
    private TimeModel timeSet;
    private DateModel dateSet;

    private int timePickerHour = 12;
    private int timePickerMin = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.todolistitemdetail_dialog, null);
        editTextTitle = (EditText) rootView.findViewById(R.id.edittext_title_todolistitem);
        editTextDescription = (EditText) rootView.findViewById(R.id.edittext_description_todolistitem);

        spinnerPriority = (Spinner) rootView.findViewById(R.id.spinner_priority_todolistitem);
        btnTimePicker = (Button) rootView.findViewById(R.id.todolist_btn_selecttime);
        btnDatePicker = (Button) rootView.findViewById(R.id.todolist_btn_selectdate);
        btnConfirm = (Button) rootView.findViewById(R.id.btn_confirm_dialog);

        Handler handler = new Handler() {
            public void handleMsssage(Message message) {
                bundle = message.getData();
                timeSet = bundle.getParcelable(CommonConstants.TIME_SET_IDENTIFIER);
            }
        };

        btnTimePicker.setText(timeSet.getHour() + ": " + timeSet.getMinute());

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new TimePickerDialogFragment();
                dialogFragment.show(getFragmentManager(), "timePicker");
            }
        });

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerDialogFragment();
                dialogFragment.show(getFragmentManager(), "datePicker");
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText = editTextTitle.getText().toString();
                if (titleText != null) {
                    String descriptionText = editTextDescription.getText().toString();
                    Intent detailedInfoIntent = new Intent(getActivity(), ToDoListMainActivity.class);
                    detailedInfoIntent.putExtra(CommonConstants.DESCRIPTION_IDENTIFIER, descriptionText);
                    startActivity(detailedInfoIntent);
                } else {
                    Toast.makeText(getActivity(), "Please input a title", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }
}
